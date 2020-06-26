package Controller.AccountPagesController;

import Controller.Exceptions;
import Model.Accounts.CustomerAccount;
import Model.Accounts.ManagerAccount;
import Model.Category;
import Model.Product;
import Model.Requests.Request;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;

public class ManagerPageController extends AccountPageController {
    boolean hasManagerPageRegistered;
    private ManagerAccount user;
    private static ManagerPageController mController = new ManagerPageController();
    public static ManagerPageController getInstance(){return mController;}
    private ManagerPageController() {
    }

    public static ManagerAccount getUser() {
        return mController.user;
    }

    public void setUser(ManagerAccount user) {
        this.user = user;
        AccountPageController.user = user;
    }
    public StringBuilder getAllAccountsController() {
    return user.getAllAccountsMoudel();
    }

    public String getAccountByUserNameController(String userName){
        return user.getAccountByUserNameMoudel(userName);
    }

    public StringBuilder getAllEmailsController(String userName){
        return user.getAllEmails();
    }

    public StringBuilder getAllPhoneNumbersController(String userName){
        return user.getAllPhoneNumbers();
    }

    public void deleteUserController(String userName){ user.deleteUserMoudel(userName); }

    public void deleteDiscountController(String code){ shop.deleteDiscountMoudel(code); }

    public StringBuilder showAllDiscountsController() throws Exception { return shop.showAllDiscountsMoudel(); }

    public String showAllRequestsController(){ return Request.showAllRequestsMoudel().toString(); }

    public void deleteProductController(long id) throws Exceptions.NoProductByThisIdException { shop.deleteProductMoudel(id);}

    public void editDiscountController(String code, GregorianCalendar begin, GregorianCalendar end, double discountPercent,int discountAmountLimit, int repeatCountForEachCustomer , HashMap<CustomerAccount, Integer> effectingCustomersAndUsageCount){ shop.editDiscountMoudel(code,begin,end,discountPercent,discountAmountLimit,repeatCountForEachCustomer ,effectingCustomersAndUsageCount);}

    public void deleteCategoryController(String name){ shop.deleteCategoryMoudel(name); }

    public void addCategoryController(String name, String attribute, Category parentCategory, ArrayList<Category> subCategories, HashMap<Product, Integer> productsAndCount){shop.addCategoryMoudel(name,attribute,parentCategory, subCategories,productsAndCount);}

     public void createManagerAccountController(String userName, String firstName, String lastName, String email, String phoneNumber, String password, String accountType, boolean isMain){user.createManagerAccountMouddel(userName,firstName,lastName,email,phoneNumber,password,accountType,isMain);}

     public StringBuilder showAllProductsController() throws Exception {return shop.showAllProductsMoudel();}

    public void checkUsernameIsUsedController(String username) throws Exception {user.checkUsernameIsUsedMoudel(username);}

    public  void createDiscountCodeController(String discountCode, GregorianCalendar begin, GregorianCalendar end, double discountPercent, int discountAmountLimit, int repeatCountForEachCustomer, HashMap<CustomerAccount, Integer> effectingCustomersAndUsageCount)
    {
    user.createDiscountCodeMoudel( discountCode, begin, end,discountPercent,discountAmountLimit,repeatCountForEachCustomer,effectingCustomersAndUsageCount);
    }
    
     public void checkValidationDiscountCodeController(String code) throws Exception {user.checkValidationOfDiscountCodeMoudel(code);}

    public String viewDiscount(String discountCode){ return shop.discountView(discountCode);}

    public CustomerAccount getCustomerAccountAccountTypeByUsernameController(String username) throws Exception {return user.getCustomerAccountAccountTypeByUserNameMoudel(username);}

    public String requestDetailController(int id) throws Exception {return user.requestDetail(id);}

    public void acceptRequest(int id ) throws Exception {user.answerRequest(id,"accept");}

    public void declineRequest(int id) throws Exception {user.answerRequest(id,"decline");}

    public Category getCategoryBynameCategoryTypeMoudel(String name) throws Exception {return shop.getCategoryBynameCategoryTypeMoudel(name);}

    public void editCategory(Category category,String newName ,String attribute,Category parentCategory){category.editCategory(newName, attribute, parentCategory);}

    public Product productByName(int id) throws Exception {return shop.getProductByIdd(id);}

    public Integer productCount(Product product){ return shop.countOfProduct(product);}

    public String getAllCategories() throws Exception {return String.valueOf(shop.getAllCategories());}
}
