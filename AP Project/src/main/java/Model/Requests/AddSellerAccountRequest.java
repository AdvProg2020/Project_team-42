package Model.Requests;

import java.util.ArrayList;
import java.util.Date;

public class AddSellerAccountRequest extends Request {
    public AddSellerAccountRequest(ArrayList<Request> unAnsweredRequests, ArrayList<Request> answeredRequests, int requestId, Date requestDate, Date answerDate, Manager responsiveManager, RequestState requestState) {
        super(unAnsweredRequests, answeredRequests, requestId, requestDate, answerDate, responsiveManager, requestState);
    }

    String companyOrWorrkshopName;
    String userName;
    String FirstName;
    String lastName;
    String email;
    String phoneNumber;
    String password;
    String accountType;

    public AddSellerRequest(ArrayList<Request> unAnsweredRequests, ArrayList<Request> answeredRequests, int requestId, Date requestDate, Date answerDate, Manager responsiveManager, RequestState requestState, String companyOrWorrkshopName, String userName, String firstName, String lastName, String email, String phoneNumber, String password, String accountType) {
        super(unAnsweredRequests, answeredRequests, requestId, requestDate, answerDate, responsiveManager, requestState);
        this.companyOrWorrkshopName = companyOrWorrkshopName;
        this.userName = userName;
        FirstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.accountType = accountType;
    }

    public String getCompanyOrWorrkshopName() {
        return companyOrWorrkshopName;
    }

    public void setCompanyOrWorrkshopName(String companyOrWorrkshopName) {
        this.companyOrWorrkshopName = companyOrWorrkshopName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }
}
