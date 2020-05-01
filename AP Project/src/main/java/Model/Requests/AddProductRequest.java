package Model.Requests;
import java.util.ArrayList;
import java.util.Date;

public class AddProductRequest extends Request {

    public AddProductRequest(ArrayList<Request> unAnsweredRequests, ArrayList<Request> answeredRequests, int requestId, Date requestDate, Date answerDate, Manager responsiveManager, RequestState requestState) {
        super(unAnsweredRequests, answeredRequests, requestId, requestDate, answerDate, responsiveManager, requestState);
    }

    SellerAcount seller;
    String name;
    int productId;
    int count;
    String brand;
    int price;
    Category category;
    String discription;
    String arrtibute;
    String state;

    public AddProductRequest(ArrayList<Request> unAnsweredRequests, ArrayList<Request> answeredRequests, int requestId, Date requestDate, Date answerDate, Manager responsiveManager, RequestState requestState, SellerAcount seller, String name, int productId, int count, String brand, int price, Category category, String discription, String arrtibute, String state) {
        super(unAnsweredRequests, answeredRequests, requestId, requestDate, answerDate, responsiveManager, requestState);
        this.seller = seller;
        this.name = name;
        this.productId = productId;
        this.count = count;
        this.brand = brand;
        this.price = price;
        this.category = category;
        this.discription = discription;
        this.arrtibute = arrtibute;
        this.state = state;
    }

    public SellerAcount getSeller() {
        return seller;
    }

    public void setSeller(SellerAcount seller) {
        this.seller = seller;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    public String getArrtibute() {
        return arrtibute;
    }

    public void setArrtibute(String arrtibute) {
        this.arrtibute = arrtibute;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}

