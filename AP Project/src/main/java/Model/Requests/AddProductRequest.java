package Model.Requests;

import Controller.Exceptions;
import Model.Accounts.SellerAccount;
import Model.Category;
import Model.Shop;
import com.google.gson.Gson;

import java.io.FileWriter;
import java.io.IOException;


public class AddProductRequest extends Request {
    private String seller;
    private String name;
    private int productId;
    private int count;
    private String brand;
    private int price;
    private String category;
    private String description;
    private String attribute;
    private boolean isForEdit;
       public AddProductRequest(boolean isForEdit,SellerAccount seller, String name, int productId, int count, String brand, int price, Category category, String description, String attribute) {
        this.isForEdit = isForEdit;
        this.seller = seller.getUserName();
        this.name = name;
        this.productId = productId;
        this.count = count;
        this.brand = brand;
        this.price = price;
        this.category = category.getName();
        this.description = description;
        this.attribute = attribute;
    }

    public boolean isForEdit() {
        return isForEdit;
    }

    public void setForEdit(boolean forEdit) {
        isForEdit = forEdit;
    }

    public SellerAccount getSeller() {
        try {
            return SellerAccount.getSellerAccountByUsername(seller);
        } catch (Exception e) {
            return null;
        }
    }

    public String getName() {
        return name;
    }

    public int getProductId() {
        return productId;
    }

    public int getCount() {
        return count;
    }

    public String getBrand() {
        return brand;
    }

    public int getPrice() {
        return price;
    }

    public Category getCategory() {
        try {
            return Shop.getInstance().getCategoryByName(category);
        } catch (Exceptions.NoCategoryException e) {
            return null;
        }
    }

    public String getDescription() {
        return description;
    }

    public String getAttribute() {
        return attribute;
    }

    @Override
    public String toString() {
        return "AddProductRequest{" +
                "seller=" + seller +
                ", name='" + name + '\'' +
                ", productId=" + productId +
                ", count=" + count +
                ", brand='" + brand + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", attribute='" + attribute + '\'' +
                '}';
    }

    public void updateResources () throws IOException {
        Gson gson = new Gson();
        FileWriter fileWriter = new FileWriter("src\\main\\resources\\Requests\\AddProductRequests" + this.requestId + ".txt");

        gson.toJson(this, fileWriter);
        fileWriter.close();
    }
}
