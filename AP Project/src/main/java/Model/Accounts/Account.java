package Model.Accounts;
import Controller.Exceptions;
import Model.Product;

import java.util.ArrayList;


public abstract class Account {

    private static ArrayList<Account> allAccounts;
    private String userName;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String password;
    private String accountType;
    private double credit;

    public Account(String userName, String firstName, String lastName, String email, String phoneNumber, String password, String accountType) {
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.accountType = accountType;
        this.credit = 0;
    }

    public double getCredit() {
        return credit;
    }

    @Override
    public String toString() {
        return "username : " + userName +
                "\nfirstName : " + firstName +
                "\nlastName : " + lastName +
                "\nemail : " + email +
                "\nphoneNumber : " + phoneNumber +
                "\npassword : " + password +
                "\naccountType : " + accountType +
                "\ncredit : " + credit;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public boolean hasBoughtProduct (Product product) {
        return false;
    }

    public void addProductToCart (Product product, SellerAccount seller) throws Exceptions.NotCustomerException {
        throw new Exceptions.NotCustomerException();
    }
}
