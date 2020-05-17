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

    public StringBuilder getAllAccount(){
        StringBuilder sallAccounts = new StringBuilder() ;
        String accountUserName;
        Account account;
        String saccountType;
        int size = allAccounts.size();
        for (int i = 0 ; i <= size-1 ; i++)
        {
            account = allAccounts.get(i);
            accountUserName = account.userName;
            saccountType = account.accountType;
            sallAccounts.append(saccountType + "   " + accountUserName + "/n");
        }
        return sallAccounts;
    }

    public String getAccountByUserName(String username){
        Account account = null;
        int i=0;
        int size = allAccounts.size();
        while (i<size) {
            account = allAccounts.get(i);
            if (account.userName . equals(username)){
                break;
            }

        }
        return String.valueOf(account);
    }

    public String getPhoneNumberByUserName(String username){
        String phonenumber = null;
        Account account = null;
        int i=0;
        int size = allAccounts.size();
        while (i<size) {
            account = allAccounts.get(i);
            if (account.userName . equals(username)){
                phonenumber = account.phoneNumber;
                break;
            }

        }
        return phonenumber;
    }

    public String getEmailByUserName(String username){
        String semail = null;
        Account account = null;
        int i=0;
        int size = allAccounts.size();
        while (i<size) {
            account = allAccounts.get(i);
            if (account.userName . equals(username)){
                semail = account.email;
                break;
            }

        }
        return semail;
    }

    public void deleteUser(String username){
        Account account = null;
        int i=0;
        int size = allAccounts.size();
        while (i<size) {
            account = allAccounts.get(i);
            if (account.userName . equals(username)){
                if(account.accountType . equals("manager"))
                {

                }
                if(account.accountType . equals("customer"))
                {

                }
                if(account.accountType . equals("seller"))
                {

                }
            }

        }
    }

}
