package Model.Accounts;

import Model.Logs.SellLog;
import Model.Off;
import Model.Product;
import Model.Requests.Request;

import java.util.ArrayList;
import java.util.HashMap;

public class SellerAccount extends Account {
  
    private static ArrayList<SellerAccount> allSellerAccounts = new ArrayList<>();
    private String companyOrWorkShopName ;
    private ArrayList<SellLog> thisCustomerAllSellLogs;
    private HashMap<Product,Integer> sellableProductAndCounts;
    private ArrayList<Off> offs;
    private ArrayList<Request> requests;

<<<<<<< HEAD
    public void deleteSellerUser(String username){

        int size = allSellerAccounts.size();
        for (int i=0;i<size;i++) {

            if (allSellerAccounts.get(i).getUserName() . equals(username))
                allSellerAccounts.get(i).setPassword("23");

        }
=======
    public SellerAccount(String userName, String firstName, String lastName, String email, String phoneNumber, String password,
                         String accountType, String companyOrWorkShopName) {
        super(userName, firstName, lastName, email, phoneNumber, password, accountType);
        this.companyOrWorkShopName = companyOrWorkShopName;
        this.thisCustomerAllSellLogs = new ArrayList<>();
        this.offs = new ArrayList<>();
        this.requests = new ArrayList<>();
    }

    public boolean hasEnoughOfProduct (Product product, int count) {
        if (count < this.sellableProductAndCounts.get(product))
            return true;
        return false;
    }

    public int getCountOfProduct (Product product) {
        return this.sellableProductAndCounts.get(product);
    }

    public void sellSellLog (SellLog sellLog) {
        this.thisCustomerAllSellLogs.add(sellLog);
        this.sellableProductAndCounts.replace(sellLog.getSoldProduct(), this.sellableProductAndCounts.get(sellLog.getSoldProduct()) - sellLog.getCount());
>>>>>>> dee7658a4f0eb2a03f87d05969f0773a2f70e5a1
    }
}
