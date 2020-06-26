package View.Pages.AccountsPage;

import Controller.AccountPagesController.AccountPageController;
import Controller.AccountPagesController.CustomerPageController;
import Controller.AccountPagesController.ManagerPageController;
import Controller.AccountPagesController.SellerPageController;
import Controller.Exceptions;
import Model.Accounts.SellerAccount;
import Model.Category;
import Model.Product;
import Model.Shop;
import View.Commands;
import View.Page;
import View.Pages.AllProductsPage;
import View.Pages.CartPage;
import View.Pages.LoginRegisterPage;
import View.Pages.OffsPage;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.regex.Matcher;

public class SellerPage extends Page {
    private static SellerPage sellerPage = new SellerPage();
    private SellerPageController controller;


    private SellerPage() {
        this.controller = SellerPageController.getInstance();
    }

    public static SellerPage getInstance() {
        return sellerPage;
    }

    public Page run() {
        Page.pagesHistory.add(this);

        System.out.println("seller page");

        String input;
        Matcher matcher;
        while (!Commands.EXIT.getMatcher(input = scanner.nextLine().trim()).matches()) {
            if (Commands.VIEW_PERSONAL_INFO.getMatcher(input).matches()) {
                viewPersonalInfo();
            } else if (Commands.VEIW_COMPANY_INFORMATION.getMatcher(input).matches()) {
                viewCompanyInfo();
            } else if (Commands.VIEW_SALES_HISTORY.getMatcher(input).matches()) {
                viewSalesHistory();
            } else if (Commands.MANAGE_PRODUCTS.getMatcher(input).matches()) {
                manageProducts();
            } else if (Commands.ADD_PRODUCT.getMatcher(input).matches()) {
                addProduct();
            } else if ((matcher = Commands.REMOVE_PRODUCT.getMatcher(input)).matches()) {
                removeProduct(Integer.parseInt(matcher.group(1)));
            } else if (Commands.SHOW_CATEGORIES.getMatcher(input).matches()) {
                showCategories();
            } else if (Commands.VIEW_OFFS.getMatcher(input).matches()) {
                viewOffs();
            } else if (Commands.VIEW_BALANCE.getMatcher(input).matches()) {
                viewBalance();
            } else if (Commands.VIEW_OFFS.getMatcher(input).matches()) {
                viewOffs();
            } else if (Commands.HELP.getMatcher(input).matches()) {
                help();
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
                help();
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

    private void viewCompanyInfo() {
        System.out.println(controller.getComponyinfo());
    }

    private void manageProducts() {
        System.out.println(controller.showSellerProduct());
        String input;
        while (!Commands.BACK.getMatcher(input = scanner.nextLine().trim()).matches()) {
            Matcher matcher;
            if ((matcher = Commands.VIEW_PRODUCT_BYID.getMatcher(input)).matches()) {
                try {
                    System.out.println(controller.viewProduct(Integer.parseInt(matcher.group(1))));
                } catch (Exceptions.NoProductByThisIdException e) {
                    System.out.println(e.getMessage());
                }
            } else if ((matcher = Commands.EDIT_PRODUCT_BYID.getMatcher(input)).matches()) {


                SellerAccount sellerAccount = (SellerAccount) controller.getUser();
                String name = null;
                int id = Integer.parseInt(matcher.group(1));
                ;
                int count = 0;
                String brand = null;
                double price = 0;
                String categoryName;
                Category category = null;
                String description = null;
                String attribute = null;
                try {
                    Shop.getInstance().getProductById(id);
                } catch (Exceptions.NoProductByThisIdException e) {
                    System.out.println(e.getMessage());
                    return;

                }
                System.out.println("please enter product name");
                name = scanner.nextLine().trim();
                System.out.println("please enter product count");
                count = scanner.nextInt();
                System.out.println("please enter product brand");
                brand = scanner.nextLine();
                System.out.println("please enter product Price");
                price = scanner.nextDouble();
                System.out.println("please enter product category");
                categoryName = scanner.next();
                try {
                    category = Shop.getInstance().getCategoryByName(categoryName);
                } catch (Exceptions.NoCategoryException e) {
                    System.out.println(e.getMessage());
                    return;
                }
                System.out.println("please inter product description");
                description = scanner.nextLine();
                System.out.println("please inter product attribute");
                attribute = scanner.nextLine();
                try {
                    controller.editProduct(sellerAccount, name, id, count, brand, price, category, description, attribute);
                } catch (Exceptions.NoCategoryException e) {
                    System.out.println(e.getMessage());
                }

            } else if ((matcher = Commands.VIEW_BUYERS.getMatcher(input)).matches()) {
                try {
                    controller.viewBuyers(Integer.parseInt(matcher.group(1)));
                } catch (Exceptions.NoProductByThisIdException e) {
                    System.out.println(e.getMessage());
                }
            } else if (Commands.HELP.getMatcher(input).matches()) {
                System.out.println("back\n" +
                        "help\n" +
                        "view [productId]\n" +
                        "view buyers [productId]\n" +
                        "edit [productId]\n");
            }
        }

    }

    private void addProduct() {
        SellerAccount sellerAccount = (SellerAccount) controller.getUser();
        String name = null;
        int id = 0;
        int count = 0;
        String brand = null;
        double price = 0;
        String categoryName;
        Category category = null;
        String descrption = null;
        String arrtibute = null;

        System.out.println("please enter product name");
        name = scanner.nextLine().trim();

        for (Product product : Shop.getInstance().getAllProducts()) {
            if (product.getName().equals(name)) {
                System.out.println("a product with this name exists :\n" +
                        product.getProductId() + " | " + product.getDescription() + " | " + product.getAttribute() + " | " +
                        product.getCategory().getName() + " | " + product.getAverageRate() + " | " + product.getPrice() +
                        "\nif you want to add the same product print yes if not print no and try again with another name.");
                if (!scanner.nextLine().trim().equalsIgnoreCase("yes"))
                    return;
                try {
                    controller.addProduct(false, sellerAccount, name, id, count, brand, price, Shop.getInstance().getAllGoodsCaregory(), arrtibute,descrption);
                } catch (Exceptions.NoCategoryException e) {
                    e.printStackTrace();
                }
            }
        }

        System.out.println("please enter product count");
        count = scanner.nextInt();
        scanner.nextLine();
        System.out.println("please enter product brand");
        brand = scanner.nextLine();
        System.out.println("please enter product Price");
        price = scanner.nextDouble();
        scanner.nextLine();
        System.out.println("please enter product category");
        categoryName = scanner.nextLine();
        try {
            category = Shop.getInstance().getCategoryByName(categoryName);

        } catch (Exceptions.NoCategoryException e) {
            category = Shop.getInstance().getAllGoodsCaregory();
            System.out.println(e.getMessage());
        }

        System.out.println("please inter product description");
        descrption = scanner.nextLine();
        System.out.println("please inter product attribute");
        arrtibute = scanner.nextLine();

        try {
            controller.addProduct(true, sellerAccount, name, id, count, brand, price, category, descrption, arrtibute);
            System.out.println("your product added");
        } catch (Exceptions.NoCategoryException e) {
            System.out.println(e.getMessage());
        }
    }


    private void removeProduct(int id) {
        try {
            controller.removeProduct(id);
        } catch (Exceptions.NoProductByThisIdException e) {
            System.out.println(e.getMessage());
        }
    }

    private void showCategories() {
        try {
            controller.showCategories();
        } catch (Exception e) {
            System.out.println("there is no categories");
        }
    }

    private void viewBalance() {
        System.out.println(controller.showBalance());
    }

    private void viewSalesHistory() {
        System.out.println(controller.showSaleHistory());
    }

    private void help() {
        System.out.println("view personal information\n" + "view compony information\n" + "manage products\n" +
                "add product\n" + "remove product\n" + "show categories\n" + "view offs\n" + "view balance\n" + "view sales history\n"+"help\n" +
                "login page\n" +
                "log out\n" +
                "products page\n" +
                "offs page\n" +
                "cart page"

        );
    }

    private void viewOffs() {
        Matcher matcher;
        String input=new String();
        while (!Commands.BACK.getMatcher(input).matches()) {
            System.out.println(controller.showOff());
            input=scanner.nextLine();
            if (input.equalsIgnoreCase("help"))
                System.out.println("add off\nedit off\nback");
            if ((Commands.ADD_OFF.getMatcher(input)).matches()) {
                ArrayList<Product> listOfProduct = new ArrayList<>();
                    while (!input.equalsIgnoreCase("done")) {
                        System.out.println(controller.showSellerProduct());
                        System.out.println("enter your productsId then done");
                        input=scanner.nextLine();
                        if (input.equalsIgnoreCase("done"))
                            break;
                            try {
                                SellerAccount seller = (SellerAccount) AccountPageController.getUser();
                                listOfProduct.add(seller.hasProduct(Integer.parseInt(input)));
                            } catch (Exceptions.NoProductByThisIdException e) {
                                System.out.println(e.getMessage());
                            }

                    }
                    System.out.println("enter start time year");
                    int year = Integer.parseInt(scanner.nextLine());
                    System.out.println("enter start time month");
                    int month = Integer.parseInt(scanner.nextLine());
                    System.out.println("enter start time day");
                    int day = Integer.parseInt(scanner.nextLine());
                    System.out.println("enter start time hour");
                    int hour = Integer.parseInt(scanner.nextLine());
                    System.out.println("enter start time minute");
                    int minute = Integer.parseInt(scanner.nextLine());
                    System.out.println("enter start time second");
                    int second = Integer.parseInt(scanner.nextLine());
                    GregorianCalendar start = new GregorianCalendar(year, month - 1, day, hour, minute, second);
                    System.out.println("enter end time year");
                    year = Integer.parseInt(scanner.nextLine());
                    System.out.println("enter end time month");
                    month = Integer.valueOf(scanner.nextLine());
                    System.out.println("enter end time day");
                    day = Integer.valueOf(scanner.nextLine());
                    System.out.println("enter end time hour");
                    hour = Integer.valueOf(scanner.nextLine());
                    System.out.println("enter end time minute");
                    minute = Integer.valueOf(scanner.nextLine());
                    System.out.println("enter end time second");
                    second = Integer.valueOf(scanner.nextLine());
                    GregorianCalendar end = new GregorianCalendar(year, month - 1, day, hour, minute, second);
                    System.out.println("enter percentage");
                    double persentage = Double.valueOf(scanner.nextLine());
                    controller.createOffRequest( listOfProduct, start, end,persentage);
                    System.out.println("successfully created");

            }
            if ((matcher = Commands.EDIT_OFF.getMatcher(input)).matches()) {
                int id;
                if (Shop.getInstance().isOffById(id = Integer.valueOf(matcher.group(1)))) {
                    ArrayList<Product> listofProduct = new ArrayList<>();
                    while (Commands.BACK.getMatcher(input = scanner.nextLine()).matches()) {
                        System.out.println("enter your productId");
                        while (Commands.BACK.getMatcher(input = scanner.nextLine()).matches()) {

                            try {
                                SellerAccount seller = (SellerAccount) AccountPageController.getUser();
                                listofProduct.add(seller.hasProduct(Integer.valueOf(input)));
                            } catch (Exceptions.NoProductByThisIdException e) {
                                System.out.println(e.getMessage());
                            }
                        }

                        System.out.println("enter start time year");
                        int year = Integer.parseInt(scanner.nextLine());
                        System.out.println("enter start time month");
                        int month = Integer.valueOf(scanner.nextLine());
                        System.out.println("enter start time day");
                        int day = Integer.valueOf(scanner.nextLine());
                        System.out.println("enter start time hour");
                        int hour = Integer.valueOf(scanner.nextLine());
                        System.out.println("enter start time minute");
                        int minute = Integer.valueOf(scanner.nextLine());
                        System.out.println("enter start time second");
                        int second = Integer.valueOf(scanner.nextLine());
                        GregorianCalendar start = new GregorianCalendar(year, month - 1, day, hour, minute, second);
                        System.out.println("enter end time year");
                        year = Integer.parseInt(scanner.nextLine());
                        System.out.println("enter end time month");
                        month = Integer.valueOf(scanner.nextLine());
                        System.out.println("enter end time day");
                        day = Integer.valueOf(scanner.nextLine());
                        System.out.println("enter end time hour");
                        hour = Integer.valueOf(scanner.nextLine());
                        System.out.println("enter end time minute");
                        minute = Integer.valueOf(scanner.nextLine());
                        System.out.println("enter end time second");
                        second = Integer.valueOf(scanner.nextLine());
                        GregorianCalendar end = new GregorianCalendar(year, month - 1, day, hour, minute, second);
                        System.out.println("enter persentage");
                        double persentage = Double.valueOf(scanner.nextLine());
                        try {
                            controller.editOffRequest(id,listofProduct,start,end,persentage);
                            Shop.getInstance().getOffById(id).changeWaitingState();
                        } catch (Exceptions.NoOffByThisId noOffByThisId) {
                            noOffByThisId.printStackTrace();
                        }
                    }
                } else {
                    System.out.println("no off by this id");
                }
            }
        }
    }

}
