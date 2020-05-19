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

    public void deleteCustomerUser(String username){
        int size = allCustomerAccounts.size();
        for (int i=0;i<size;i++) {

            if (allCustomerAccounts.get(i).getUserName() . equals(username))
                allCustomerAccounts.get(i).setPassword("23");

        }
    }
}
