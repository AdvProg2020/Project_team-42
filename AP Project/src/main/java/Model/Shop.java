package Model;

import Controller.Exceptions;
import Model.Logs.BuyLog;
import Model.Logs.SellLog;

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

    public HashMap<Product, Integer> getAllProductAndCount() {
        return allProductAndCount;
    }

    public HashMap<Product, Integer> getAllProductOnOffsAndCount() {
        return allProductOnOffsAndCount;
    }

    public StringBuilder showAllProductsMoudel(){
        StringBuilder sallProducts = new StringBuilder();
        for (Product product : allProductAndCount.keySet()) {
            sallProducts.append(product.getName() + "   " + product.getBrand() + "   " + product.getProductId()
                    + "    " + product.getAverageRate() + "\n");
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

    public void deleteProductMoudel(long id) throws Exceptions.NoProductByThisIdException {
        allProductAndCount.replace(getProductById(id), 0);
    }

    public ArrayList<Discount> getAllDiscounts() {
        return allDiscounts;
    }

    public ArrayList<Off> getAllOffs() {
        return allOffs;
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

    public ArrayList<Category> getAllCategories() {
        if(allCategories.isEmpty())
           return null;
        return allCategories;
    }

    public ArrayList<SellLog> getAllSellLogs() {
        return allSellLogs;
    }

    public ArrayList<BuyLog> getAllBuyLogs() {
        return allBuyLogs;
    }

    public void addBuyLog (BuyLog buyLog) {
        this.allBuyLogs.add(buyLog);
    }

    public void addSellLog (SellLog sellLog) {
        this.allSellLogs.add(sellLog);
    }
    
        public void removeProduct(Product product , int count) {
        allProductAndCount.replace(product , allProductAndCount.get(product)-count);
        for (Product productOnOFF : allProductOnOffsAndCount.keySet()) {
            if(product == productOnOFF) {
                allProductOnOffsAndCount.replace(productOnOFF,allProductOnOffsAndCount.get(productOnOFF)-count);
            }
        }
    }

    public boolean isCategory(String name){
        for (Category category : allCategories) {
            if(category.getName().equals(name)){
                return true;
            }
        }
        return false;
    }
    
     public Category getCategoryByName(String categoryName)throws Exceptions.NoCategoryException {
        for (Category category : allCategories) {
            if(category.getName().equals(categoryName))
                return category;
        }
        throw new Exceptions.NoCategoryException();
    }
}
