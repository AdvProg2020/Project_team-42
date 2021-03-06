package View.Pages.AccountsPage;

import Controller.AccountPagesController.AccountPageController;
import Controller.AccountPagesController.CustomerPageController;
import Controller.AccountPagesController.ManagerPageController;
import Controller.AccountPagesController.SellerPageController;
import Controller.Exceptions;
import Model.Discount;
import Model.Logs.BuyLog;
import View.Commands;
import View.Page;
import View.Pages.AllProductsPage;
import View.Pages.CartPage;
import View.Pages.LoginRegisterPage;
import View.Pages.OffsPage;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.regex.Matcher;

public class CustomerPage extends Page {
    private static CustomerPage customerPage = new CustomerPage();
    private CustomerPageController controller;

    private CustomerPage () {
        this.controller = CustomerPageController.getInstance();
    }

    public static CustomerPage getInstance() {
        return customerPage;
    }

    public Page run() {
        String input;

        Page.pagesHistory.add(this);

        System.out.println("customer page");

        while (!Commands.EXIT.getMatcher(input = scanner.nextLine().trim()).matches()) {
            if (Commands.VIEW_PERSONAL_INFO.getMatcher(input).matches())
                viewPersonalInfo();
            else if (Commands.VIEW_CART.getMatcher(input).matches())
                return CartPage.getInstance();
            else if (Commands.VIEW_ORDERS.getMatcher(input).matches())
                viewOrders();
            else if (Commands.VIEW_BALANCE.getMatcher(input).matches())
                viewBalance();
            else if (Commands.VIEW_DISCOUNT_CODES.getMatcher(input).matches())
                viewDiscountCodes();
            else if (Commands.HELP.getMatcher(input).matches())
                customerPageHelp();
            else if (Commands.BACK.getMatcher(input).matches()) {
                Page.pagesHistory.remove(Page.pagesHistory.size() - 1);
                return Page.pagesHistory.get(Page.pagesHistory.size() - 1);
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
            } else if (Commands.CART_PAGE.getMatcher(input).matches()) {
                return CartPage.getInstance();
            } else {
                printInvalidCommandMessage();
                customerPageHelp();
            }
        }

        return null;
    }

    private void customerPageHelp () {
        System.out.println("Valid commands in this page are:\n\tview personal info\n\tview cart\n\tview orders\n\tview balance\n\tview discount codes\n\thelp\n\tback\n\texit");
    }

    private void viewPersonalInfo () {
        System.out.println(controller.getPersonalInfo());

        String input;
        Matcher matcher;

        while (!Commands.BACK.getMatcher(input = scanner.nextLine().trim()).matches()) {
            if ((matcher = Commands.EDIT_PERSONAL_INFO.getMatcher(input)).matches()) {
                try {
                    editPersonalInfo(matcher.group(1));
                } catch (Exceptions.InvalidFormatException | Exceptions.InvalidFieldException e) {
                    System.out.println(e.getMessage());
                    System.out.println("The changeable fields are : first name, last name, email, phone number, and password");
                }
            } else if (Commands.HELP.getMatcher(input).matches())
                personalInfoHelp();
            else {
                printInvalidCommandMessage();
                personalInfoHelp();
            }
        }
    }

    private void personalInfoHelp () {
        System.out.println("Valid commands in this page are:\n\tedit personal info\n\thelp\n\tback");
    }

    private void editPersonalInfo (String field) throws Exceptions.InvalidFormatException, Exceptions.InvalidFieldException {
        String input;

        if (field.equalsIgnoreCase("first name") || field.equalsIgnoreCase("last name")) {
            if (Commands.NAMES.getMatcher(input = scanner.nextLine().trim()).matches())
                controller.editPersonalInfo(field, input);
            else throw new Exceptions.InvalidFormatException();
        } else if (field.equalsIgnoreCase("email")) {
            if (Commands.EMAIL.getMatcher(input = scanner.nextLine().trim()).matches())
                controller.editPersonalInfo(field, input);
            else throw new Exceptions.InvalidFormatException();
        } else if (field.equalsIgnoreCase("phone number")) {
            if (Commands.PHONE_NUMBER.getMatcher(input = scanner.nextLine().trim()).matches())
                controller.editPersonalInfo(field, input);
            else throw new Exceptions.InvalidFormatException();
        } else if (field.equalsIgnoreCase("password")) {
            if (Commands.PASSWORD.getMatcher(input = scanner.nextLine().trim()).matches())
                controller.editPersonalInfo(field, input);
            else throw new Exceptions.InvalidFormatException();
        } else throw new Exceptions.InvalidFieldException();
        System.out.println(controller.getPersonalInfo());
    }

    private void viewOrders () {
        try {
            ArrayList<BuyLog> orders = controller.getOrders();
            System.out.println("order ID | date and time       | State     \n" +
                               "---------|---------------------|-------------");
            for (BuyLog order : orders) {
                System.out.printf("%-8d | %d/%02d/%02d %02d:%02d:%02d | %-10s\n", order.getBuyLogId(), order.getDate().get(Calendar.YEAR),
                        order.getDate().get(Calendar.MONTH) + 1, order.getDate().get(Calendar.DAY_OF_MONTH), order.getDate().get(Calendar.HOUR),
                        order.getDate().get(Calendar.MINUTE), order.getDate().get(Calendar.SECOND), order.getState());
            }

            String input;
            Matcher matcher;

            while (Commands.EXIT.getMatcher(input = scanner.nextLine().trim()).matches()) {
                if ((matcher = Commands.SHOW_ORDER.getMatcher(input)).matches())
                    viewOrder(Long.parseLong(matcher.group(1)));
                else if ((matcher = Commands.RATE_PRODUCT.getMatcher(input)).matches())
                    rateProduct(Long.parseLong(matcher.group(1)), Integer.parseInt(matcher.group(2)));
                else if (Commands.HELP.getMatcher(input).matches())
                    helpOrders();
                else if (Commands.BACK.getMatcher(input).matches())
                    return;
                else {
                    printInvalidCommandMessage();
                    helpOrders();
                }
            }
        } catch (Exceptions.NoOrderException e) {
            System.out.println(e.getMessage());
        }
    }

    private void helpOrders () {
        System.out.println("Valid commands in this page are:\n\tshow order (order id)\n\trate (product id) (rate(from 1 to 5))\n\thelp\n\tback\n\texit");
    }

    private void viewOrder (long orderId) {
        try {
            BuyLog order = controller.getBuyLogById(orderId);
            System.out.println(order.toString());
        } catch (Exceptions.NoBuyLogByIdException e) {
            System.out.println(e.getMessage());
        }
    }

    private void rateProduct(long productId, int rate) {
        try {
            controller.rateProduct(productId, rate);
        } catch (Exceptions.NotBoughtProductByIdException | Exceptions.RateOutOfRangeException e) {
            System.out.println(e.getMessage());
        }
    }

    private void viewBalance () {
        System.out.println("Your balance is " + controller.getBalance() + " units.");
    }

    private void viewDiscountCodes () {
        try {
            HashMap<Discount, Integer> discountCodesAndUseCount = controller.getDiscountCodesAndUseCounts();
            System.out.println("discount code | begin date and time | end date and time   | discount percent | discount amount limit | remaining usages\n" +
                               "--------------|---------------------|---------------------|------------------|-----------------------|-----------------");
            for (Discount discount : discountCodesAndUseCount.keySet()) {
                System.out.printf("%-13s | %d/%02d/%02d %02d:%02d:%02d | %d/%02d/%02d %02d:%02d:%02d | %-16d | %-21d | %-16d\n",
                        discount.getDiscountCode(), discount.getBegin().get(Calendar.YEAR), discount.getBegin().get(Calendar.MONTH) + 1,
                        discount.getBegin().get(Calendar.DAY_OF_MONTH), discount.getBegin().get(Calendar.HOUR), discount.getBegin().get(Calendar.MINUTE),
                        discount.getBegin().get(Calendar.SECOND), discount.getEnd().get(Calendar.YEAR), discount.getEnd().get(Calendar.MONTH) + 1,
                        discount.getEnd().get(Calendar.DAY_OF_MONTH), discount.getEnd().get(Calendar.HOUR), discount.getEnd().get(Calendar.MINUTE),
                        discount.getEnd().get(Calendar.SECOND), discount.getDiscountPercent(), discount.getDiscountAmountLimit(),
                        discount.getRepeatCountForEachCustomer() - discountCodesAndUseCount.get(discount));
            }
        } catch (Exceptions.NoDiscountCodeException e) {
            System.out.println(e.getMessage());
        }
    }
}