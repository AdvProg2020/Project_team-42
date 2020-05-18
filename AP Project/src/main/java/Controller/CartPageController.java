package Controller;

import Controller.AccountPagesController.AccountPageController;
import Model.Accounts.CustomerAccount;
import Model.Accounts.SellerAccount;
import Model.Discount;
import Model.Product;
import Model.Shop;

import java.util.HashMap;
import java.util.Set;

public class CartPageController {
    private static CartPageController cartPageController = new CartPageController();
    private Shop shop;
    private HashMap<Product, HashMap<SellerAccount, Integer>> cart;

    private CartPageController () {
        this.shop = Shop.getInstance();
        cart = new HashMap<>();
    }

    public static CartPageController getInstance() {
        return cartPageController;
    }

    public void addProductToCart (SellerAccount seller, Product selectedProduct) throws Exceptions.NotCustomerException {
        try {
            AccountPageController.getUser().addProductToCart(selectedProduct, seller);
        } catch (Exceptions.NotLogedInException e) {
            this.cart.put(selectedProduct, new HashMap<>());
            this.cart.get(selectedProduct).put(seller, 1);
        }
    }

    public Product getCartProductById (long productId) throws Exceptions.NoProductWithThisIdInCartException {
        for (Product product : cart.keySet()) {
            if (product.getProductId() == productId)
                return product;
        }
        throw new Exceptions.NoProductWithThisIdInCartException();
    }

    public Set<Product> getProducts () throws Exceptions.EmptyCartException {
        if (cart.isEmpty())
            throw new Exceptions.EmptyCartException();
        return cart.keySet();
    }

    public HashMap<SellerAccount, Integer> getSellersAndCountOfProduct (Product product) {
        return cart.get(product);
    }

    public void increaseProductCount (Product product, String sellerUserName) throws Exceptions.NoSellerByThisUserNameForProductException {
        SellerAccount seller = product.getSellerByUsername(sellerUserName);

        if (cart.get(product).containsKey(seller))
            cart.get(product).replace(seller, cart.get(product).get(seller) + 1);
        else
            cart.get(product).put(seller, 1);
    }

    public void decreaseProductCount (Product product, String sellerUserName) throws Exceptions.NoSellerByThisUserNameForProductException, Exceptions.NoSellerWithThisUserNameForThisProductInCartException {
        SellerAccount seller = product.getSellerByUsername(sellerUserName);

        if (cart.get(product).containsKey(seller)) {
            cart.get(product).replace(seller, cart.get(product).get(seller) - 1);
            if (cart.get(product).get(seller) == 0) {
                cart.get(product).remove(seller);
                if (cart.get(product).isEmpty())
                    cart.remove(product);
            }
        } else
            throw new Exceptions.NoSellerWithThisUserNameForThisProductInCartException();
    }

    public int getTotalPrice () throws Exceptions.EmptyCartException {
        int totalPrice = 0;
        for (Product product : getProducts()) {
            for (SellerAccount seller : cart.get(product).keySet()) {
                try {
                    totalPrice += (int) (cart.get(product).get(seller) * product.getPrice() * product.getOff(seller)) / 100;
                } catch (Exceptions.NoOffForThisProductException e) {
                    totalPrice += cart.get(product).get(seller) * product.getPrice();
                }
            }
        }
        return totalPrice;
    }

    public void checkCartForPurchase () throws Exceptions.NotEnoughProductToPurchaseException {
        for (Product product : cart.keySet()) {
            for (SellerAccount seller : cart.get(product).keySet()) {
                if (!seller.hasEnoughOfProduct(product, cart.get(product).get(seller)))
                    throw new Exceptions.NotEnoughProductToPurchaseException(product, seller, seller.getCountOfProduct(product));
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
}
