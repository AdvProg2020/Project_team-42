package Model.Accounts;
import Model.Discount;
import Model.Logs.BuyLog;
import Model.Product;

import java.util.ArrayList;
import java.util.HashMap;

public class CustomerAccount extends Account {

    private static ArrayList<CustomerAccount> allCustomerAccounts = new ArrayList<CustomerAccount>();
    private HashMap<Product, Integer> cart;
    private ArrayList<BuyLog> thisCustomerBuyLogs;
    private HashMap<Discount, Integer> discountCodeAndUseCount;

    public ArrayList<BuyLog> getBuyLogs() {
        return thisCustomerBuyLogs;
    }

    public HashMap<Discount, Integer> getDiscountCodeAndUseCount() {
        return discountCodeAndUseCount;
    }
}
