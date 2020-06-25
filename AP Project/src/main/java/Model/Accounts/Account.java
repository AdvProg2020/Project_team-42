package Model.Accounts;
import Controller.Exceptions;
import Model.Product;

import java.io.IOException;
import java.util.ArrayList;


public abstract class Account {

    protected static ArrayList<Account> allAccounts = new ArrayList<>();
    protected String userName;
    protected String firstName;
    protected String lastName;
    protected String email;
    protected String phoneNumber;
    protected String password;
    protected String accountType;
    protected double credit;

    public Account(String userName, String firstName, String lastName, String email, String phoneNumber, String password, String accountType) {
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.accountType = accountType;
        this.credit = 0;

        try {
            updateResources();
        } catch (IOException e) {}
    }

    public double getCredit() {
        return credit;
    }

    public static Account getAccountByUsername (String username) throws Exception {
        for (Account account : allAccounts) {
            if (account.userName.equalsIgnoreCase(username))
                return account;
        }
        throw new Exception();
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

    public static ArrayList<Account> getAllAccounts() {
        return allAccounts;
    }

    public static void setAllAccounts(ArrayList<Account> allAccounts) {
        Account.allAccounts = allAccounts;
    }

    public StringBuilder getAllAccountsMoudel(){
        StringBuilder sallAccounts = new StringBuilder() ;
        int size = allAccounts.size();
        for (int i = 0 ; i <= size-1 ; i++)
        {
            sallAccounts.append(allAccounts.get(i).accountType + "   " + allAccounts.get(i).userName + "\n");
        }
        return sallAccounts;
    }

    public String getAccountByUserNameMoudel(String username){
        int asize = allAccounts.size();
        int i=0;
        for (;i < asize ;i++) {
            if (allAccounts.get(i).userName . equals(username)){
                break;
            }

        }
        return String.valueOf(allAccounts.get(i));
    }

    public StringBuilder getAllPhoneNumbers(){
        StringBuilder sphoneNumbers = new StringBuilder() ;
        int size = allAccounts.size();
        for (int i = 0 ; i <= size-1 ; i++)
        {
            sphoneNumbers.append(allAccounts.get(i).userName + "   " + allAccounts.get(i).phoneNumber + "/n");
        }
        return sphoneNumbers;
    }

    public StringBuilder getAllEmails(){
        StringBuilder semails = new StringBuilder() ;
        int size = allAccounts.size();
        for (int i = 0 ; i <= size-1 ; i++)
        {
            semails.append(allAccounts.get(i).userName + "   " + allAccounts.get(i).email + "/n");
        }
        return semails;
    }

    public void deleteUserMoudel(String username){
        int size = allAccounts.size();
        for (Account allAccount : allAccounts) {
            if (allAccount.userName.equals(username)) {

                if (!(allAccount.accountType.equals("manager"))) {
                    allAccount.setPassword("23");
                }
            }
            try {
                allAccount.updateResources();
            } catch (IOException ignored) {}
        }
    }

    public boolean hasBoughtProduct (Product product) {
        return false;
    }
    
    public String getPassword() {
        return password;
    }

    public abstract void updateResources() throws IOException;
}
