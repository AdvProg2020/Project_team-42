package Model.Requests;

import Controller.Exceptions;
import Model.Accounts.Account;
import Model.Product;
import Model.Shop;
import com.google.gson.Gson;

import java.io.FileWriter;
import java.io.IOException;

public class CommentRequest extends Request {
    private String user;
    private int productId;
    private String title;
    private String content;
    private boolean isProductBoughtByUser;

    public CommentRequest(Account user, Product product, String title, String content, boolean isProductBoughtByUser) {
        this.user = user.getUserName();
        this.productId = (int) product.getProductId();
        this.title = title;
        this.content = content;
        this.isProductBoughtByUser = isProductBoughtByUser;
    }
    
    @Override
    public String toString() {
        return "CommentRequest{" +
                "user=" + user +
                ", product=" + productId +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", isProductBoughtByUser=" + isProductBoughtByUser +
                '}';
    }

    public Account getUser() {
        try {
            return Account.getAccountByUsername(user);
        } catch (Exception e) {
            return null;
        }
    }

    public Product getProduct() {
        try {
            return Shop.getInstance().getProductById(productId);
        } catch (Exceptions.NoProductByThisIdException e) {
            return null;
        }
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public boolean isProductBoughtByUser() {
        return isProductBoughtByUser;
    }

    public void updateResources () throws IOException {
        Gson gson = new Gson();
        FileWriter fileWriter = new FileWriter("src\\main\\resources\\Requests\\CommentRequests\\" + this.requestId + ".txt");

        gson.toJson(this, fileWriter);
        fileWriter.close();
    }
}
