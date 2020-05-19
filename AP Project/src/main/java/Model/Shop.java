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

    public StringBuilder showAllProductsMoudel(){
        StringBuilder sallProducts = new StringBuilder();
        int size = allProducts.size();
        for (int i=0;i<size;i++)
        {
            sallProducts.append(allProducts.get(i).getName() + "   " + allProducts.get(i).getBrand() + "   " + allProducts.get(i).getProductId() + "    " + allProducts.get(i).getAverageRate() + "/n");
        }
        return sallProducts;
    }

    public StringBuilder showAllDiscountsMoudel(){
        StringBuilder sallDiscounts = new StringBuilder();
        int size = allDiscounts.size();
        for (int i=0;i<size;i++)
        {
            sallDiscounts.append(allDiscounts.get(i).getDiscountCode() + "   " + allDiscounts.get(i).getDiscountPercent() + "/n");
        }
        return sallDiscounts;
    }

    public void deleteProductMoudel(long id){
        int size = allProducts.size();
        for (int i =0;i<size;i++)
        {
            if (allProducts.get(i).getProductId() == id)
                allProducts.get(i).value() =0;
        }
    }

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
