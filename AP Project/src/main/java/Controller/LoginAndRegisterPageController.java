package Controller;

import Model.Accounts.CustomerAccount;
import Model.Shop;

public class LoginAndRegisterPageController {
    Shop shop = Shop.getInstance();

    public void createCustomerAccountController(String userName, String firstName, String lastName, String email, String phoneNumber, String password, String accountType)
    {
        shop.createCustomerAccountMoudel(userName,firstName,lastName,email,phoneNumber,password,accountType);
    }

    public void createRegisterRequestSellerAccountController(String userName, String firstName, String lastName, String email, String phoneNumber, String password, String accountType, String companyOrWorkShopName)
    {
        createRegisterRequestSellerAccountController(userName,firstName,lastName,email,phoneNumber,password,accountType,companyOrWorkShopName);
    }

    public CustomerAccount loginCustomerAccountController(String username ,String password) throws Exception { return shop.loginCustomerMoudel(username,password);}
}
 