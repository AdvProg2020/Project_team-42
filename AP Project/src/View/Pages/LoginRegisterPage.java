package View.Pages;

import Controller.AccountPagesController.AccountPageController;
import Controller.AccountPagesController.CustomerPageController;
import Controller.AccountPagesController.ManagerPageController;
import Controller.AccountPagesController.SellerPageController;
import Controller.LoginAndRegisterPageController;
import Model.Accounts.CustomerAccount;
import Model.Accounts.ManagerAccount;
import Model.Accounts.SellerAccount;
import View.Commands;
import View.Page;
import View.Pages.AccountsPage.CustomerPage;
import View.Pages.AccountsPage.ManagerPage;
import View.Pages.AccountsPage.SellerPage;

import java.util.regex.Matcher;

public class LoginRegisterPage extends Page {
    private static LoginRegisterPage loginRegisterPage = new LoginRegisterPage();
    private LoginAndRegisterPageController controller = LoginAndRegisterPageController.getInstance();

    private LoginRegisterPage () {}

    public static LoginRegisterPage getInstance() {
        return loginRegisterPage;
    }
    public static boolean isMainManagerJoined=false;
    public Page run() {
        Page.pagesHistory.add(this);

        System.out.println("login page");

        while (true) {
            Matcher matcher;
            String command = scanner.nextLine();
            if (Commands.EXIT.getMatcher(command).matches())
                break;
            else if((matcher = Commands.LOGIN.getMatcher(command)).matches())
            {

                String username=matcher.group(1);
                System.out.println("please enter your password");
                String password=scanner.nextLine();
                boolean customer=false,seller=false,manager=false;
                ManagerAccount managerAccount=null;
                CustomerAccount customerAccount=null;
                SellerAccount sellerAccount=null;
                try {
                    customerAccount=controller.loginCustomerAccountController(username,password);
                } catch (Exception e) {
                    customer=true;
                }
                try {
                    sellerAccount=controller.loginSellerAccountController(username,password);
                } catch (Exception e) {
                    seller=true;
                }
                try {
                    managerAccount=controller.loginManagerAccountController(username,password);
                } catch (Exception e) {
                    manager=true;
                }
                if (!manager){
                    ManagerPageController.getInstance().setUser(managerAccount);
                    return ManagerPage.getInstance();
                }
                else if (!seller){
                    SellerPageController.getInstance().setUser(sellerAccount);
                    return SellerPage.getInstance();}
                else if (!customer)
                {
                    CustomerPageController.getInstance().setUser(customerAccount);
                    return CustomerPage.getInstance();
                }else
                    System.out.println("invalid username or password please try again");

            } else if (Commands.BACK.getMatcher(command).matches()) {
                if (Page.pagesHistory.size() == 1)
                    return Page.pagesHistory.get(0);
                Page.pagesHistory.remove(Page.pagesHistory.size() - 1);
                return Page.pagesHistory.get(Page.pagesHistory.size() - 1);
            }
            else if ((matcher=Commands.CREATE_ACCOUNT.getMatcher(command)).matches())
            {


                String type=matcher.group(1);
                String username=matcher.group(2);
                if (!isMainManagerJoined){
                if (type.equals("manager")) {
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
                    try {

                        controller.CreateFirstManagerAccountController(username, userfirstname, userlastname, useremail, userphoneNumber, userpassword,
                                "manager");
                        isMainManagerJoined=true;
                        System.out.println("You have successfully created an account.");
                    } catch (Exception e) {
                        System.out.println("username is used please try again");
                    }
                }
                }
                else if (type.equals("customer"))
                {
                    if (isMainManagerJoined) {
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
                        try {
                            controller.createCustomerAccountController(username, userfirstname, userlastname, useremail, userphoneNumber, userpassword, "customer");
                            System.out.println("You have successfully created an account.");
                        } catch (Exception e) {
                            System.out.println("username is used please try again");
                        }
                    }
                }
                else if (type.equals("seller"))
                {
                    if (isMainManagerJoined) {
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
                        System.out.println("enter company or workshop name");
                        String company = scanner.nextLine();
                        try {

                            controller.createRegisterRequestSellerAccountController(username, userfirstname, userlastname, useremail, userphoneNumber, userpassword, "seller", company);
                            System.out.println("You have successfully created a request for seller account.");
                        } catch (Exception e) {
                            System.out.println("username is used please try again");
                        }
                    }
                }
            } else if (Commands.ACCOUNT_PAGE.getMatcher(command).matches()) {
                if (AccountPageController.getUser() == null)
                    return LoginRegisterPage.getInstance();
                if (AccountPageController.getUser().getClass() == CustomerAccount.class)
                    return CustomerPage.getInstance();
                if (AccountPageController.getUser().getClass() == SellerAccount.class)
                    return SellerPage.getInstance();
                if (AccountPageController.getUser().getClass() == ManagerAccount.class)
                    return ManagerPage.getInstance();
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
            } else if (Commands.HELP.getMatcher(command).matches()) {
                    System.out.println("login [username]\n" +
                            "create account [type] [username]\n" +
                            "help\n" +
                            "log out\n" +
                            "all product page\n" +
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
}
