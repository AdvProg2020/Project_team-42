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
    private ArrayList<SellLog> thisSellerAllSellLogs;
    private HashMap<Product,Integer> sellableProductAndCounts;
    private ArrayList<Off> offs;
    private ArrayList<Request> requests;

    public SellerAccount(String userName, String firstName, String lastName, String email, String phoneNumber, String password, String accountType, String companyOrWorkShopName) {
        super(userName, firstName, lastName, email, phoneNumber, password, accountType);
        this.companyOrWorkShopName = companyOrWorkShopName;
        this.offs = new ArrayList<>();
        this.sellableProductAndCounts = new HashMap<>();
        this.requests = new ArrayList<>();
        this.thisSellerAllSellLogs = new ArrayList<>();
    }

    public String getCompanyOrWorkShopName() {
        return companyOrWorkShopName;
    }

    public ArrayList<SellLog> getThisSellerAllSellLogs() {
        return thisSellerAllSellLogs;
    }

    public HashMap<Product, Integer> getSellableProductAndCounts() {
        return sellableProductAndCounts;
    }

    public ArrayList<Off> getOffs() {
        return offs;
    }

    public ArrayList<Request> getRequests() {
        return requests;
    }

    public String showPersolanInfo()
    {
        return "username : " + super.userName +
                "\nfirstName : " + super.firstName +
                "\nlastName : " + super.lastName +
                "\nemail : " + super.email +
                "\nphoneNumber : " + super.phoneNumber +
                "\npassword : " + super.password +
                "\naccountType : " + super.accountType +
                "\ncredit : " + super.credit +
                "\ncompony or workshop :" + companyOrWorkShopName;
    }

    public String getBalance(){
        return "Balance :"+String.valueOf(super.credit);
    }
}
