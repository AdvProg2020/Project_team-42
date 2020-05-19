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
            sallAccounts.append(allAccounts.get(i).accountType + "   " + allAccounts.get(i).userName + "/n");
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
        for (int i=0;i<size;i++) {
            if (allAccounts.get(i).userName . equals(username)){

                     if(!(allAccounts.get(i).accountType . equals("manager")))
                {
                        allAccounts.get(i).setPassword("23");
                }
            }

        }
    }

}
