package Model.Accounts;
import Controller.Exceptions;
import Model.Discount;
import Model.Logs.BuyLog;
import Model.Product;

import java.util.ArrayList;
import java.util.HashMap;

public class CustomerAccount extends Account {

    private static ArrayList<CustomerAccount> allCustomerAccounts = new ArrayList<CustomerAccount>();
    private HashMap<Product, HashMap<SellerAccount, Integer>> cart;
    private ArrayList<BuyLog> thisCustomerBuyLogs;
    private HashMap<Discount, Integer> discountCodeAndUseCount;

    public CustomerAccount(String userName, String firstName, String lastName, String email, String phoneNumber, String password, String accountType) {
        super(userName, firstName, lastName, email, phoneNumber, password, accountType);
        this.cart = new HashMap<>();
        this.thisCustomerBuyLogs = new ArrayList<>();
        this.discountCodeAndUseCount = new HashMap<>();
    }

    public ArrayList<BuyLog> getBuyLogs() {
        return thisCustomerBuyLogs;
    }

    public HashMap<Discount, Integer> getDiscountCodeAndUseCount() {
        return discountCodeAndUseCount;
    }

    public boolean hasBoughtProduct(Product product) {
        for (BuyLog buyLog : this.thisCustomerBuyLogs) {
            if (buyLog.getBoughtProducts().contains(product))
                return true;
        }
        return false;
    }

    public void addProductToCart (Product product, SellerAccount seller) throws Exceptions.NotCustomerException {
        this.cart.put(product, new HashMap<SellerAccount, Integer>());
        this.cart.get(product).put(seller, 1);
    }
}
