package Controller.AccountPagesController;

import Controller.Exceptions;
import Model.Accounts.Account;
import Model.Shop;

public abstract class AccountPageController {
    protected Shop shop;
    protected static Account user;

    public static Account getUser() throws Exceptions.NotLogedInException {
        if (user == null)
            throw new Exceptions.NotLogedInException();
        return user;
    }

    public String getPersonalInfo() {
        return user.toString();
    }

    public void editPersonalInfo (String field, String newValue) {
        if (field.equalsIgnoreCase("first name"))
            user.setFirstName(newValue);
        else if (field.equalsIgnoreCase("last name"))
            user.setLastName(newValue);
        else if (field.equalsIgnoreCase("email"))
            user.setEmail(newValue);
        else if (field.equalsIgnoreCase("phone number"))
            user.setPhoneNumber(newValue);
        else if (field.equalsIgnoreCase("password"))
            user.setPassword(newValue);
    }
}
