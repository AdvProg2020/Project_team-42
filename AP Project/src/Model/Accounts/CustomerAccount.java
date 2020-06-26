package Model.Accounts;
import Controller.Exceptions;
import Model.Discount;
import Model.Logs.BuyLog;
import Model.Product;
import Model.Shop;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class CustomerAccount extends Account {

    private static ArrayList<CustomerAccount> allCustomerAccounts = new ArrayList<>();
    private HashMap<Integer, HashMap<String, Integer>> cart;
    private ArrayList<BuyLog> thisCustomerBuyLogs;
    private HashMap<String, Integer> discountCodeAndUseCount;

    public CustomerAccount(String userName, String firstName, String lastName, String email, String phoneNumber, String password,
                           String accountType) {
        super(userName, firstName, lastName, email, phoneNumber, password, accountType);
        this.cart = new HashMap<>();
        this.thisCustomerBuyLogs = new ArrayList<>();
        this.discountCodeAndUseCount = new HashMap<>();
    }

    public static CustomerAccount getCustomerByUsername (String username) throws Exception {
        for (CustomerAccount customerAccount : allCustomerAccounts) {
            if (customerAccount.getUserName().equalsIgnoreCase(username))
                return customerAccount;
        }

        throw new Exception();
    }

    public HashMap<Product, HashMap<SellerAccount, Integer>> getCart() {
        HashMap<Product, HashMap<SellerAccount, Integer>> cart = new HashMap<>();
        for (Integer productId : this.cart.keySet()) {
            HashMap<SellerAccount, Integer> sellerAndCount = new HashMap<>();
            for (String seller : this.cart.get(productId).keySet()) {
                try {
                    sellerAndCount.put(SellerAccount.getSellerAccountByUsername(seller),
                            this.cart.get(productId).get(seller));
                } catch (Exception ignored) {}
            }
            try {
                cart.put(Shop.getInstance().getProductById(productId), sellerAndCount);
            } catch (Exceptions.NoProductByThisIdException ignored) {}
        }
        return cart;
    }

    public ArrayList<BuyLog> getBuyLogs() {
        return thisCustomerBuyLogs;
    }

    public HashMap<Discount, Integer> getDiscountCodeAndUseCount() {
        HashMap<Discount, Integer> discountAndCount = new HashMap<>();
        for (String code : this.discountCodeAndUseCount.keySet()) {
            try {
                discountAndCount.put(Shop.getInstance().getDiscountByCode(code), this.discountCodeAndUseCount.get(code));
            } catch (Exception ignored) {}
        }
        return discountAndCount;
    }

    public static ArrayList<CustomerAccount> getAllCustomerAccounts() {
        return allCustomerAccounts;
    }

    public boolean hasBoughtProduct(Product product) {
        for (BuyLog buyLog : this.thisCustomerBuyLogs) {
            if (buyLog.getBoughtProducts().contains(product))
                return true;
        }
        return false;
    }

    public Discount getDiscountByCode (String discountCode) throws Exceptions.NoDiscountByCodeException {
        if (this.discountCodeAndUseCount.containsKey(discountCode)) {
            try {
                return Shop.getInstance().getDiscountByCode(discountCode);
            } catch (Exception ignored) {}
        }
        throw new Exceptions.NoDiscountByCodeException();
    }

    public Product getProductFromBuyLogsById (long id) throws Exceptions.NotBoughtProductByIdException {
        for (BuyLog buyLog : this.thisCustomerBuyLogs) {
            for (Product product : buyLog.getBoughtProducts()) {
                if (product.getProductId() == id)
                    return product;
            }
        }
        throw new Exceptions.NotBoughtProductByIdException();
    }

    public void purchaseBuyLog (BuyLog buyLog) {
        this.thisCustomerBuyLogs.add(buyLog);
        this.credit -= buyLog.getPayedMoney();
    }

    /*public void updateResources () throws IOException {
        Gson gson = new Gson();
        FileWriter fileWriter = new FileWriter("src\\main\\resources\\Accounts\\CustomerAccounts\\" + this.userName + ".txt");

        gson.toJson(this, fileWriter);
        fileWriter.close();
    }*/

    public void setCart(HashMap<Integer, HashMap<String, Integer>> cart) {
        this.cart = cart;
    }
}
