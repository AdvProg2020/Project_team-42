package Model;

import Model.Accounts.CustomerAccount;

public class Rate {
    private CustomerAccount user;
    private Product product;
    private int rate;

    public Rate(CustomerAccount user, Product product, int rate) {
        this.user = user;
        this.product = product;
        this.rate = rate;
    }

    public int getRate() {
        return rate;
    }
}
