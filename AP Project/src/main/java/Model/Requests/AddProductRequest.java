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
}
