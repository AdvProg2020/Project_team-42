package Model.Accounts;

import Controller.Exceptions;
import Model.Logs.SellLog;
import Model.Off;
import Model.Product;
import Model.Requests.Request;
import Model.Shop;
import com.google.gson.Gson;


import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class SellerAccount extends Account {
  
    private static ArrayList<SellerAccount> allSellerAccounts = new ArrayList<>();
    private String companyOrWorkShopName ;
    private ArrayList<SellLog> thisSellerAllSellLogs;
    private HashMap<Integer,Integer> sellableProductAndCounts;
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
  
    public void setSellableProductAndCounts(HashMap<Product, Integer> sellableProductAndCounts) {
        this.sellableProductAndCounts = new HashMap<>();
        for (Product product : sellableProductAndCounts.keySet()) {
            this.sellableProductAndCounts.put((int) product.getProductId(), sellableProductAndCounts.get(product));
        }
    }

    public static SellerAccount getSellerAccountByUsername (String username) throws Exception {

        for (SellerAccount sellerAccount : SellerAccount.getAllSellerAccounts()) {
            if (sellerAccount.getUserName().equalsIgnoreCase(username))
                return sellerAccount;
        }
        throw new Exception();
    }

    public static ArrayList<SellerAccount> getAllSellerAccounts() {
        return allSellerAccounts;
    }

    public String getCompanyOrWorkShopName() {
        return companyOrWorkShopName;
    }

    public ArrayList<SellLog> getThisSellerAllSellLogs() {
        return thisSellerAllSellLogs;
    }

    public HashMap<Product, Integer> getSellableProductAndCounts() {
        HashMap<Product, Integer> productsAndCount = new HashMap<>();
        for (Integer productId : this.sellableProductAndCounts.keySet()) {
            try {
                productsAndCount.put(Shop.getInstance().getProductById(productId), this.sellableProductAndCounts.get(productId));
            } catch (Exceptions.NoProductByThisIdException ignored) {}
        }
        return productsAndCount;
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
        if (count < this.sellableProductAndCounts.get((int)product.getProductId()))
            return true;
        return false;
    }

    public int getCountOfProduct (Product product) {
        return this.sellableProductAndCounts.get((int)product.getProductId());
    }

    public void sellSellLog (SellLog sellLog) {
        this.thisSellerAllSellLogs.add(sellLog);
        this.sellableProductAndCounts.replace((int)sellLog.getSoldProductId().getProductId(),
                this.sellableProductAndCounts.get((int)sellLog.getSoldProductId().getProductId()) - sellLog.getCount());
    }

    public void removeProduct(Product product) {
        sellableProductAndCounts.replace((int) product.getProductId(),0);
    }

    public void addRequest(Request request){
        this.requests.add(request);
    }

    public Product hasProduct(int id) throws Exceptions.NoProductByThisIdException {
        if (this.sellableProductAndCounts.containsKey(id)) {
            try {
                return Shop.getInstance().getProductByIdd(id);
            } catch (Exception ignored) {}
        }
        throw new Exceptions.NoProductByThisIdException(id);
    }

    public int countProcudt(Product product){
        return sellableProductAndCounts.get((int)product.getProductId());
    }

    public void updateResources () throws IOException {
        Gson gson = new Gson();
        FileWriter fileWriter = new FileWriter("src\\main\\resources\\Accounts\\SellerAccounts\\" + this.userName + ".txt");

        gson.toJson(this, fileWriter);
        fileWriter.close();
    }

    public ArrayList<String> getBuyers(int productId) throws Exceptions.NoProductByThisIdException {
        ArrayList<String> listBuyers = new ArrayList<>();
        boolean isEmpty = false;
        for (SellLog sellLog : this.thisSellerAllSellLogs) {
            if(sellLog.getSoldProductId().equals(productId)){
                listBuyers.add(sellLog.getBuyerUsername());
                isEmpty = true;
            }
        }
        if(isEmpty){
            return listBuyers;
        }else{
            throw new Exceptions.NoProductByThisIdException(productId);
        }
    }
}

