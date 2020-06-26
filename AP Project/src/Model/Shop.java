package Model;

import Controller.Exceptions;
import Model.Accounts.Account;
import Model.Accounts.CustomerAccount;
import Model.Accounts.ManagerAccount;
import Model.Accounts.SellerAccount;
import Model.Logs.BuyLog;
import Model.Logs.SellLog;
import Model.Requests.AddSellerAccountRequest;
import Model.Requests.Request;

import java.io.IOException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;

import static Model.Accounts.Account.getAllAccounts;
import static Model.Accounts.CustomerAccount.getAllCustomerAccounts;
import static Model.Accounts.ManagerAccount.getAllManagerAccounts;
import static Model.Accounts.SellerAccount.getAllSellerAccounts;


public class Shop {
    private static Shop shop = new Shop();
    private HashMap<Product, Integer> allProductAndCount;
    private HashMap<Product, Integer> allProductOnOffsAndCount;
    private ArrayList<SellLog> allSellLogs;
    private ArrayList<BuyLog> allBuyLogs;
    private ArrayList<Discount> allDiscounts;
    private ArrayList<Category> allCategories;
    private ArrayList<Off> allOffs;
    private Category allGoodsCategory;

    private Shop() {
        this.allProductAndCount = new HashMap<>();
        this.allProductOnOffsAndCount = new HashMap<>();
        this.allSellLogs = new ArrayList<>();
        this.allBuyLogs = new ArrayList<>();
        this.allDiscounts = new ArrayList<>();
        this.allCategories = new ArrayList<>();
        this.allOffs = new ArrayList<>();
        allCategories.add(allGoodsCategory = new Category("all goods", "", null, new ArrayList<Category>(), new HashMap<Product, Integer>()));
    }

    public void setAllProductOnOffsAndCount(HashMap<Product, Integer> allProductOnOffsAndCount) {
        this.allProductOnOffsAndCount = allProductOnOffsAndCount;
    }

    public Discount getDiscountByCode(String code) throws Exception {
        for (Discount discount : this.allDiscounts) {
            if (discount.getDiscountCode().equals(code))
                return discount;
        }
        throw new Exception();
    }

    public HashMap<Product, Integer> getAllProductAndCount() {
        return allProductAndCount;
    }

    public HashMap<Product, Integer> getAllProductOnOffsAndCount() {
        return allProductOnOffsAndCount;
    }

    public StringBuilder showAllProductsMoudel() throws Exception {
        StringBuilder sallProducts = new StringBuilder();
        if (allProductAndCount.isEmpty())
            throw new Exception("there is no product");
        for (Product product : allProductAndCount.keySet()) {
            sallProducts.append(product.getProductId() + "   " + product.getName() + "\n");
        }
        return sallProducts;
    }

    public StringBuilder showAllDiscountsMoudel() throws Exception {
        if (allDiscounts.isEmpty())
            throw new Exception("there is no discount");
        StringBuilder sallDiscounts = new StringBuilder();
        int size = allDiscounts.size();
        for (int i = 0; i < size; i++) {
            sallDiscounts.append(allDiscounts.get(i).getDiscountCode() + "   " + allDiscounts.get(i).getDiscountPercent() + "/n");
        }
        return sallDiscounts;
    }

    public void deleteProductMoudel(long id) throws Exceptions.NoProductByThisIdException {
        Product product = getProductById(id);
        allProductAndCount.replace(product, 0);
        for (SellerAccount seller : product.getSellers()) {
            seller.removeProduct(product);
        }
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

    public Product getProductById(long productId) throws Exceptions.NoProductByThisIdException {
        for (Product product : allProductAndCount.keySet()) {
            if (product.getProductId() == productId)
                return product;
        }
        throw new Exceptions.NoProductByThisIdException(productId);
    }

    public void setAllCategories(ArrayList<Category> allCategories) {
        this.allCategories = allCategories;
    }

    public ArrayList<Category> getAllCategories() throws Exception {
        if (allCategories.isEmpty())
            throw new Exception("there is no categories");
        return allCategories;
    }

    public ArrayList<SellLog> getAllSellLogs() {
        return allSellLogs;
    }

    public ArrayList<BuyLog> getAllBuyLogs() {
        return allBuyLogs;
    }

    public void addBuyLog(BuyLog buyLog) {
        this.allBuyLogs.add(buyLog);
    }

    public void addSellLog(SellLog sellLog) {
        this.allSellLogs.add(sellLog);
    }

    public void removeProduct(Product product, int count) {
        allProductAndCount.replace(product, allProductAndCount.get(product) - count);
        for (Product productOnOFF : allProductOnOffsAndCount.keySet()) {
            if (product == productOnOFF) {
                allProductOnOffsAndCount.replace(productOnOFF, allProductOnOffsAndCount.get(productOnOFF) - count);
            }
        }
    }

    public void deleteDiscountMoudel(String code) {
        for (int i = 0; i < allDiscounts.size(); i++) {
            if (allDiscounts.get(i).getDiscountCode().equals(code)) {
                allDiscounts.remove(i);
                break;
            }
        }
    }

    public void editDiscountMoudel(String code, GregorianCalendar begin, GregorianCalendar end, double discountPercent, int discountAmountLimit, int repeatCountForEachCustomer, HashMap<CustomerAccount, Integer> effectingCustomersAndUsageCount) {
        for (Discount allDiscount : allDiscounts) {
            if (allDiscount.getDiscountCode().equals(code)) {
                allDiscount.setBegin(begin);
                allDiscount.setDiscountAmountLimit(discountAmountLimit);
                allDiscount.setDiscountPercent(discountPercent);
                allDiscount.setEnd(end);
                allDiscount.setRepeatCountForEachCustomer(repeatCountForEachCustomer);
                break;
            }
        }

    }

    //Product product = getProductById(id);
    //for (SellerAccount seller : product.getSellers()) {
    //seller.getSellableProductAndCounts().replace(product , 0);
    //}

    public boolean isCategory(String name) {
        for (Category category : allCategories) {
            if (category.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public void deleteCategoryMoudel(String name) {
        for (Category category : allCategories) {


            if (category.getName().equals(name)) {
                if (category.getParentCategory() != null) {
                    category.getParentCategory().getProductIdAndCount().putAll(category.getProductIdAndCount());
                    category.getParentCategory().addToSubCategories().addAll(category.addToSubCategories());
                }
                for (Category subCategory : category.getSubCategories()) {
                    subCategory.setParentCategory(category.getParentCategory());
                }
                for (Product product : category.getProductsAndCount().keySet()) {
                    product.setCategory(category.getParentCategory());
                }
                allCategories.remove(category);
                /*try {
                    category.updateResources();
                } catch (IOException ignored) {}*/
                break;
            }
        }
    }

    public Category getCategoryBynameCategoryTypeMoudel(String name) throws Exception {
        for (Category allCategory : allCategories) {
            if (allCategory.getName().equals(name))
                return allCategory;
        }
        throw new Exception("not found");
    }


    public void addCategoryMoudel(String name, String attribute, Category parentCategory, ArrayList<Category> subCategories, HashMap<Product, Integer> productsAndCount) {
        allCategories.add(new Category(name, attribute, parentCategory, subCategories, productsAndCount));
    }


    public Category getCategoryByName(String categoryName) throws Exceptions.NoCategoryException {
        for (Category category : allCategories) {
            if (category.getName().equals(categoryName))
                return category;
        }
        throw new Exceptions.NoCategoryException();
    }

    public int returnNewId() {
        return allProductAndCount.keySet().size();
    }

    public Off getOffById(int id) throws Exceptions.NoOffByThisId {
        for (Off off : allOffs) {
            if (off.getOffId() == id) {
                return off;
            }
        }
        throw new Exceptions.NoOffByThisId();
    }

    public void setAllProductAndCount(HashMap<Product, Integer> allProductAndCount) {
        this.allProductAndCount = allProductAndCount;
    }

    public String discountView(String discountCode) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Discount allDiscount : allDiscounts) {
            if (allDiscount.getDiscountCode().equals(discountCode)) {
                for (CustomerAccount customerAccount : allDiscount.getEffectingCustomersAndUsageCount().keySet()) {
                    stringBuilder.append(customerAccount.getUserName() + "    " + allDiscount.getEffectingCustomersAndUsageCount().get(customerAccount) + "\n");
                }
                return allDiscount.getDiscountCode() + "  " + allDiscount.getBegin() + "   " + allDiscount.getEnd() + "    " + allDiscount.getDiscountPercent() + "    " + allDiscount.getDiscountAmountLimit() + "   " + allDiscount.getRepeatCountForEachCustomer() + "    " + "\n" + stringBuilder;
            }
        }
        return "not found";
    }

    public void createCustomerAccountMoudel(String userName, String firstName, String lastName, String email, String phoneNumber, String password, String accountType) throws Exception {
        for (int i = 0; i < getAllAccounts().size(); i++) {
            if (getAllAccounts().get(i).getUserName().equals(userName))
                throw new Exception("username is used");
        }
        CustomerAccount account;
        getAllAccounts().add(account = new CustomerAccount(userName, firstName, lastName, email, phoneNumber, password, accountType));
        getAllCustomerAccounts().add(account);
        //account.updateResources();
    }

    public void createRegisterRequestSellerAccountMoudel(String userName, String firstName, String lastName, String email, String phoneNumber, String password, String accountType, String companyOrWorkShopName) throws Exception {
        for (int i = 0; i < getAllAccounts().size(); i++) {
            if (getAllAccounts().get(i).getUserName().equals(userName))
                throw new Exception("username is used");
        }
        Request register = new AddSellerAccountRequest(companyOrWorkShopName, userName, firstName, lastName, email, phoneNumber, password, accountType);
        Request.getUnAnsweredRequests().add(register);
    }

    public CustomerAccount loginCustomerMoudel(String username, String password) throws Exception {
        for (CustomerAccount customerAccount : getAllCustomerAccounts()) {
            if (username.equals(customerAccount.getUserName()) && password.equals(customerAccount.getPassword()))
                return customerAccount;
        }
        throw new Exception("account not found");
    }

    public Product getProductByIdd(int id) throws Exception {
        for (Product product : allProductAndCount.keySet()) {
            if (product.getProductId() == id) {
                return product;
            }
        }
        throw new Exception("not found");
    }

    public Integer countOfProduct(Product product) {
        return allProductAndCount.get(product);
    }

    public boolean isOffById(int id) {
        for (Off off : allOffs) {
            if (off.getOffId() == id) {
                return true;
            }
        }
        return false;
    }


    public ManagerAccount loginManagerMoudel(String username, String password) throws Exception {
        for (ManagerAccount managerAccount : getAllManagerAccounts()) {
            if (username.equals(managerAccount.getUserName()) && password.equals(managerAccount.getPassword()))
                return managerAccount;
        }
        throw new Exception("account not found");
    }

    public void createFirstManagerAccountMoudel(String userName, String firstName, String lastName, String email, String phoneNumber, String password,
                                                String accountType) throws Exception {
        for (Account account : getAllAccounts()) {
            if (account.getUserName().equalsIgnoreCase(userName))
                throw new Exception("username is used");
        }
        ManagerAccount managerAccount = new ManagerAccount(userName, firstName, lastName, email, phoneNumber, password,
                accountType, true);
        //managerAccount.updateResources();
        getAllManagerAccounts().add(managerAccount);
        getAllAccounts().add(managerAccount);
    }

    public SellerAccount loginSellerMoudel(String username, String password) throws Exception {
        for (SellerAccount sellerAccount : getAllSellerAccounts()) {
            if (username.equals(sellerAccount.getUserName()) && password.equals(sellerAccount.getPassword()))
                return sellerAccount;
        }
        throw new Exception("account not found");
    }

    public Category getAllGoodsCaregory() {
        return allGoodsCategory;
    }
}
