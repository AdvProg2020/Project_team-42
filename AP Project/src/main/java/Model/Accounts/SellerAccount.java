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
    }
}
