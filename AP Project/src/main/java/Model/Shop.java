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
  
}
