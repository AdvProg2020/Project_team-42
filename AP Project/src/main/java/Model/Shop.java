package Model;

import Controller.Exceptions;
import Model.Logs.BuyLog;
import Model.Logs.SellLog;
import Model.Accounts.CustomerAccount;
import Model.Accounts.SellerAccount;
import java.util.ArrayList;
import java.util.HashMap;
import Model.Requests.AddSellerAccountRequest;
import Model.Requests.Request;
import java.util.GregorianCalendar;
import static Model.Accounts.Account.getAllAccounts;
import static Model.Accounts.CustomerAccount.getAllCustomerAccounts;



public class Shop {
    private static Shop shop = new Shop();
    private HashMap<Product,Integer> allProductAndCount;
    private HashMap<Product, Integer> allProductOnOffsAndCount;
    private ArrayList<SellLog> allSellLogs;
    private ArrayList<BuyLog> allBuyLogs;
    private ArrayList<Discount> allDiscounts;
    private ArrayList<Category> allCategories;
    private ArrayList<Off> allOffs;

    private Shop() {
        this.allProductAndCount = new HashMap<>();
        this.allProductOnOffsAndCount = new HashMap<>();
        this.allSellLogs = new ArrayList<>();
        this.allBuyLogs = new ArrayList<>();
        this.allDiscounts = new ArrayList<>();
        this.allCategories = new ArrayList<>();
        this.allOffs = new ArrayList<>();
    }

    public HashMap<Product, Integer> getAllProductAndCount() {
        return allProductAndCount;
    }

    public HashMap<Product, Integer> getAllProductOnOffsAndCount() {
        return allProductOnOffsAndCount;
    }

    public StringBuilder showAllProductsMoudel(){
        StringBuilder sallProducts = new StringBuilder();
        for (Product product : allProductAndCount.keySet()) {
           sallDiscounts.append(allDiscounts.get(i).getDiscountCode() + "   " + allDiscounts.get(i).getDiscountPercent() + "\n");
        }
        return sallProducts;
    }

    public StringBuilder showAllDiscountsMoudel(){
        StringBuilder sallDiscounts = new StringBuilder();
        int size = allDiscounts.size();
        for (int i=0;i<size;i++)
        {
            sallDiscounts.append(allDiscounts.get(i).getDiscountCode() + "   " + allDiscounts.get(i).getDiscountPercent() + "/n");
        }
        return sallDiscounts;
    }

    public void deleteProductMoudel(long id) throws Exceptions.NoProductByThisIdException {
        allProductAndCount.replace(getProductById(id), 0);
    }

    public ArrayList<Discount> getAllDiscounts() {
        return allDiscounts;
    }

    public ArrayList<Off> getAllOffs() {
        return allOffs;
    }



    public static Shop getInstance() {
        return shop;
    }

    public Product getProductById (long productId) throws Exceptions.NoProductByThisIdException {
        for (Product product : allProductAndCount.keySet()) {
            if (product.getProductId() == productId)
                return product;
        }
        throw new Exceptions.NoProductByThisIdException(productId);
    }

    public ArrayList<Category> getAllCategories() {
        if(allCategories.isEmpty())
           return null;
        return allCategories;
    }

    public ArrayList<SellLog> getAllSellLogs() {
        return allSellLogs;
    }

    public ArrayList<BuyLog> getAllBuyLogs() {
        return allBuyLogs;
    }

    public void addBuyLog (BuyLog buyLog) {
        this.allBuyLogs.add(buyLog);
    }

    public void addSellLog (SellLog sellLog) {
        this.allSellLogs.add(sellLog);
    }
    
        public void removeProduct(Product product , int count) {
        allProductAndCount.replace(product , allProductAndCount.get(product)-count);
        for (Product productOnOFF : allProductOnOffsAndCount.keySet()) {
            if(product == productOnOFF) {
                allProductOnOffsAndCount.replace(productOnOFF,allProductOnOffsAndCount.get(productOnOFF)-count);
            }
        }
    }
    
    public void deleteDiscountMoudel(String code){
        for (int i =0;i<allDiscounts.size();i++)
        {
            if (allDiscounts.get(i).getDiscountCode() . equals(code))
            {
                allDiscounts.remove(i);
                break;
            }
        }
    }

    public void editDiscountMoudel(String code, GregorianCalendar begin,GregorianCalendar end,double discountPercent,int discountAmountLimit,int repeatCountForEachCustomer,HashMap<CustomerAccount, Integer> effectingCustomersAndUsageCount){
        for (int i =0;i<allDiscounts.size();i++)
        {
            if (allDiscounts.get(i).getDiscountCode() . equals(code))
            {
                allDiscounts.get(i).setBegin(begin);
                allDiscounts.get(i).setDiscountAmountLimit(discountAmountLimit);
                allDiscounts.get(i).setDiscountPercent(discountPercent);
                allDiscounts.get(i).setEnd(end);
                allDiscounts.get(i).setRepeatCountForEachCustomer(repeatCountForEachCustomer);
                break;
            }
        }

    }

    Product product = getProductById(id);
        for (SellerAccount seller : product.getSellers()) {
            seller.getSellableProductAndCounts().replace(product , 0);
        }

    public boolean isCategory(String name){
        for (Category category : allCategories) {
            if(category.getName().equals(name)){
                return true;
            }
        }
        return false;
    }
    
    public void deleteCategoryMoudel(String name){
        for (Category category : allCategories) {


            if (category.getName() . equals(name))
            {
                if (category.getParentCategory()!=null) {
                    category.getParentCategory().getProductsAndCount().putAll(category.getProductsAndCount());
                    category.getParentCategory().getSubCategories().addAll(category.getSubCategories());
                }
                for (Category subCategory : category.getSubCategories()) {
                    subCategory.setParentCategory(category.getParentCategory());
                }
                for (Product product : category.getProductsAndCount().keySet()) {
                    product.setCategory(category.getParentCategory());
                }
                allCategories.remove(category);
                break;
            }
        } 
    }

    public Category getCategoryBynameCategoryTypeMoudel(String name) throws Exception {
        for (int i = 0 ; i< allCategories.size(); i++)
        {
            if (allCategories.get(i).getName().equals(name))
                return allCategories.get(i);
        }
        throw new Exception("not found");
    }



public void addCategoryMoudel(String name, String attribute, Category parentCategory, ArrayList<Category> subCategories, HashMap<Product, Integer> productsAndCount)
{
   allCategories.add( new Category(name, attribute,parentCategory,subCategories, productsAndCount) ) ;
}
    
    
     public Category getCategoryByName(String categoryName)throws Exceptions.NoCategoryException {
        for (Category category : allCategories) {
            if(category.getName().equals(categoryName))
                return category;
        }
        throw new Exceptions.NoCategoryException();
    }
    
     public int returnNewId()
    {
        return  allProductOnOffsAndCount.keySet().size();
    }
    
      public Off getOffById(int id) throws Exceptions.NoOffByThisId{
        for (Off off : allOffs) {
            if(off.getOffId() == id) {
                return off;
            }
        }throw new Exceptions.NoOffByThisId();
    }
    
    public void setAllProductAndCount(HashMap<Product, Integer> allProductAndCount) {
        this.allProductAndCount = allProductAndCount;
    }

    public String discountView(String discountCode)
    {
        for (int i=0;i<allDiscounts.size();i++) {
            if (allDiscounts.get(i).getDiscountCode().equals(discountCode)) {
                String a = allDiscounts.get(i).getDiscountCode() + "  " + allDiscounts.get(i).getBegin() + "   " + allDiscounts.get(i).getEnd() + "    " + allDiscounts.get(i).getDiscountPercent() + "    " + allDiscounts.get(i).getDiscountAmountLimit() + "   " + allDiscounts.get(i).getRepeatCountForEachCustomer() + "    " + allDiscounts.get(i).getEffectingCustomersAndUsageCount();
                return a;
            }
        }
        return "not found";
    }

    public void createCustomerAccountMoudel(String userName, String firstName, String lastName, String email, String phoneNumber, String password, String accountType)
    {
        CustomerAccount account;
        getAllAccounts().add(account = new CustomerAccount(userName,firstName,lastName,email,phoneNumber,password,accountType));
        getAllCustomerAccounts().add(account);
    }

    public void createRegisterRequestSellerAccountMoudel(String userName, String firstName, String lastName, String email, String phoneNumber, String password, String accountType, String companyOrWorkShopName)
    {
        Request register = new AddSellerAccountRequest(companyOrWorkShopName,userName,firstName,lastName,email,phoneNumber,password,accountType);
        Request.getUnAnsweredRequests().add(register);
    }

    public CustomerAccount loginCustomerMoudel(String username,String password) throws Exception {
        for (CustomerAccount customerAccount : getAllCustomerAccounts()) {
            if (username.equals(customerAccount.getUserName())&&password.equals(customerAccount.getPassword()))
                return customerAccount;
        }
        throw new Exception("account not found");
    }

    public Product getProductByIdd(int id) throws Exception {
        for (Product product : allProductAndCount.keySet()) {
            if(product.getProductId() == id){
                return product;
            }
        }
        throw new Exception("not found");
    }

    public Integer countOfProduct(Product product){
        return allProductAndCount.get(product);
    }

    public boolean isOffById(int id) {
        for (Off off : allOffs) {
            if(off.getOffId() == id) {
                return true;
            }
        }
        return false;
    }
    
    public ManagerAccount loginManagerMoudel(String username, String password) throws Exception {
        for (ManagerAccount managerAccount : getAllManagerAccounts()) {
            if (username.equals(managerAccount.getUserName())&&password.equals(managerAccount.getPassword()))
                return managerAccount;
        }
        throw new Exception("account not found");
    }

    public void createFirstManagerAccount(String userName, String firstName, String lastName, String email, String phoneNumber, String password,
                                          String accountType) throws Exception {
        for (int i=0;i<getAllAccounts().size();i++)
        {
            if (getAllAccounts().get(i).equals(userName))
                throw new Exception("username is used");
        }
        ManagerAccount managerAccount= new ManagerAccount(userName,firstName,lastName,email,phoneNumber,password,
                accountType,true);
        getAllManagerAccounts().add(managerAccount);
        getAllAccounts().add(managerAccount);
    }
}
