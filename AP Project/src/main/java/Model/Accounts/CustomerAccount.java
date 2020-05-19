package Model.Accounts;
import Controller.Exceptions;
import Model.Discount;
import Model.Logs.BuyLog;
import Model.Product;

import java.util.ArrayList;
import java.util.HashMap;

public class CustomerAccount extends Account {

    private static ArrayList<CustomerAccount> allCustomerAccounts = new ArrayList<CustomerAccount>();
    private HashMap<Product, HashMap<SellerAccount, Integer>> cart;
    private ArrayList<BuyLog> thisCustomerBuyLogs;
    private HashMap<Discount, Integer> discountCodeAndUseCount;

    public CustomerAccount(String userName, String firstName, String lastName, String email, String phoneNumber, String password, String accountType) {
        super(userName, firstName, lastName, email, phoneNumber, password, accountType);
        this.cart = new HashMap<>();
        this.thisCustomerBuyLogs = new ArrayList<>();
        this.discountCodeAndUseCount = new HashMap<>();
    }

    public HashMap<Product, HashMap<SellerAccount, Integer>> getCart() {
        return cart;
    }

    public ArrayList<BuyLog> getBuyLogs() {
        return thisCustomerBuyLogs;
    }

    public HashMap<Discount, Integer> getDiscountCodeAndUseCount() {
        return discountCodeAndUseCount;
    }

    public void deleteCustomerUser(String username) {
        int size = allCustomerAccounts.size();
        for (int i = 0; i < size; i++) {

            if (allCustomerAccounts.get(i).getUserName().equals(username))
                allCustomerAccounts.get(i).setPassword("23");

        }
    }

    public boolean hasBoughtProduct(Product product) {
        for (BuyLog buyLog : this.thisCustomerBuyLogs) {
            if (buyLog.getBoughtProducts().contains(product))
                return true;
        }
        return false;
    }

    public Discount getDiscountByCode (String discountCode) throws Exceptions.NoDiscountByCodeException {
        for (Discount discount : this.discountCodeAndUseCount.keySet()) {
            if (discountCode.equals(discount.getDiscountCode()))
                return discount;
        }
        throw new Exceptions.NoDiscountByCodeException();
    }

    public Product getProductFromBuyLogsById (long id) throws Exceptions.NotBoughtProductByIdException {
        for (BuyLog buyLog : this.thisCustomerBuyLogs) {
            for (Product product : buyLog.getBoughtProducts()) {
                if (product.getProductId() == id)
                    return product;
            }
        }
        throw new Exceptions.NotBoughtProductByIdException();
    }

    public void purchaseBuyLog (BuyLog buyLog) {
        this.thisCustomerBuyLogs.add(buyLog);
        this.credit -= buyLog.getPayedMoney();
    }
}
