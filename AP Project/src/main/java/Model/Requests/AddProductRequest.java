package Model.Requests;

import Model.Accounts.SellerAccount;
import Model.Category;

public class AddProductRequest extends Request {
    private SellerAccount seller;
    private String name;
    private int productId;
    private int count;
    private String brand;
    private int price;
    private Category category;
    private String description;
    private String attribute;
    
       public AddProductRequest(SellerAccount seller, String name, int productId, int count, String brand, double price, Category category, String description, String attribute) {
        this.seller = seller;
        this.name = name;
        this.productId = productId;
        this.count = count;
        this.brand = brand;
        this.price = price;
        this.category = category;
        this.description = description;
        this.attribute = attribute;
    }
}
