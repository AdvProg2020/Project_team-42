package Model;

import Controller.Exceptions;
import Model.Accounts.SellerAccount;
import com.google.gson.Gson;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Product {
    private long productId;
    private String name;
    private String brand;
    private OffOrProductState state;
    private int price;
    private String category;
    private String attribute;
    private String description;
    private double averageRate;
    private HashMap<String, Integer> sellersAndOff;
    private ArrayList<Comment> comments;
    private ArrayList<Rate> rates;
    private int visit;
    
    public long getProductId() {
        return productId;
    }

    public void setPrice(int price) {
        this.price = price;
        try {
            this.updateResources();
        } catch (IOException ignored) {}
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        try {
            this.updateResources();
        } catch (IOException ignored) {}
    }

    public Product(long productId,String name, String brand, int price, Category category, String attribute, String description, SellerAccount firstSeller) {
        this.productId = productId;
        this.name = name;
        this.brand = brand;
        this.price = price;
        this.category = category.getName();
        this.attribute = attribute;
        this.description = description;
        this.averageRate = 0;
        this.sellersAndOff = new HashMap<>();
        this.sellersAndOff.put(firstSeller.getUserName(), -1);
        this.comments = new ArrayList<>();
        this.rates = new ArrayList<>();
        this.visit = 0;

        try {
            updateResources();
        } catch (IOException ignored) {}
    }
    
    public int getVisit() {
        return this.visit;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
        try {
            this.updateResources();
        } catch (IOException ignored) {}
    }

    public OffOrProductState getState() {
        return state;
    }

    public void setState(OffOrProductState state) {
        this.state = state;
        try {
            this.updateResources();
        } catch (IOException ignored) {}
    }

    public Category getCategory() {
        try {
            return Shop.getInstance().getCategoryByName(this.category);
        } catch (Exceptions.NoCategoryException e) {
            return null;
        }
    }

    public void setCategory(Category category) {
        this.category = category.getName();

        try {
            updateResources();
        } catch (IOException ignored) {}
    }

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
        try {
            this.updateResources();
        } catch (IOException ignored) {}
    }

    public SellerAccount getSellerByUsername (String userName) throws Exceptions.NoSellerByThisUserNameForProductException {
        if (this.sellersAndOff.containsKey(userName)) {
            try {
                return SellerAccount.getSellerAccountByUsername(userName);
            } catch (Exception ignored) {}
        }
        throw new Exceptions.NoSellerByThisUserNameForProductException();
    }

    public String getDescription() {
        return description;
    }

    public int getPrice() {
        return price;
    }

    public ArrayList<SellerAccount> getSellers () {
        ArrayList<SellerAccount> sellerAccounts = new ArrayList<>();
        for (String sellerUsername : this.sellersAndOff.keySet()) {
            try {
                sellerAccounts.add(SellerAccount.getSellerAccountByUsername(sellerUsername));
            } catch (Exception ignored) {}
        }

        return sellerAccounts;
    }

    public ArrayList<Comment> getComments () throws Exceptions.NoCommentsException {
        if (this.comments.isEmpty())
            throw new Exceptions.NoCommentsException();

        try {
            updateResources();
        } catch (IOException ignored) {}

        return this.comments;
    }

    public void setDescription(String description) {
        this.description = description;
        try {
            this.updateResources();
        } catch (IOException ignored) {}
    }

    public double getAverageRate() {
        return averageRate;
    }

    public double getOff(SellerAccount seller) throws Exceptions.NoOffForThisProductException {
        if (this.sellersAndOff.get(seller.getUserName()) != -1) {
            try {
                if ((Shop.getInstance().getOffById(this.sellersAndOff.get(seller.getUserName()))).isUsable())
                    return (Shop.getInstance().getOffById(this.sellersAndOff.get(seller.getUserName()))).getOffPercentage();
            } catch (Exceptions.NoOffByThisId ignored) {}
            this.sellersAndOff.remove(seller.getUserName());
        }
        throw new Exceptions.NoOffForThisProductException();
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            stringBuilder.append("Category     : " + this.category +
                               "\n\tCategory attribute : " + Shop.getInstance().getCategoryByName(this.category).getAttribute() +
                               "\nName         : " + this.name +
                               "\nBrand        : " + this.brand +
                               "\nDescription  : " + this.description +
                               "\nAttribute    : " + this.attribute +
                               "\nAverage rate : " + this.averageRate +
                               "\nPrice        : " + this.price +
                               "\nSellers      : ");
        } catch (Exceptions.NoCategoryException e) {
            return null;
        }

        for (String sellerUsername : this.sellersAndOff.keySet()) {
            SellerAccount seller = null;
            try {
                seller = getSellerByUsername(sellerUsername);
            } catch (Exceptions.NoSellerByThisUserNameForProductException ignored) {}
            try {
                stringBuilder.append("\n\t" + sellerUsername + " by " + this.getOff(seller) + "% off");
            } catch (Exceptions.NoOffForThisProductException e) {
                stringBuilder.append("\n\t" + sellerUsername);
            }
        }
        return stringBuilder.toString();
    }

    public void addRate (Rate rate) {
        this.rates.add(rate);
        this.averageRate = (this.averageRate * (this.rates.size() - 1) + rate.getRate()) / this.rates.size();

        try {
            updateResources();
        } catch (IOException ignored) {}
    }
    
    public void setComments(ArrayList<Comment> comments) {
        this.comments = comments;
        try {
            this.updateResources();
        } catch (IOException ignored) {}
    }

    public void updateResources () throws IOException {
        Gson gson = new Gson();
        FileWriter fileWriter = new FileWriter("src\\main\\resources\\Products\\" + this.productId + ".txt");

        gson.toJson(this, fileWriter);
        fileWriter.close();
    }
}
