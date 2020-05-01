package Model;

public class Comment {
    Account user;
    Product product;
    String title;
    String content;
    String state;
    boolean isProductBoughtByUser;

    public Comment(Account user, Product product, String title, String content, String state, boolean isProductBoughtByUser) {
        this.user = user;
        this.product = product;
        this.title = title;
        this.content = content;
        this.state = state;
        this.isProductBoughtByUser = isProductBoughtByUser;
    }

    public Account getUser() {
        return user;
    }

    public void setUser(Account user) {
        this.user = user;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public boolean isProductBoughtByUser() {
        return isProductBoughtByUser;
    }

    public void setProductBoughtByUser(boolean productBoughtByUser) {
        isProductBoughtByUser = productBoughtByUser;
    }
}
