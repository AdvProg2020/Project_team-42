package View.Pages.AccountsPage;

import Controller.AccountPagesController.AccountPageController;
import Controller.AccountPagesController.CustomerPageController;
import Controller.AccountPagesController.ManagerPageController;
import Controller.AccountPagesController.SellerPageController;
import Controller.Exceptions;
import Model.Accounts.CustomerAccount;
import Model.Category;
import Model.Product;
import View.Commands;
import View.Page;
import View.Pages.AllProductsPage;
import View.Pages.CartPage;
import View.Pages.LoginRegisterPage;
import View.Pages.OffsPage;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.regex.Matcher;

public class ManagerPage extends Page {
    private static ManagerPage managerPage = new ManagerPage();

    private ManagerPage() {

    }

    ManagerPageController mController = ManagerPageController.getInstance();

    public static ManagerPage getInstance() {
        return managerPage;
    }

    public Page run() {
        Page.pagesHistory.add(this);

        System.out.println("manager page");

        while (true) {
            Matcher matcher;
            String command = scanner.nextLine();
            if (Commands.EXIT.getMatcher(command).matches())
                break;
            else if (Commands.VIEW_PERSONAL_INFO.getMatcher(command).matches()) {
                viewPersonalInfo();
            } else if (Commands.MANAGE_USERS.getMatcher(command).matches()) {
                System.out.println(mController.getAllAccountsController());
                while (true) {

                    command = scanner.nextLine();
                    if ((matcher = Commands.VIEW.getMatcher(command)).matches()) {
                        String username = matcher.group(1);
                        System.out.println(mController.getAccountByUserNameController(username));
                    } else if ((matcher = Commands.DELETE_USER.getMatcher(command)).matches()) {
                        String username = matcher.group(1);
                        mController.deleteUserController(username);
                    } else if (Commands.CREATE_MANAGER_PROFILE.getMatcher(command).matches()) {
                        System.out.println("please enter your username");
                        String useru = scanner.nextLine();
                        try {
                            mController.checkUsernameIsUsedController(useru);
                        } catch (Exception e) {
                            System.out.println("username is used please enter another one");
                            useru = scanner.nextLine();
                            try {
                                mController.checkUsernameIsUsedController(useru);
                            } catch (Exception exception) {
                                System.out.println("username is used please enter another one");
                                useru = scanner.nextLine();
                                try {
                                    mController.checkUsernameIsUsedController(useru);
                                } catch (Exception ex) {
                                    System.out.println("username is used please try again");
                                    break;
                                }
                            }
                        }
                        System.out.println("please enter your firstname");
                        String userfirstname = scanner.nextLine();
                        System.out.println("please enter your lastname");
                        String userlastname = scanner.nextLine();
                        System.out.println("please enter your email");
                        String useremail = scanner.nextLine();
                        System.out.println("please enter your phone number");
                        String userphoneNumber = scanner.nextLine();
                        System.out.println("please enter your password it should be over 4 characters");
                        String userpassword = scanner.nextLine();
                        while (userpassword.length() < 5) {
                            System.out.println("please enter another password it should be over 4 characters");
                            userpassword = scanner.nextLine();
                        }
                        mController.createManagerAccountController(useru, userfirstname, userlastname, useremail, userphoneNumber, userpassword, "manager", false);
                    } else if (Commands.BACK.getMatcher(command).matches())
                        break;
                }
            } else if (Commands.MANAGE_ALL_PRODUCTS.getMatcher(command).matches()) {
                do {
                    try {
                        System.out.println(mController.showAllProductsController());
                    } catch (Exception e) {
                        e.printStackTrace();
                        System.out.println("there is no product");
                    }
                    command = scanner.nextLine();
                    if (Commands.HELP.getMatcher(command).matches())
                        System.out.println("remove [product id]" + "\n" + "back");
                    else if ((matcher = Commands.REMOVE_PRODUCT.getMatcher(command)).matches()) {
                        try {
                            mController.deleteProductController(Long.parseLong(matcher.group(1)));
                        } catch (Exceptions.NoProductByThisIdException e) {
                            System.out.println("invalid id");
                        }
                    }
                } while (!Commands.BACK.getMatcher(command).matches());
            } else if (Commands.CREATE_DISCOUNT_CODE.getMatcher(command).matches()) {
                try {
                    System.out.println(mController.showAllDiscountsController());
                } catch (Exception e) {
                    System.out.println("there is no discount built");
                }
                while (true) {
                    System.out.println("please enter discount code");
                    String discountCode = scanner.nextLine();
                    if (Commands.BACK.getMatcher(command).matches())
                        break;
                    try {
                        mController.checkValidationDiscountCodeController(discountCode);
                    } catch (Exception e) {
                        System.out.println("code is used please try again");
                        command = "back";
                    }

                    System.out.println("please enter start year");
                    int year = Integer.parseInt(scanner.nextLine());
                    System.out.println("please enter start month");
                    int month = Integer.parseInt(scanner.nextLine());
                    System.out.println("please enter start day");
                    int day = Integer.parseInt(scanner.nextLine());
                    System.out.println("please enter start hour");
                    int hour = Integer.parseInt(scanner.nextLine());
                    GregorianCalendar begin = new GregorianCalendar(year, month - 1, day, hour, 0, 0);
                    System.out.println("please enter end year");
                    year = Integer.parseInt(scanner.nextLine());
                    System.out.println("please enter end month");
                    month = Integer.parseInt(scanner.nextLine());
                    System.out.println("please enter end day");
                    day = Integer.parseInt(scanner.nextLine());
                    System.out.println("please enter end hour");
                    hour = Integer.parseInt(scanner.nextLine());
                    GregorianCalendar end = new GregorianCalendar(year, month - 1, day, hour, 0, 0);
                    System.out.println("enter discount percentage");
                    double percent = Double.parseDouble(scanner.nextLine());
                    while (percent > 100.00 || percent <= 0) {
                        System.out.println("enter a valid discount percentage");
                        percent = Double.parseDouble(scanner.nextLine());
                    }
                    System.out.println("enter discount amount limit");
                    int amountLimit = Integer.parseInt(scanner.nextLine());
                    System.out.println("enter discount repeat for each customer");
                    int reapeatLimit = Integer.parseInt(scanner.nextLine());
                    System.out.println("enter  customers then type done");
                    HashMap<CustomerAccount, Integer> customers = new HashMap<CustomerAccount, Integer>();
                    String customer = "";
                    while (!customer.equals("done")) {
                        customer = scanner.nextLine();
                        if (customer.equals("done"))
                            break;
                        try {
                            customers.put(mController.getCustomerAccountAccountTypeByUsernameController(customer), 0);
                        } catch (Exception e) {
                            System.out.println("customer not found");
                        }
                    }
                    mController.createDiscountCodeController(discountCode, begin, end, percent, amountLimit, reapeatLimit, customers);
                    System.out.println("enter new information or type back");
                }
            } else if (Commands.VIEW_DISCOUNT_CODES.getMatcher(command).matches()) {
                try {
                    mController.showAllDiscountsController();

                    while (!Commands.BACK.getMatcher(command).matches()) {

                        command = scanner.nextLine();
                        if ((matcher = Commands.VIEW_DISCOUNT_CODE.getMatcher(command)).matches()) {
                            String discountCode = matcher.group(1);
                            mController.viewDiscount(discountCode);
                        } else if ((matcher = Commands.REMOVE_DISCOUNT_CODE.getMatcher(command)).matches()) {
                            String discountCode = matcher.group(1);
                            mController.deleteDiscountController(discountCode);
                        } else if ((matcher = Commands.EDIT_DISCOUNT_CODE.getMatcher(command)).matches()) {
                            String discountCode = matcher.group(1);
                            System.out.println("please enter start year");
                            int year = Integer.parseInt(scanner.nextLine());
                            System.out.println("please enter start month");
                            int month = Integer.parseInt(scanner.nextLine());
                            System.out.println("please enter start day");
                            int day = Integer.parseInt(scanner.nextLine());
                            System.out.println("please enter start hour");
                            int hour = Integer.parseInt(scanner.nextLine());
                            GregorianCalendar begin = new GregorianCalendar(year, month - 1, day, hour, 0, 0);
                            System.out.println("please enter end year");
                            year = Integer.parseInt(scanner.nextLine());
                            System.out.println("please enter end month");
                            month = Integer.parseInt(scanner.nextLine());
                            System.out.println("please enter end day");
                            day = Integer.parseInt(scanner.nextLine());
                            System.out.println("please enter end hour");
                            hour = Integer.parseInt(scanner.nextLine());
                            GregorianCalendar end = new GregorianCalendar(year, month - 1, day, hour, 0, 0);
                            System.out.println("enter discount percentage");
                            double percent = Double.parseDouble(scanner.nextLine());
                            while (percent > 100.00 || percent <= 0) {
                                System.out.println("enter a vilid discount percentage");
                                percent = Double.parseDouble(scanner.nextLine());
                            }
                            System.out.println("enter discount amount limit");
                            int amountLimit = Integer.parseInt(scanner.nextLine());
                            System.out.println("enter discount reapeat for each customer");
                            int reapeatLimit = Integer.parseInt(scanner.nextLine());
                            System.out.println("enter  customers then type done");
                            HashMap<CustomerAccount, Integer> customers = new HashMap<CustomerAccount, Integer>();
                            String customer = "";
                            while (true) {
                                customer = scanner.nextLine();
                                if (customer.equals("done"))
                                    break;
                                else if (customer.equalsIgnoreCase("help"))
                                    System.out.println("customer name" + "\n" + "done");
                                else {
                                    try {
                                        customers.put(mController.getCustomerAccountAccountTypeByUsernameController(customer), 0);
                                    } catch (Exception e) {
                                        System.out.println("customer not found");
                                    }
                                }
                            }
                            mController.editDiscountController(discountCode, begin, end, percent, amountLimit, reapeatLimit, customers);
                        } else if (Commands.HELP.getMatcher(command).matches())
                            System.out.println("view discount code [code]" + "\n" + "edit discount code [code]" + "\n" + "remove discount code [code]");

                        else {
                            printInvalidCommandMessage();
                        }
                    }
                } catch (Exception e) {
                    System.out.println("there is no discount");
                }
            } else if (Commands.MANAGE_REQUESTS.getMatcher(command).matches()) {
                while (true) {
                    System.out.println(mController.showAllRequestsController());
                    command = scanner.nextLine();
                    if (Commands.BACK.getMatcher(command).matches())
                        break;
                    else if ((matcher = Commands.DETAILS.getMatcher(command)).matches()) {
                        int id = Integer.parseInt(matcher.group(1));
                        try {
                            System.out.println(mController.requestDetailController(id));
                        } catch (Exception e) {
                            System.out.println("request not found");
                        }
                    } else if ((matcher = Commands.ACCEPT.getMatcher(command)).matches()) {
                        int id = Integer.parseInt(matcher.group(1));
                        try {
                            mController.acceptRequest(id);
                        } catch (Exception e) {
                            System.out.println("unanswered request not found");
                            //e.printStackTrace();
                        }
                    } else if ((matcher = Commands.DECLINE.getMatcher(command)).matches()) {
                        int id = Integer.parseInt(matcher.group(1));
                        try {
                            mController.declineRequest(id);
                        } catch (Exception e) {
                            System.out.println("unanswered request not found");
                        }
                    } else if (Commands.HELP.getMatcher(command).matches())
                        System.out.println("details [id]\n" +
                                "accept [id]\n" +
                                "decline [id]\n" +
                                "help\n" +
                                "back");
                    else
                        printInvalidCommandMessage();
                }
            } else if (Commands.MANAGE_CATEGORIES.getMatcher(command).matches()) {
                while (true) {
                    try {
                        System.out.println(mController.getAllCategories());
                    } catch (Exception e) {
                        System.out.println("there is no categories");
                    }
                    command = scanner.nextLine();
                    if (Commands.BACK.getMatcher(command).matches())
                        break;
                    else if ((matcher = Commands.EDIT_CATEGORY.getMatcher(command)).matches()) {
                        String categoryName = matcher.group(1);
                        try {
                            Category a = mController.getCategoryBynameCategoryTypeMoudel(categoryName);
                        } catch (Exception e) {
                            System.out.println("category not found please try again");
                            break;
                        }
                        System.out.println("please enter the new name");
                        String newName = scanner.nextLine();
                        System.out.println("enter new artibute");
                        String newarrtibute = scanner.nextLine();
                        System.out.println("enter new parent category name");
                        String newparentCategoryName = scanner.nextLine();
                        try {
                            mController.getCategoryBynameCategoryTypeMoudel(newparentCategoryName);
                        } catch (Exception e) {
                            System.out.println("new parent category not found please try again");
                            break;
                        }
                        try {
                            mController.editCategory(mController.getCategoryBynameCategoryTypeMoudel(categoryName), newName, newarrtibute, mController.getCategoryBynameCategoryTypeMoudel(newparentCategoryName));
                        } catch (Exception ignored) {}
                    } else if ((matcher = Commands.ADD_CATEGORY.getMatcher(command)).matches()) {
                        String name = matcher.group(1);
                        System.out.println("enter  attribute");
                        String arrtibute = scanner.nextLine();
                        System.out.println("enter parent category name");
                        String parentCategoryName = scanner.nextLine();
                        try {
                            mController.getCategoryBynameCategoryTypeMoudel(parentCategoryName);
                        } catch (Exception e) {
                            System.out.println("new parent category not found please try again");
                            break;
                        }
                        System.out.println("pleas enter sub categories then type done");
                        String sub = "";
                        ArrayList<Category> subs = null;
                        while (!sub.equals("done")) {
                            sub = scanner.nextLine();
                            try {
                                subs.add(mController.getCategoryBynameCategoryTypeMoudel(sub));
                            } catch (Exception e) {
                                System.out.println("invalid sub");
                            }
                            if (sub.equalsIgnoreCase("back"))
                                break;
                        }
                        if (Commands.BACK.getMatcher(sub).matches())
                            break;
                        HashMap<Product, Integer> productIntegerHashMap = null;
                        System.out.println("enter categorys products then done");
                        int productId;
                        while (true) {
                            String id = scanner.nextLine();

                            if (id.equals("done"))
                                break;
                            productId = Integer.parseInt(scanner.nextLine());
                            try {
                                productIntegerHashMap.put(mController.productByName(productId), mController.productCount(mController.productByName(productId)));
                            } catch (Exception e) {
                                System.out.println("product not found please try again");
                                break;
                            }
                        }

                    } else if ((matcher = Commands.REMOVE_CATEGORY.getMatcher(command)).matches()) {


                        String name = matcher.group(1);
                        try {
                            Category category = mController.getCategoryBynameCategoryTypeMoudel(name);
                        } catch (Exception e) {
                            System.out.println("category not found please try again");
                            break;
                        }
                        mController.deleteCategoryController(name);

                    } else if (Commands.HELP.getMatcher(command).matches())
                        System.out.println("edit [category]\n" +
                                "add [category]\n" +
                                "remove [category]\n" +
                                "help\n" +
                                "back");
                    else
                        printInvalidCommandMessage();
                }
            } else if (Commands.LOGIN_PAGE.getMatcher(command).matches()) {
                return LoginRegisterPage.getInstance();
            } else if (Commands.LOG_OUT.getMatcher(command).matches()) {
                AccountPageController.setUser(null);
                SellerPageController.getInstance().setUser(null);
                ManagerPageController.getInstance().setUser(null);
                CustomerPageController.getInstance().setUser(null);
                Page.pagesHistory.clear();
                return LoginRegisterPage.getInstance();
            } else if (Commands.ALL_PRODUCTS_PAGE.getMatcher(command).matches()) {
                return AllProductsPage.getInstance();
            } else if (Commands.OFFS_PAGE.getMatcher(command).matches()) {
                return OffsPage.getInstance();
            } else if (Commands.CART_PAGE.getMatcher(command).matches()) {
                return CartPage.getInstance();
            } else if (Commands.BACK.getMatcher(command).matches()) {
                Page page = pagesHistory.get(pagesHistory.size() - 1);
                pagesHistory.remove(pagesHistory.get(pagesHistory.size() - 1));
                return page;
            } else if (Commands.HELP.getMatcher(command).matches()) {
                System.out.println("view personal information\n" + "manage users\n" +
                        "manage all product\n" + "create discount code\n" + "view discount codes\n" + "manage requests\n" +
                        "manage categories\n" + "help\n" +
                        "login page\n" +
                        "log out\n" +
                        "products page\n" +
                        "offs page\n" +
                        "cart page\n" +
                        "back\n" +
                        "exit");
            } else {
                printInvalidCommandMessage();
            }
        }
        return null;
    }

    private void viewPersonalInfo() {
        System.out.println(mController.getPersonalInfo());

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
                mController.editPersonalInfo(field, input);
            else throw new Exceptions.InvalidFormatException();
        } else if (field.equalsIgnoreCase("email")) {
            if (Commands.EMAIL.getMatcher(input = scanner.nextLine().trim()).matches())
                mController.editPersonalInfo(field, input);
            else throw new Exceptions.InvalidFormatException();
        } else if (field.equalsIgnoreCase("phone number")) {
            if (Commands.PHONE_NUMBER.getMatcher(input = scanner.nextLine().trim()).matches())
                mController.editPersonalInfo(field, input);
            else throw new Exceptions.InvalidFormatException();
        } else if (field.equalsIgnoreCase("password")) {
            if (Commands.PASSWORD.getMatcher(input = scanner.nextLine().trim()).matches())
                mController.editPersonalInfo(field, input);
            else throw new Exceptions.InvalidFormatException();
        } else throw new Exceptions.InvalidFieldException();
        System.out.println(mController.getPersonalInfo());
    }
}
