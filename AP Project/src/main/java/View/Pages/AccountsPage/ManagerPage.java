package View.Pages.AccountsPage;

import View.AllPages;
import View.Page;

public class ManagerPage extends Page {
    private static ManagerPage managerPage = new ManagerPage();

    private ManagerPage(){}

    public static ManagerPage getInstance() {
        return managerPage;
    }

    public AllPages run() {
        return null;
    }
}
