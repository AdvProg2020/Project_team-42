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

    public boolean hasEnoughOfProduct (Product product, int count) {
        if (count < this.sellableProductAndCounts.get(product))
            return true;
        return false;
    }

    public int getCountOfProduct (Product product) {
        return this.sellableProductAndCounts.get(product);
    }

    public void sellSellLog (SellLog sellLog) {
        this.thisSellerAllSellLogs.add(sellLog);
        this.sellableProductAndCounts.replace(sellLog.getSoldProduct(), this.sellableProductAndCounts.get(sellLog.getSoldProduct()) - sellLog.getCount());
    }

    public void removeProduct(Product product) {
        sellableProductAndCounts.replace(product,0);
    }

    public void addRequest(Request request){
        this.requests.add(request);
    }

    public Product hasProduct(int id) throws Exceptions.NoProductByThisIdException {
        for (Product product : sellableProductAndCounts.keySet()) {
            if(product.getProductId() == id) {
                return product;
            }
        }
        throw new Exceptions.NoProductByThisIdException(id);
    }

    public int countProcudt(Product product){
        return sellableProductAndCounts.get(product);
    }
}

