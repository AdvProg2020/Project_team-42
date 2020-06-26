package Controller;

import Controller.AccountPagesController.AccountPageController;
import Controller.AccountPagesController.CustomerPageController;
import Model.Accounts.CustomerAccount;
import Model.Accounts.SellerAccount;
import Model.Discount;
import Model.Logs.BuyLog;
import Model.Logs.SellLog;
import Model.Product;
import Model.Shop;

import java.util.ArrayList;
import java.util.HashMap;

public class CartPageController {
    private static CartPageController cartPageController = new CartPageController();
    //private Shop Shop.getInstance();
    private HashMap<Integer, HashMap<String, Integer>> cart;

    private CartPageController () {
        //this.Shop.getInstance() = Shop.getInstance();
        cart = new HashMap<>();
    }

    public static CartPageController getInstance() {
        return cartPageController;
    }

    public void addProductToCart (SellerAccount seller, Product selectedProduct) throws Exceptions.NotCustomerException {
        if (AccountPageController.getUser() != null && CustomerPageController.getInstance().getCustomer() == null)
            throw new Exceptions.NotCustomerException();
        this.cart.put((int) selectedProduct.getProductId(), new HashMap<>());
        this.cart.get((int)selectedProduct.getProductId()).put(seller.getUserName(), 1);
    }

    public Product getCartProductById (long productId) throws Exceptions.NoProductWithThisIdInCartException {
        if (cart.containsKey((int)productId)) {
            try {
                return Shop.getInstance().getProductById(productId);
            } catch (Exceptions.NoProductByThisIdException ignored) {}
        }
        throw new Exceptions.NoProductWithThisIdInCartException();
    }

    public ArrayList<Product> getProducts () throws Exceptions.EmptyCartException {
        if (cart.isEmpty())
            throw new Exceptions.EmptyCartException();
        ArrayList<Product> products = new ArrayList<>();
        for (Integer productId : cart.keySet()) {
            try {
                products.add(Shop.getInstance().getProductById(productId));
            } catch (Exceptions.NoProductByThisIdException ignored) {}
        }
        return products;
    }

    public HashMap<SellerAccount, Integer> getSellersAndCountOfProduct (Product product) {
        HashMap<SellerAccount, Integer> sellerAndCount = new HashMap<>();
        for (String seller : this.cart.get((int)product.getProductId()).keySet()) {
            try {
                sellerAndCount.put(SellerAccount.getSellerAccountByUsername(seller), this.cart.get((int)product.getProductId()).get(seller));
            } catch (Exception ignored) {}
        }
        return sellerAndCount;
    }

    public void increaseProductCount (Product product, String sellerUserName) throws Exceptions.NoSellerByThisUserNameForProductException {
        SellerAccount seller = product.getSellerByUsername(sellerUserName);

        if (cart.get((int)product.getProductId()).containsKey(seller.getUserName()))
            cart.get((int)product.getProductId()).replace(seller.getUserName(), cart.get((int)product.getProductId()).get(seller.getUserName()) + 1);
        else
            cart.get((int)product.getProductId()).put(seller.getUserName(), 1);
    }

    public void decreaseProductCount (Product product, String sellerUserName) throws Exceptions.NoSellerByThisUserNameForProductException, Exceptions.NoSellerWithThisUserNameForThisProductInCartException {
        SellerAccount seller = product.getSellerByUsername(sellerUserName);

        if (cart.get((int)product.getProductId()).containsKey(seller.getUserName())) {
            cart.get((int)product.getProductId()).replace(seller.getUserName(), cart.get((int)product.getProductId()).get(seller.getUserName()) - 1);
            if (cart.get((int)product.getProductId()).get(seller.getUserName()) == 0) {
                cart.get((int)product.getProductId()).remove(seller.getUserName());
                if (cart.get((int)product.getProductId()).isEmpty())
                    cart.remove((int)product.getProductId());
            }
        } else
            throw new Exceptions.NoSellerWithThisUserNameForThisProductInCartException();
    }

    public int getTotalPrice () throws Exceptions.EmptyCartException {
        int totalPrice = 0;
        for (Product product : getProducts()) {
            for (String seller : cart.get((int)product.getProductId()).keySet()) {
                try {
                    totalPrice += (int) (cart.get((int)product.getProductId()).get(seller) * product.getPrice() *
                            product.getOff(SellerAccount.getSellerAccountByUsername(seller))) / 100;
                } catch (Exceptions.NoOffForThisProductException e) {
                    totalPrice += cart.get((int)product.getProductId()).get(seller) * product.getPrice();
                } catch (Exception ignored) {}
            }
        }
        return totalPrice;
    }

    public void checkCartForPurchase () throws Exception {
        for (int product : cart.keySet()) {
            for (String seller : cart.get(product).keySet()) {

                if (!SellerAccount.getSellerAccountByUsername(seller).hasEnoughOfProduct(Shop.getInstance().getProductById(product),
                        cart.get(product).get(seller)))
                    throw new Exceptions.NotEnoughProductToPurchaseException(product, seller,
                            SellerAccount.getSellerAccountByUsername(seller).getCountOfProduct(Shop.getInstance().getProductById(product)));
            }
        }
    }

    public int getDiscountAmount (CustomerAccount customer, String discountCode) throws Exceptions.NoDiscountByCodeException, Exceptions.NotUsableDiscountCodeException {
        Discount discount = customer.getDiscountByCode(discountCode);
        int totalPrice = 0;
        try {
            totalPrice = getTotalPrice();
        } catch (Exceptions.EmptyCartException ignored) {}

        if (discount.isUsable()) {
            return Math.min((int) ((totalPrice * discount.getDiscountPercent()) / 100), discount.getDiscountAmountLimit());
        }
        throw new Exceptions.NotUsableDiscountCodeException();
    }

    public void buyCart (CustomerAccount customer, int payablePrice, int discountAmount, String[] receiverInfo) throws Exceptions.NotEnoughCreditException {
        if (customer.getCredit() < payablePrice)
            throw new Exceptions.NotEnoughCreditException();

        for (int product : cart.keySet()) {
            for (String seller : cart.get(product).keySet()) {
                SellLog sellLog = null;
                try {
                    sellLog = new SellLog(Shop.getInstance().getAllSellLogs().size() + 1, payablePrice + discountAmount,
                            Shop.getInstance().getProductByIdd(product).getOff(SellerAccount.getSellerAccountByUsername(seller)),
                            Shop.getInstance().getProductByIdd(product), cart.get(product).get(seller),
                            customer.getUserName(), receiverInfo[0], receiverInfo[1],
                            receiverInfo[2]);
                } catch (Exceptions.NoOffForThisProductException e) {
                    try {
                        sellLog = new SellLog(Shop.getInstance().getAllSellLogs().size() + 1, payablePrice + discountAmount,
                                0, Shop.getInstance().getProductByIdd(product), cart.get(product).get(seller), customer.getUserName(), receiverInfo[0], receiverInfo[1],
                                receiverInfo[2]);
                    } catch (Exception ignored) {}
                } catch (Exception ignored) {}
                try {
                    SellerAccount.getSellerAccountByUsername(seller).sellSellLog(sellLog);
                } catch (Exception ignored) {}
                Shop.getInstance().addSellLog(sellLog);
            }
        }

        BuyLog buyLog = new BuyLog(Shop.getInstance().getAllBuyLogs().size() + 1, payablePrice, discountAmount,
                                    cart, receiverInfo[0], receiverInfo[1], receiverInfo[2]);
        customer.purchaseBuyLog(buyLog);
        Shop.getInstance().addBuyLog(buyLog);
    }

    public HashMap<Integer, HashMap<String, Integer>> getCart() {
        return cart;
    }
}
