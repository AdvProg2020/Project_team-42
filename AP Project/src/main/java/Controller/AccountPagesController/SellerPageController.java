package Controller.AccountPagesController;
import Controller.Exceptions;
import Model.Accounts.Account;
import Model.Accounts.SellerAccount;
import Model.Category;
import Model.Logs.SellLog;
import Model.Off;
import Model.Product;
import Model.Requests.AddProductRequest;
import Model.Shop;

import java.util.HashMap;

public class SellerPageController extends AccountPageController {
    private static SellerPageController sellerPageController = new SellerPageController();
    private SellerAccount user;
    private Shop shop = Shop.getInstance();

    public void setUser(SellerAccount user) {
        this.user = user;
        AccountPageController.user = user;
    }

    private SellerPageController() {

    }

    public static SellerPageController getInstance() {
        return sellerPageController;
    }

    public String getComponyinfo() {
        return user.getCompanyOrWorkShopName();
    }

    public String getPersonalInformation(){
        return user.showPersolanInfo();
    }

    public String showBalance(){
         return user.getBalance();
    }

    public void showSaleHistory(){
        for (SellLog thisSellerAllSellLog : user.getThisSellerAllSellLogs()) {
            System.out.println(thisSellerAllSellLog);
        }
    }

    public void showSellerProduct(){
        HashMap<Product, Integer> sellableProductAndCounts = user.getSellableProductAndCounts();
       // sellableProductAndCounts.

    }

    public void removeProduct(){}

    public void showCategories() {
        if (shop.getAllCategories().isEmpty())
        for (Category category : shop.getAllCategories()) {
            System.out.println(category);
        }
    }

    public void showOff(){
        for (Off off : user.getOffs()) {
            System.out.println(off);
        }
    }

    public void addProduct(SellerAccount sellerAccount,String name,int id,int count ,String brand,double price,Category category,String descrption,String arrtibute)throws Exceptions.NoCategoryException{
        if(!shop.isCategory(category.getName())){
          throw new Exceptions.NoCategoryException();
        }
         user.addRequest(new AddProductRequest(sellerAccount,name,id,count,brand,price,category,descrption,arrtibute));

    }

    public void removeProduct(int id) throws Exceptions.NoProductByThisIdException {
        user.removeProduct(shop.getProductById(id));
        shop.removeProduct(shop.getProductById(id),user.getCountOfProduct(shop.getProductById(id)));
    }

    public SellerAccount getUser(){
        return this.user;
    }
}

