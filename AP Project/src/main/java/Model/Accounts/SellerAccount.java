package Model.Accounts;

import Model.Logs.SellLog;
import Model.Off;
import Model.Product;
import Model.Requests.Request;

import java.util.ArrayList;
import java.util.HashMap;

public class SellerAccount extends Account {
  
    private static ArrayList<SellerAccount> allSellerAccounts = new ArrayList<SellerAccount>();
    private String companyOrWorkShopName ;
    private ArrayList<SellLog> thisCustomerAllSellLogs;
    private HashMap<Product,Integer> sallableProductAndCounts;
    private ArrayList<Off> offs;
    private ArrayList<Request> requests;

    public void deleteSellerUser(String username){

        int size = allSellerAccounts.size();
        for (int i=0;i<size;i++) {

            if (allSellerAccounts.get(i).getUserName() . equals(username))
                allSellerAccounts.get(i).setPassword("23");

        }
    }
}
