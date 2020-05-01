package Model;

public class Rate {
    CustomerAccount user;
    Product product;
    double rate;

    public rate(CustomerAccount user, Product product, double rate) {
        this.user = user;
        this.product = product;
        this.rate = rate;
    }

    public CustomerAccount getUser() {
        return user;
    }

    public void setUser(CustomerAccount user) {
        this.user = user;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }
}
