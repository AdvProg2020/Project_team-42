package View.Pages;

import Controller.AccountPagesController.AccountPageController;
import Controller.AccountPagesController.CustomerPageController;
import Controller.AccountPagesController.ManagerPageController;
import Controller.AccountPagesController.SellerPageController;
import Controller.CartPageController;
import Controller.Exceptions;
import Model.Accounts.CustomerAccount;
import Model.Accounts.ManagerAccount;
import Model.Accounts.SellerAccount;
import Model.Product;
import View.Commands;
import View.Page;
import View.Pages.AccountsPage.CustomerPage;
import View.Pages.AccountsPage.ManagerPage;
import View.Pages.AccountsPage.SellerPage;

import java.util.HashMap;
import java.util.regex.Matcher;

public class CartPage extends Page {
    private static CartPage cartPage = new CartPage();
    private CartPageController controller;

    private CartPage () {
        this.controller = CartPageController.getInstance();
    }

    public static CartPage getInstance() {
        return cartPage;
    }

    public Page run() {
        String input;
        Matcher matcher;

        Page.pagesHistory.add(this);

        System.out.println("cart page");

        while (!Commands.EXIT.getMatcher(input = scanner.nextLine()).matches()) {
            if (Commands.SHOW_PRODUCTS.getMatcher(input).matches())
                showCart();
            else if ((matcher = Commands.INCREASE_PRODUCT.getMatcher(input)).matches())
                increaseProduct(Long.parseLong(matcher.group(1)));
            else if ((matcher = Commands.DECREASE_PRODUCT.getMatcher(input)).matches())
                decreaseProduct(Long.parseLong(matcher.group(1)));
            else if (Commands.SHOW_TOTAL_PRICE.getMatcher(input).matches())
                showTotalPrice();
            else if (Commands.PURCHASE.getMatcher(input).matches())
                purchase();
            else if (Commands.HELP.getMatcher(input).matches())
                cartPageHelp();
            else if (Commands.BACK.getMatcher(input).matches()) {
                Page.pagesHistory.remove(Page.pagesHistory.size() - 1);
                return Page.pagesHistory.get(Page.pagesHistory.size() - 1);
            }
            else if ((matcher = Commands.VIEW_PRODUCT.getMatcher(input)).matches()) {
                try {
                    return prepareProductPage(Long.parseLong(matcher.group(1)));
                } catch (Exceptions.NoProductWithThisIdInCartException e) {
                    System.out.println(e.getMessage());
                }
            }  else if (Commands.ACCOUNT_PAGE.getMatcher(input).matches()) {
                if (AccountPageController.getUser() == null)
                    return LoginRegisterPage.getInstance();
                if (AccountPageController.getUser().getClass() == CustomerAccount.class)
                    return CustomerPage.getInstance();
                if (AccountPageController.getUser().getClass() == SellerAccount.class)
                    return SellerPage.getInstance();
                if (AccountPageController.getUser().getClass() == ManagerAccount.class)
                    return ManagerPage.getInstance();
            } else if (Commands.LOGIN_PAGE.getMatcher(input).matches()) {
                return LoginRegisterPage.getInstance();
            } else if (Commands.LOG_OUT.getMatcher(input).matches()) {
                AccountPageController.setUser(null);
                SellerPageController.getInstance().setUser(null);
                ManagerPageController.getInstance().setUser(null);
                CustomerPageController.getInstance().setUser(null);
                Page.pagesHistory.clear();
                return LoginRegisterPage.getInstance();
            } else if (Commands.ALL_PRODUCTS_PAGE.getMatcher(input).matches()) {
                return AllProductsPage.getInstance();
            } else if (Commands.OFFS_PAGE.getMatcher(input).matches()) {
                return OffsPage.getInstance();
            } else {
                printInvalidCommandMessage();
                cartPageHelp();
            }
        }

        return null;
    }

    private void cartPageHelp () {
        System.out.println("Valid commands in this page are:\n\tshow products\n\tview (product id)\n\tincrease (product id)" +
                "\n\tdecrease (product id)\n\t" +
                "show total price\n\tpurchase\n\thelp\n\taccount page\n\tlogin page\n\tlogout\n\tall product page\n\toffs page" +
                "\n\tback\n\texit");
    }

    private void showCart () {
        System.out.println("product id | product name         | seller               | count | final price\n" +
                "---------------------------------------------------------------------------------------");
        try {
            for (Product product : controller.getProducts()) {
                HashMap<SellerAccount, Integer> sellersAndCount = controller.getSellersAndCountOfProduct(product);
                for (SellerAccount seller : sellersAndCount.keySet()) {
                    try {
                        System.out.printf("%-10d | %-20s | %-20s | %-5d | %d", product.getProductId(), product.getName(), seller.getUserName(),
                                sellersAndCount.get(seller), (int) (product.getPrice() * sellersAndCount.get(seller) * (100 - product.getOff(seller))) / 100);
                    } catch (Exceptions.NoOffForThisProductException e) {
                        System.out.printf("%-10d | %-20s | %-20s | %-5d | %d", product.getProductId(), product.getName(), seller.getUserName(),
                                sellersAndCount.get(seller), (product.getPrice() * sellersAndCount.get(seller)));
                    }
                }
            }
        } catch (Exceptions.EmptyCartException e) {
            System.out.println(e.getMessage());
        }
    }

    private void increaseProduct (long productId) {
        try {
            Product product = controller.getCartProductById(productId);
            System.out.println("Please select a seller:");
            controller.increaseProductCount(product, scanner.nextLine().trim());
        } catch (Exceptions.NoProductWithThisIdInCartException | Exceptions.NoSellerByThisUserNameForProductException e) {
            System.out.println(e.getMessage());
        }
    }

    private void decreaseProduct (long productId) {
        try {
            Product product = controller.getCartProductById(productId);
            System.out.println("Please select a seller:");
            controller.decreaseProductCount(product, scanner.nextLine().trim());
        } catch (Exceptions.NoProductWithThisIdInCartException | Exceptions.NoSellerByThisUserNameForProductException | Exceptions.NoSellerWithThisUserNameForThisProductInCartException e) {
            System.out.println(e.getMessage());
        }
    }

    private void showTotalPrice () {
        try {
            System.out.println("Total price of your cart is " + controller.getTotalPrice() + " .");
        } catch (Exceptions.EmptyCartException e) {
            System.out.println(e.getMessage());
        }
    }

    private ProductPage prepareProductPage (long productId) throws Exceptions.NoProductWithThisIdInCartException {
        return new ProductPage (controller.getCartProductById(productId));
    }

    private void purchase () {
        try {
            CustomerAccount customer = CustomerPageController.getInstance().getCustomer();

            if (customer == null)
                throw new Exceptions.NotCustomerException();

            controller.checkCartForPurchase();

            int totalPrice = controller.getTotalPrice();

            String[] receiverInfo = getReceiverInfo();

            int discountAmount = getDiscountAmount(customer);

            controller.checkCartForPurchase();

            controller.buyCart(customer, totalPrice - discountAmount, discountAmount, receiverInfo);

        } catch (Exceptions.NotEnoughProductToPurchaseException | Exceptions.EmptyCartException | Exceptions.NotEnoughCreditException |
                    Exceptions.NotCustomerException e) {
            System.out.println(e.getMessage());
        } catch (Exception ignored) {}
    }

    private String[] getReceiverInfo () throws Exceptions.StopPurchaseException {
        String[] receiverInfo = new String[3];
        String firstName;
        String lastName;
        String phoneNumber;

        System.out.println("Please enter the receiver`s first name:");
        while (!Commands.NAMES.getMatcher(firstName = scanner.nextLine().trim()).matches()) {
            if (Commands.BACK.getMatcher(firstName).matches())
                throw new Exceptions.StopPurchaseException();
            System.out.println("The format is incorrect.");
        }
        System.out.println("Please enter the receiver`s last name:");
        while (!Commands.NAMES.getMatcher(lastName = scanner.nextLine().trim()).matches()) {
            if (Commands.BACK.getMatcher(lastName).matches())
                throw new Exceptions.StopPurchaseException();
            System.out.println("The format is incorrect.");
        }
        System.out.println("Please enter the receiver`s phone number:");
        while (!Commands.PHONE_NUMBER.getMatcher(phoneNumber = scanner.nextLine().trim()).matches()) {
            if (Commands.BACK.getMatcher(phoneNumber).matches())
                throw new Exceptions.StopPurchaseException();
            System.out.println("The format is incorrect.");
        }

        receiverInfo[0] = firstName + " " + lastName;
        receiverInfo[1] = phoneNumber;

        System.out.println("Please enter the receiver`s address:");

        receiverInfo[2] = scanner.nextLine().trim();

        return receiverInfo;
    }

    private int getDiscountAmount (CustomerAccount customer) throws Exceptions.StopPurchaseException {
        System.out.println("In order to use a discount code enter it if not just enter a white space.");
        String input;
        while ((input = scanner.nextLine().trim()).equalsIgnoreCase("")) {
            if (Commands.BACK.getMatcher(input).matches())
                throw new Exceptions.StopPurchaseException();
            try {
                return controller.getDiscountAmount(customer, input);
            } catch (Exceptions.NotUsableDiscountCodeException | Exceptions.NoDiscountByCodeException e) {
                System.out.println(e.getMessage());
            }
        }
        return 0;
    }
}
