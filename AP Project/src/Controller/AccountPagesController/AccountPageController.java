package Controller.AccountPagesController;

import Model.Accounts.Account;
import Model.Shop;

import java.io.IOException;

public abstract class AccountPageController {
    protected Shop shop = Shop.getInstance();
    protected static Account user;

    public static Account getUser() {
        return user;
    }

    public static void setUser(Account user) {
        AccountPageController.user = user;
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

        /*try {
            user.updateResources();
        } catch (IOException ignored) {}*/
    }
}
