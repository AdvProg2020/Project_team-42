package Controller.AccountPagesController;
import Controller.Exceptions;
import Model.Accounts.SellerAccount;
import Model.Category;
import Model.Logs.SellLog;
import Model.Off;
import Model.Product;
import Model.Requests.AddProductRequest;
import Model.Requests.CreateOffRequest;
import Model.Requests.Request;
import Model.Shop;

import java.io.IOException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
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

    public String showSaleHistory(){
        StringBuilder stringBuilder = new StringBuilder();
        for (SellLog thisSellerAllSellLog : user.getThisSellerAllSellLogs()) {
            stringBuilder.append(thisSellerAllSellLog + "\n");
        }
        return String.valueOf(stringBuilder);
    }

    public String showSellerProduct(){
        StringBuilder stringBuilder = new StringBuilder();
        HashMap<Product, Integer> sellableProductAndCounts = user.getSellableProductAndCounts();
        for (Product product : sellableProductAndCounts.keySet()) {
            stringBuilder.append("" + product.getProductId() + "    " + product.getName()+"\n");
        }
        return String.valueOf(stringBuilder);
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

    public String showOff(){
        StringBuilder stringBuilder = new StringBuilder();
        for (Integer offId : user.getOffs()) {
            try {
                stringBuilder.append(shop.getOffById(offId)+ "\n");
            } catch (Exceptions.NoOffByThisId noOffByThisId) {}
        }
        return String.valueOf(stringBuilder);
    }

    public void addProduct(boolean isNew ,SellerAccount sellerAccount,String name,int id,int count ,String brand,double price,Category category,String descrption,String arrtibute)throws Exceptions.NoCategoryException{
        if(!shop.isCategory(category.getName())){
          throw new Exceptions.NoCategoryException();
        }
        Request request;
         user.addRequest(request = new AddProductRequest(isNew,false,sellerAccount,name,id,count,brand, (int) price,category,descrption,arrtibute));
        try {
            request.updateResources();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void editProduct(SellerAccount sellerAccount,String name,int id,int count ,String brand,double price,Category category,String descrption,String arrtibute)throws Exceptions.NoCategoryException{
        if(!shop.isCategory(category.getName())){
          throw new Exceptions.NoCategoryException();
        }
        Request request;
         user.addRequest(request = new AddProductRequest(false,true ,sellerAccount,name,id,count,brand, (int) price,category,descrption,arrtibute));
        try {
            request.updateResources();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void removeProduct(int id) throws Exceptions.NoProductByThisIdException {
        user.removeProduct(shop.getProductById(id));
    }

    public void createOffRequest(ArrayList<Product> listOfProduct, GregorianCalendar start,GregorianCalendar end, double persentage){
        CreateOffRequest request = new CreateOffRequest(this.user.getUserName(),false, null, listOfProduct, start, end, persentage);

    }
    public void editOffRequest(int id,ArrayList<Product> listofProduct,GregorianCalendar start, GregorianCalendar end, double persentage) throws Exceptions.NoOffByThisId {
        new CreateOffRequest(this.user.getUserName(),true, shop.getOffById(id), listofProduct, start, end, persentage);

    }
}

