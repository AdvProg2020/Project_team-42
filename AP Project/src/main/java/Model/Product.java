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
}
