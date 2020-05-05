package Model.Accounts;
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

    public static Account getAccountByUserName(String userName){
        for (Account account : allAccounts) {
            if(account.userName.equals(userName))
            {
                return account;
            }
        }
        return null;
    }

    public static Account getphoneNubmberByUserName(String userName){
        for (Account account : allAccounts) {
            if(account.userName.equals(userName))
            {
                return account;
            }
        }
        return null;
    }

    public static Account getEmailByUserName(String userName){
        for (Account account : allAccounts) {
            if(account.userName.equals(userName))
            {
                return account;
            }
        }
        return null;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public double getCredit() {
        return credit;
    }

    public void setCredit(double credit) {
        this.credit = credit;
    }

}
