package Model.Requests;

import Model.Accounts.SellerAccount;
import Model.Category;

public SellerAccount getSeller() {
        return seller;
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
        return category;
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
