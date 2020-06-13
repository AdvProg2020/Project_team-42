package Model;

import Controller.Exceptions;
import Model.Accounts.SellerAccount;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class Product {
    private long productId;
    private String name;
    private String brand;
    private OffOrProductState state;
    private int price;
    private Category category;
    private String attribute;
    private String description;
    private double averageRate;
    private HashMap<SellerAccount, Off> sellersAndOff;
    private ArrayList<Comment> comments;
    private ArrayList<Rate> rates;
    private int visit;
    
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

    public Product(long productId,String name, String brand, int price, Category category, String attribute, String description, SellerAccount firstSeller) {
        this.productId = productId;
        this.name = name;
        this.brand = brand;
        this.price = price;
        this.category = category;
        this.attribute = attribute;
        this.description = description;
        this.averageRate = 0;
        this.sellersAndOff = new HashMap<>();
        this.sellersAndOff.put(firstSeller, null);
        this.comments = new ArrayList<>();
        this.rates = new ArrayList<>();
        this.visit = 0;
    }
    
    public int getVisit() {
        return this.visit;
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

    public SellerAccount getSellerByUsername (String userName) throws Exceptions.NoSellerByThisUserNameForProductException {
        for (SellerAccount seller : this.sellersAndOff.keySet()) {
            if (seller.getUserName().equals(userName))
                return seller;
        }
        throw new Exceptions.NoSellerByThisUserNameForProductException();
    }

    public String getDescription() {
        return description;
    }

    public int getPrice() {
        return price;
    }

    public Set<SellerAccount> getSellers () {
        return this.sellersAndOff.keySet();
    }

    public ArrayList<Comment> getComments () throws Exceptions.NoCommentsException {
        if (this.comments.isEmpty())
            throw new Exceptions.NoCommentsException();
        return this.comments;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getAverageRate() {
        return averageRate;
    }

    public double getOff(SellerAccount seller) throws Exceptions.NoOffForThisProductException {
        if (this.sellersAndOff.get(seller) != null) {
            if (this.sellersAndOff.get(seller).isUsable())
                return this.sellersAndOff.get(seller).getOffPercentage();
            this.sellersAndOff.remove(seller);
        }
        throw new Exceptions.NoOffForThisProductException();
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Category     : " + this.category.getName() +
                           "\n\tCategory attribute : " + this.category.getAttribute() +
                           "\nName         : " + this.name +
                           "\nBrand        : " + this.brand +
                           "\nDescription  : " + this.description +
                           "\nAttribute    : " + this.attribute +
                           "\nAverage rate : " + this.averageRate +
                           "\nPrice        : " + this.price +
                           "\nSellers      : ");

        for (SellerAccount seller : this.sellersAndOff.keySet()) {
            try {
                stringBuilder.append("\n\t" + seller.getUserName() + " by " + this.getOff(seller) + "% off");
            } catch (Exceptions.NoOffForThisProductException e) {
                stringBuilder.append("\n\t" + seller.getUserName());
            }
        }
        return stringBuilder.toString();
    }

    public void addRate (Rate rate) {
        this.rates.add(rate);
        this.averageRate = (this.averageRate * (this.rates.size() - 1) + rate.getRate()) / this.rates.size();
    }
    
    public void setComments(ArrayList<Comment> comments) {
        this.comments = comments;
    }
}
