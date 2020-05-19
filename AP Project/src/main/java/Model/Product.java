package Model;

import Model.Accounts.SellerAccount;

import java.util.ArrayList;
import java.util.HashMap;

public class Product {
    private long productId;
    private String name;
    private String brand;
    private OffOrProductState state;
    private HashMap<SellerAccount, Integer> sellerAndPrice;
    private Category category;
    private String attribute;
    private String description;
    private double averageRate;
    private Off off;
    private ArrayList<Comment> comments;
    private ArrayList<Rate> rates;

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public OffOrProductState getState() {
        return state;
    }

    public void setState(OffOrProductState state) {
        this.state = state;
    }

    public HashMap<SellerAccount, Integer> getSellerAndPrice() {
        return sellerAndPrice;
    }

    public void setSellerAndPrice(HashMap<SellerAccount, Integer> sellerAndPrice) {
        this.sellerAndPrice = sellerAndPrice;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getAverageRate() {
        return averageRate;
    }

    public void setAverageRate(double averageRate) {
        this.averageRate = averageRate;
    }

    public Off getOff() {
        return off;
    }

    public void setOff(Off off) {
        this.off = off;
    }

    public ArrayList<Comment> getComments() {
        return comments;
    }

    public void setComments(ArrayList<Comment> comments) {
        this.comments = comments;
    }

    public ArrayList<Rate> getRates() {
        return rates;
    }

    public void setRates(ArrayList<Rate> rates) {
        this.rates = rates;
    }
}
