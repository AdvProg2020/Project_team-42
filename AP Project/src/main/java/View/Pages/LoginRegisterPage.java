package View.Pages;

import View.Page;

public class LoginRegisterPage extends Page {
    private static LoginRegisterPage loginRegisterPage = new LoginRegisterPage();

    private LoginRegisterPage () {}

    public static LoginRegisterPage getInstance() {
        return loginRegisterPage;
    }

    public Page run() {
while (true) {
            Matcher matcher;
            String command = scanner.nextLine();
            if((matcher=Commands.LOGIN.getMatcher(command)).matches())
            {
                
                String username=matcher.group(1);
                System.out.println("please enter your password");
                String password=scanner.nextLine();
                boolean customer=false,seller=false,manager=false;
                ManagerAccount managerAccount=null;
                CustomerAccount customerAccount=null;
                SellerAccount sellerAccount=null;
                try {
                    customerAccount=controller.loginCustomerAccountController(username,password)
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
                    manager=true
                }
                if (manager==false){
                    ManagerPageController.setUser(managerAccount);
                return ;
                }
                else if (seller==false){
                    SellerPageController.setUser(sellerAccount);
                    return ;}
                else if (customer==false)
                {
                    CustomerPageController.setUser(customerAccount);
                    return ;
                }

            }
            if ((matcher=Commands.CREATE_ACCOUNT.getMatcher(command)).matches())
            {
                String type=matcher.group(1);
                String username=matcher.group(2);
                if (type.equals("manager"))
                {
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
                    while (userpassword.length()<5)
                    {
                        System.out.println("please enter another password it should be over 4 characters");
                        userpassword = scanner.nextLine();
                    }
                    try {
                        controller.CreateFirstManagerAccount(username, userfirstname, userlastname, useremail, userphoneNumber, userpassword,
                            "manager");
                    } catch (Exception e) {
                        System.out.println("username is used please try again");
                    }
                }
                else if (type.equals("customer"))
                {
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
                    while (userpassword.length()<5)
                    {
                        System.out.println("please enter another password it should be over 4 characters");
                        userpassword = scanner.nextLine();
                    }
                    try {
                        controller.createCustomerAccountController(username,userfirstname,userlastname,useremail,userphoneNumber,userpassword,"customer");
                    } catch (Exception e) {
                        System.out.println("username is used please try again");
                    }
                }
                else if (type.equals("seller"))
                {
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
                    while (userpassword.length()<5)
                    {
                        System.out.println("please enter another password it should be over 4 characters");
                        userpassword = scanner.nextLine();
                    }
                    System.out.println("enter company or workshop name");
                    String company = scanner.nextLine();
                    try {
                        controller.createRegisterRequestSellerAccountController(username,userfirstname,userlastname,useremail,userphoneNumber,userpassword,"seller",company);
                    } catch (Exception e) {
                        System.out.println("username is used please try again");
                    }
                }
            }
        }    }
}
