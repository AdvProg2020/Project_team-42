package Model;

import Model.Accounts.Account;

public class Comment {
    private Account user;
    private Product product;
    private String title;
    private String content;
    private boolean isProductBoughtByUser;

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }
     public Comment(Account user, Product product, String title, String content, boolean isProductBoughtByUser) {
        this.user = user;
        this.product = product;
        this.title = title;
        this.content = content;
        this.isProductBoughtByUser = isProductBoughtByUser;
    }

    public Account getUser() {
        return user;
    }

    public Product getProduct() {
        return product;
    }

    public boolean isProductBoughtByUser() {
        return isProductBoughtByUser;
    }
}
