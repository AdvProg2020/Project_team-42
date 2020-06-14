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
    
    public SellerAccount loginSellerAccountController(String username , String password) throws Exception { return shop.loginSellerMoudel(username,password);}

    public ManagerAccount loginManagerAccountController(String username , String password) throws Exception { return shop.loginManagerMoudel(username,password);}

    public void CreateFirstManagerAccount(String userName, String firstName, String lastName, String email, String phoneNumber, String password,
                                          String accountType) throws Exception {
        shop.createFirstManagerAccount(userName, firstName, lastName, email, phoneNumber, password, accountType);
    }



}
 
