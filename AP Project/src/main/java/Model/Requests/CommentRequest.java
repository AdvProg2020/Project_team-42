package Model.Requests;

import Model.Accounts.Account;
import Model.Product;

public class CommentRequest extends Request {
    private Account user;
    private Product product;
    private String title;
    private String content;
    private boolean isProductBoughtByUser;

    public CommentRequest(Account user, Product product, String title, String content, boolean isProductBoughtByUser) {
        this.user = user;
        this.product = product;
        this.title = title;
        this.content = content;
        this.isProductBoughtByUser = isProductBoughtByUser;
    }
    
    @Override
    public String toString() {
        return "CommentRequest{" +
                "user=" + user +
                ", product=" + product +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", isProductBoughtByUser=" + isProductBoughtByUser +
                '}';
    }

    public Account getUser() {
        return user;
    }

    public Product getProduct() {
        return product;
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
}
