package Model.Accounts;

import java.util.ArrayList;
import java.util.HashMap;

public class SellerAccount extends Account {
  
    private static ArrayList<SellerAccount> allSellerAccounts = new ArrayList<SellerAccount>();
    private String componyOrWorkShopName ;
    private ArrayList<SellLog> thisCostomerAllSellLogs;
    private HashMap<Product,Integer> sallableProductAndCounts;
    private ArrayList<Off> offs;
    private ArrayList<Requests> requests;
  
}
