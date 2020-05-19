package Model;

import Controller.Exceptions;
import Model.Logs.BuyLog;
import Model.Logs.SellLog;

import java.awt.geom.Area;
import java.util.ArrayList;
import java.util.HashMap;

public class Shop {
    private static Shop shop = new Shop();
    private HashMap<Product,Integer> allProductAndCount;
    private HashMap<Product, Integer> allProductOnOffsAndCount;
    private ArrayList<SellLog> allSellLogs;
    private ArrayList<BuyLog> allBuyLogs;
    private ArrayList<Discount> allDiscounts;
    private ArrayList<Category> allCategories;
    private ArrayList<Off> allOffs;

    private Shop() {
        this.allProductAndCount = new HashMap<>();
        this.allProductOnOffsAndCount = new HashMap<>();
        this.allSellLogs = new ArrayList<>();
        this.allBuyLogs = new ArrayList<>();
        this.allDiscounts = new ArrayList<>();
        this.allCategories = new ArrayList<>();
        this.allOffs = new ArrayList<>();
    }

    public ArrayList<SellLog> getAllSellLogs() {
        return allSellLogs;
    }

    public ArrayList<BuyLog> getAllBuyLogs() {
        return allBuyLogs;
    }

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

    public void addBuyLog (BuyLog buyLog) {
        this.allBuyLogs.add(buyLog);
    }

    public void addSellLog (SellLog sellLog) {
        this.allSellLogs.add(sellLog);
    }
}
