package Model.Requests;

public class AddSellerAccountRequest extends Request {
    private String companyOrWorkshopName;
    private String userName;
    private String FirstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String password;
    private String accountType;
    
    public String getCompanyOrWorkshopName() {
        return companyOrWorkshopName;
    }

    public String getUserName() {
        return userName;
    }

    public String getFirstName() {
        return FirstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public String getAccountType() {
        return accountType;
    }

    @Override
    public String toString() {
        return "AddSellerAccountRequest{" +
                "companyOrWorkshopName='" + companyOrWorkshopName + '\'' +
                ", userName='" + userName + '\'' +
                ", FirstName='" + FirstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", password='" + password + '\'' +
                ", accountType='" + accountType + '\'' +
                '}';
    }

    public AddSellerAccountRequest(String companyOrWorkshopName, String userName, String firstName, String lastName, String email, String phoneNumber, String password, String accountType) {
        this.companyOrWorkshopName = companyOrWorkshopName;
        this.userName = userName;
        FirstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.accountType = accountType;
    }
}
