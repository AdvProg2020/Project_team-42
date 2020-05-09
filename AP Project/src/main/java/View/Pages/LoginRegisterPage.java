package View.Pages;

import View.AllPages;
import View.Page;

public class LoginRegisterPage extends Page {
    private static LoginRegisterPage loginRegisterPage = new LoginRegisterPage();

    private LoginRegisterPage () {}

    public static LoginRegisterPage getInstance() {
        return loginRegisterPage;
    }

    public AllPages run() {
        return null;
    }
}
