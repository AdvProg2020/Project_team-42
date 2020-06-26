package Controller.AccountPagesController;

import Controller.Exceptions;
import Model.Accounts.Account;
import Model.Accounts.CustomerAccount;
import Model.Accounts.ManagerAccount;
import Model.Category;
import Model.Product;
import Model.Requests.Request;
import Model.Shop;

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

    public void deleteDiscountController(String code){
        Shop.getInstance().deleteDiscountMoudel(code); }

    public StringBuilder showAllDiscountsController() throws Exception { return Shop.getInstance().showAllDiscountsMoudel(); }

    public String showAllRequestsController(){ return Request.showAllRequestsMoudel().toString(); }

    public void deleteProductController(long id) throws Exceptions.NoProductByThisIdException { Shop.getInstance().deleteProductMoudel(id);}

    public void editDiscountController(String code, GregorianCalendar begin, GregorianCalendar end, double discountPercent,int discountAmountLimit, int repeatCountForEachCustomer , HashMap<CustomerAccount, Integer> effectingCustomersAndUsageCount){ Shop.getInstance().editDiscountMoudel(code,begin,end,discountPercent,discountAmountLimit,repeatCountForEachCustomer ,effectingCustomersAndUsageCount);}

    public void deleteCategoryController(String name){ Shop.getInstance().deleteCategoryMoudel(name); }

    public void addCategoryController(String name, String attribute, String parentCategory, ArrayList<Category> subCategories, HashMap<Product, Integer> productsAndCount){Shop.getInstance().addCategoryMoudel(name,attribute,parentCategory, subCategories,productsAndCount);}

     public void createManagerAccountController(String userName, String firstName, String lastName, String email, String phoneNumber, String password, String accountType, boolean isMain){user.createManagerAccountMouddel(userName,firstName,lastName,email,phoneNumber,password,accountType,isMain);}

     public StringBuilder showAllProductsController() throws Exception {return Shop.getInstance().showAllProductsMoudel();}

    public void checkUsernameIsUsedController(String username) throws Exception {user.checkUsernameIsUsedMoudel(username);}

    public  void createDiscountCodeController(String discountCode, GregorianCalendar begin, GregorianCalendar end, double discountPercent, int discountAmountLimit, int repeatCountForEachCustomer, HashMap<CustomerAccount, Integer> effectingCustomersAndUsageCount)
    {
    user.createDiscountCodeMoudel( discountCode, begin, end,discountPercent,discountAmountLimit,repeatCountForEachCustomer,effectingCustomersAndUsageCount);
    }
    
     public void checkValidationDiscountCodeController(String code) throws Exception {user.checkValidationOfDiscountCodeMoudel(code);}

    public String viewDiscount(String discountCode){ return Shop.getInstance().discountView(discountCode);}

    public CustomerAccount getCustomerAccountAccountTypeByUsernameController(String username) throws Exception {return user.getCustomerAccountAccountTypeByUserNameMoudel(username);}

    public String requestDetailController(int id) throws Exception {return user.requestDetail(id);}

    public void acceptRequest(int id ) throws Exception {user.answerRequest(id,"accept");}

    public void declineRequest(int id) throws Exception {user.answerRequest(id,"decline");}

    public Category getCategoryBynameCategoryTypeMoudel(String name) throws Exception {return Shop.getInstance().getCategoryBynameCategoryTypeMoudel(name);}

    public void editCategory(Category category,String newName ,String attribute,Category parentCategory){category.editCategory(newName, attribute, parentCategory);}

    public Product productByName(int id) throws Exception {return Shop.getInstance().getProductByIdd(id);}

    public Integer productCount(Product product){ return Shop.getInstance().countOfProduct(product);}

    public String getAllCategories() throws Exception {
        StringBuilder stringBuilder = new StringBuilder();
        for (Category category : Shop.getInstance().getAllCategories()) {
            stringBuilder.append(String.valueOf(category)+"\n");
        }
        return String.valueOf(stringBuilder);
    }
}
