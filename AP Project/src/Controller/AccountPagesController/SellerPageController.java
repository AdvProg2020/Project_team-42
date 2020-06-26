package Controller.AccountPagesController;

import Controller.Exceptions;
import Model.Accounts.SellerAccount;
import Model.Category;
import Model.Logs.SellLog;
import Model.Product;
import Model.Requests.AddProductRequest;
import Model.Requests.Request;
import Model.Shop;

import java.io.IOException;
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
        return user.showPersonalInfo();
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
        for (Product product : sellableProductAndCounts.keySet()) {
            System.out.println("" + product.getProductId() + "    " + product.getName());
        }
    }

    public void viewBuyers(int productId) throws Exceptions.NoProductByThisIdException {
        for (String buyer : user.getBuyers(productId)) {
            System.out.println(buyer + "\n");
        }
    }

    public Product viewProduct(int productId) throws Exceptions.NoProductByThisIdException {
        for (Product product : user.getSellableProductAndCounts().keySet()) {
            if(product.getProductId() == productId){
                return product;
            }
        }
        throw new Exceptions.NoProductByThisIdException(productId);
    }

    public void showCategories() throws Exception {
        if (shop.getAllCategories().isEmpty())
        for (Category category : shop.getAllCategories()) {
            System.out.println(category);
        }
    }

    public void showOff(){
        for (Integer offId : user.getOffs()) {
            try {
                System.out.println(shop.getOffById(offId));
            } catch (Exceptions.NoOffByThisId noOffByThisId) {}
        }
    }

    public void addProduct(boolean isNew ,SellerAccount sellerAccount,String name,int id,int count ,String brand,double price,Category category,String descrption,String arrtibute)throws Exceptions.NoCategoryException{
        if(!shop.isCategory(category.getName())){
          throw new Exceptions.NoCategoryException();
        }
        Request request;
         user.addRequest(request = new AddProductRequest(isNew,false,sellerAccount,name,id,count,brand, (int) price,category,descrption,arrtibute));
        /*try {
            request.updateResources();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }

    public void editProduct(SellerAccount sellerAccount,String name,int id,int count ,String brand,double price,Category category,String descrption,String arrtibute)throws Exceptions.NoCategoryException{
        if(!shop.isCategory(category.getName())){
          throw new Exceptions.NoCategoryException();
        }
        Request request;
         user.addRequest(request = new AddProductRequest(false,true ,sellerAccount,name,id,count,brand, (int) price,category,descrption,arrtibute));
        /*try {
            request.updateResources();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }

    public void removeProduct(int id) throws Exceptions.NoProductByThisIdException {
        user.removeProduct(shop.getProductById(id));
        shop.removeProduct(shop.getProductById(id),user.getCountOfProduct(shop.getProductById(id)));
    }

}

