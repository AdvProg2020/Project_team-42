package View.Pages.AccountsPage;

import Controller.AccountPagesController.SellerPageController;
import Controller.Exceptions;
import View.Commands;
import View.Page;

import java.util.regex.Matcher;

public class SellerPage extends Page {
    private static SellerPage sellerPage = new SellerPage();
    private SellerPageController controller;

    private SellerPage () {
        this.controller = SellerPageController.getInstance();
    }

    public static SellerPage getInstance() {
        return sellerPage;
    }

    public Page run()  {
        String input;
        boolean isEnd;
        while (!Commands.EXIT.getMatcher(input = scanner.nextLine().trim()).matches()) {
            if (Commands.VIEW_PERSONAL_INFO.getMatcher(input).matches()) {
                viewPersonalInfo();
            } else if (Commands.VEIW_COMPANY_INFORMATION.getMatcher(input).matches()) {
                viewComponyInfo();
            } else if (Commands.VIEW_SALES_HISTORY.getMatcher(input).matches()) {
                viewSalesHistory();
            } else if (Commands.MANAGE_PRODUCTS.getMatcher(input).matches()) {
                manageProducts();
            } else if (Commands.ADD_PRODUCT.getMatcher(input).matches()) {
                addProduct();
            } else if (Commands.REMOVE_PRODUCT.getMatcher(input).matches()) {
                removeProduct();//todo
            } else if (Commands.SHOW_CATEGORIES.getMatcher(input).matches()) {
                showCategories();
            } else if (Commands.VIEW_OFFS.getMatcher(input).matches()) {
                viewOffs();
            } else if (Commands.VIEW_BALANCE.getMatcher(input).matches()) {
                viewBalance();
            }else if (Commands.HELP.getMatcher(input).matches()) {
                help();
            }else if(Commands.OFF.getMatcher(input).matches()){

            }else{
                System.out.println("invalid command");
            }
        }

        return null;
    }

    private void viewPersonalInfo() {
        System.out.println(controller.getPersonalInformation());

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
            }
        }
    }

    private void editPersonalInfo(String field) throws Exceptions.InvalidFormatException, Exceptions.InvalidFieldException {
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

    private void viewComponyInfo() {
        System.out.println(controller.getComponyinfo());
    }
    //ToDo

    private void manageProducts() {
        controller.showSellerProduct();
    }

    private void addProduct() {
        controller.addProduct();
    }

    private void removeProduct() {
        controller.removeProduct();
    }

    private void showCategories()  {
        controller.showCategories();
    }

    private void viewOffs() {
        controller.showOff();
    }

    private void viewBalance() {
        System.out.println(controller.showBalance());
    }

    private void viewSalesHistory(){
        controller.showSaleHistory();
    }

    private void help() {
        System.out.println("view personal information" + "view compony information" + "manage products" + "add product" + "remove product" + "show categories" + "view offs" + "view balance" + "help");
    }



}
