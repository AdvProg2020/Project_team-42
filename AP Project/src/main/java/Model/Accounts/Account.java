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

    public String getAllAccount(){
        StringBuilder sallAccounts = new StringBuilder() ;
        String accountUserName;
        Account account;
        String accountType;
        int size = allAccounts.size();
        for (int i = 0 ; i <= size-1 ; i++)
        {
            account = allAccounts.get(i);
            accountUserName = account.userName;
            accountType = account.accountType;
            sallAccounts.appened(accountUserName + "  " + accountType + "/n");
        }
        return sallAccounts;
    }

}
