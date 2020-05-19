package Model;

import Controller.Exceptions;
import Model.Logs.BuyLog;
import Model.Logs.SellLog;

import java.util.ArrayList;
import java.util.HashMap;

public class Shop {
    private static Shop shop = new Shop();
    private HashMap<Product,Integer> allProductAndCount = new HashMap<>();
    private HashMap<Product, Integer> allProductOnOffsAndCount = new HashMap<>();

    public HashMap<Product, Integer> getAllProductAndCount() {
        return allProductAndCount;
    }

    public HashMap<Product, Integer> getAllProductOnOffsAndCount() {
        return allProductOnOffsAndCount;
    }

    public ArrayList<SellLog> getAllSellLogs() {
        return allSellLogs;
    }

    public ArrayList<BuyLog> getAllBuyLogs() {
        return allBuyLogs;
    }

    public ArrayList<Discount> getAllDiscounts() {
        return allDiscounts;
    }

    public ArrayList<Off> getAllOffs() {
        return allOffs;
    }

    private ArrayList<SellLog> allSellLogs = new ArrayList<>();
    private ArrayList<BuyLog> allBuyLogs = new ArrayList<>();
    private ArrayList<Discount> allDiscounts = new ArrayList<>();
    private ArrayList<Category> allCategories = new ArrayList<>();
    private ArrayList<Off> allOffs = new ArrayList<>();

    private Shop() {}

    public static Shop getInstance() {
        return shop;
    }

    public Product getProductById (long productId) throws Exceptions.NoProductByThisIdException {
        for (Product product : allProductAndCount.keySet()) {
            if (product.getProductId() == productId)
                return product;
        }
        throw new Exceptions.NoProductByThisIdException(productId);
    }

    public ArrayList<Category> getAllCategories() {
        if(allCategories.isEmpty())
           return null;
        return allCategories;
    }
}
