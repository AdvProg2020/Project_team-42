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

<<<<<<< HEAD
    public HashMap<Product, Integer> getAllProductAndCount() {
        return allProductAndCount;
    }

    public HashMap<Product, Integer> getAllProductOnOffsAndCount() {
        return allProductOnOffsAndCount;
=======
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
>>>>>>> f46f3e6eddfc03b3e6eb4b37f3a86b5d1f593140
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
