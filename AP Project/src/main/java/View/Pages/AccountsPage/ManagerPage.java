package View.Pages.AccountsPage;

import View.Page;

public class ManagerPage extends Page {
    private static ManagerPage managerPage = new ManagerPage();

    private ManagerPage(){

    }

    public static ManagerPage getInstance() {
        return managerPage;
    }

    public Page run() {
        return null;
    }
}
