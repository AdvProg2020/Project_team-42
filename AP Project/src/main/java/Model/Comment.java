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
}
