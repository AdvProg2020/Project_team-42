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
    private String companyOrWorkShopName;
    private ArrayList<SellLog> thisSellerAllSellLogs;
    private HashMap<Integer, Integer> sallableProductAndCounts;
    private ArrayList<Integer> offs;
    private ArrayList<Integer> requests;

    public SellerAccount(String userName, String firstName, String lastName, String email, String phoneNumber, String password, String accountType, String companyOrWorkShopName) {
        super(userName, firstName, lastName, email, phoneNumber, password, accountType);
        this.companyOrWorkShopName = companyOrWorkShopName;
        this.offs = new ArrayList<>();
        this.sallableProductAndCounts = new HashMap<>();
        this.requests = new ArrayList<>();
        this.thisSellerAllSellLogs = new ArrayList<>();
    }

    public void newOff() {
        offs = new ArrayList<>();
    }

    public void newSellableProductAndCount() {
        sallableProductAndCounts = new HashMap<>();
    }

    public void newRequests() {
        requests = new ArrayList<>();
    }

    public void newThisSellerAllSellLogs() {
        thisSellerAllSellLogs = new ArrayList<>();
    }

    public void setSellableProductAndCounts(HashMap<Product, Integer> sellableProductAndCounts) {
        this.sallableProductAndCounts = new HashMap<>();
        for (Product product : sellableProductAndCounts.keySet()) {
            this.sallableProductAndCounts.put((int) product.getProductId(), sellableProductAndCounts.get(product));
        }
    }

    public static SellerAccount getSellerAccountByUsername(String username) throws Exception {

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
        if (sallableProductAndCounts == null)
            sallableProductAndCounts = new HashMap<>();
        for (Integer productId : this.sallableProductAndCounts.keySet()) {
            try {
                productsAndCount.put(Shop.getInstance().getProductById(productId), this.sallableProductAndCounts.get(productId));
            } catch (Exceptions.NoProductByThisIdException ignored) {
            }
        }
        return productsAndCount;
    }

    public void addToSellableProducts(int id , int count)
    {
        this.sallableProductAndCounts.put(id, count);
        try {
            updateResources();
        } catch (IOException ignored) {}
    }

    public ArrayList<Integer> getOffs() {
        return offs;
    }

    public ArrayList<Integer> getRequests() {
        return requests;
    }

    public String showPersonalInfo() {
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

    public String getBalance() {
        return "Balance :" + String.valueOf(super.credit);
    }

    public boolean hasEnoughOfProduct(Product product, int count) {
        if (count < this.sallableProductAndCounts.get((int) product.getProductId()))
            return true;
        return false;
    }

    public int getCountOfProduct(Product product) {
        return this.sallableProductAndCounts.get((int) product.getProductId());
    }

    public void sellSellLog(SellLog sellLog) {
        this.thisSellerAllSellLogs.add(sellLog);
        this.sallableProductAndCounts.replace((int) sellLog.getSoldProductId().getProductId(),
                this.sallableProductAndCounts.get((int) sellLog.getSoldProductId().getProductId()) - sellLog.getCount());
    }

    public void removeProduct(Product product) {
        sallableProductAndCounts.replace((int) product.getProductId(), 0);
    }

    public void addRequest(Request request) {
        this.requests.add(request.getRequestId());

    }

    public Product hasProduct(int id) throws Exceptions.NoProductByThisIdException {
        if (this.sallableProductAndCounts.containsKey(id)) {
            try {
                return Shop.getInstance().getProductByIdd(id);
            } catch (Exception ignored) {
            }
        }
        throw new Exceptions.NoProductByThisIdException(id);
    }

    public int countProcudt(Product product) {
        return sallableProductAndCounts.get((int) product.getProductId());
    }

    public void updateResources() throws IOException {
        Gson gson = new Gson();
        FileWriter fileWriter = new FileWriter("src\\main\\resources\\Accounts\\SellerAccounts\\" + this.userName + ".txt");

        gson.toJson(this, fileWriter);
        fileWriter.close();
    }

    public ArrayList<String> getBuyers(int productId) throws Exceptions.NoProductByThisIdException {
        ArrayList<String> listBuyers = new ArrayList<>();
        boolean isEmpty = false;
        for (SellLog sellLog : this.thisSellerAllSellLogs) {
            if (sellLog.getSoldProductId().equals(productId)) {
                listBuyers.add(sellLog.getBuyerUsername());
                isEmpty = true;
            }
        }
        if (isEmpty) {
            return listBuyers;
        } else {
            throw new Exceptions.NoProductByThisIdException(productId);
        }
    }

    public String sellersName(ArrayList<SellerAccount> sellers){
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0 ; i< sellers.size();i++)
        {
            stringBuilder.append(sellers.get(i).userName+"   ");
        }
        return String.valueOf(stringBuilder);
    }
}

