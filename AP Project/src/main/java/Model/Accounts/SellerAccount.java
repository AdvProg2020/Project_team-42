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
    private HashMap<Product,Integer> sellableProductAndCounts;
    private ArrayList<Off> offs;
    private ArrayList<Request> requests;
}
