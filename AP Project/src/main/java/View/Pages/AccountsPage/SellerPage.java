package View.Pages.AccountsPage;

import Controller.AccountPagesController.SellerPageController;
import Controller.Exceptions;
import Model.Accounts.SellerAccount;
import Model.Category;
import Model.Shop;
import View.Commands;
import View.Page;

import java.util.regex.Matcher;

public class SellerPage extends Page {
    private static SellerPage sellerPage = new SellerPage();
    private SellerPageController controller;
    private Shop shop = Shop.getInstance();

    private SellerPage () {
        this.controller = SellerPageController.getInstance();
    }

    public static SellerPage getInstance() {
        return sellerPage;
    }

    public Page run() {
        String input;
        Matcher matcher;
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
            } else if ((matcher = Commands.REMOVE_PRODUCT.getMatcher(input)).matches()) {
                removeProduct(Integer.valueOf(matcher.group(1)));
            } else if (Commands.SHOW_CATEGORIES.getMatcher(input).matches()) {
                showCategories();
            } else if (Commands.VIEW_OFFS.getMatcher(input).matches()) {
                viewOffs();
            } else if (Commands.VIEW_BALANCE.getMatcher(input).matches()) {
                viewBalance();
            }else if (Commands.VIEW_OFFS.getMatcher(input).matches()) {
                viewOffs();
            } else if (Commands.HELP.getMatcher(input).matches()) {
                help();
            }else if(Commands.OFF.getMatcher(input).matches()){
                //todo go to off page
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

    private void manageProducts() {
        controller.showSellerProduct();
    }

    private void addProduct() {
        String input;
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

       while(!Commands.BACK.getMatcher(input = scanner.next().trim()).matches()){

           System.out.println("please inter product name");
           name=scanner.nextLine().trim();//todo
           id = shop.returnNewId();
           System.out.println("please inter product count");
           count = scanner.nextInt();
           System.out.println("please inter product brand");
           brand = scanner.nextLine();
           System.out.println("please inter product Pricee");
           price = scanner.nextDouble();
           System.out.println("please inter product category");
           categoryName = scanner.next();
           System.out.println("please inter product name");
           try {
               category = shop.getCategoryByName(categoryName);
           } catch (Exceptions.NoCategoryException e) {
               System.out.println(e.getMessage());
               return;
           }
           System.out.println("please inter product name");
           descrption = scanner.nextLine();
           System.out.println("please inter product name");
           arrtibute = scanner.nextLine();
       }
        try {
            controller.addProduct(sellerAccount, name, id, count , brand, price, category, descrption, arrtibute);
        } catch (Exceptions.NoCategoryException ignored) {}
    }


    private void removeProduct(int id) {
        try {
        controller.removeProduct(id);
        } catch (Exceptions.NoProductByThisIdException e) {
            System.out.println(e.getMessage());
        }
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

 private void viewOffs() {
        Matcher matcher;
        String input;
        controller.showOff();
        while(!Commands.BACK.getMatcher(input = scanner.nextLine()).matches()){
            if((matcher = Commands.VIEW_OFF.getMatcher(input)).matches()){
                try {
                    System.out.println(shop.getOffById(Integer.valueOf(matcher.group(1))));
                } catch (Exceptions.NoOffByThisId noOffByThisId) {
                    System.out.println(noOffByThisId.getMessage());
                }
            }if((Commands.ADD_OFF.getMatcher(input)).matches()){
                ArrayList<Product>listofProduct = new ArrayList<>();
                while(Commands.BACK.getMatcher(input = scanner.nextLine()).matches()) {
                    System.out.println("enter your productId");
                    while(Commands.BACK.getMatcher(input = scanner.nextLine()).matches()){
                        try {
                            listofProduct.add(user.hasProduct(Integer.valueOf(input)));
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
                    GregorianCalendar start = new GregorianCalendar(year,month -1,day,hour,minute,second);
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
                    GregorianCalendar end = new GregorianCalendar(year,month -1,day,hour,minute,second);
                    System.out.println("enter persentage");
                    double persentage = Double.valueOf(scanner.nextLine());
                    new CreateOffRequest(false,null,listofProduct,start,end,persentage);
                }
            }if((matcher = Commands.EDIT_OFF.getMatcher(input)).matches()){
                int id;
                if(shop.isOffById(id = Integer.valueOf(matcher.group(1)))){
                    ArrayList<Product>listofProduct = new ArrayList<>();
                    while(Commands.BACK.getMatcher(input = scanner.nextLine()).matches()) {
                        System.out.println("enter your productId");
                        while(Commands.BACK.getMatcher(input = scanner.nextLine()).matches()){

                            try {
                                listofProduct.add(user.hasProduct(Integer.valueOf(input)));
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
                        GregorianCalendar start = new GregorianCalendar(year,month -1,day,hour,minute,second);
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
                        GregorianCalendar end = new GregorianCalendar(year,month -1,day,hour,minute,second);
                        System.out.println("enter persentage");
                        double persentage = Double.valueOf(scanner.nextLine());
                        try {
                            new CreateOffRequest(true,shop.getOffById(id),listofProduct,start,end,persentage);
                        } catch (Exceptions.NoOffByThisId noOffByThisId) {
                            noOffByThisId.printStackTrace();
                        }
                    }
                }else{
                    System.out.println("no off by this id");
                }
            }
        }
    }

}
