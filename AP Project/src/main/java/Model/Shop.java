package Model;

import Model.Logs.BuyLog;
import Model.Logs.SellLog;

import java.util.ArrayList;
import java.util.HashMap;

public class Shop {
  
    private HashMap<Product,Integer> allProductAndCount;
    private ArrayList<Product> allProducts;
    private ArrayList<Product> allProductOnOffs;
    private ArrayList<SellLog> allSellLogs;
    private ArrayList<BuyLog> allBuyLogs;
    private ArrayList<Discount> allDiscounts;
    private ArrayList<Category> allCategories;
    private ArrayList<Off> allOffs;
  
}
