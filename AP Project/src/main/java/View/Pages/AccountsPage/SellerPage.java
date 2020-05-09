package View.Pages.AccountsPage;

import View.AllPages;
import View.Page;

public class SellerPage extends Page {
    private static SellerPage sellerPage = new SellerPage();

    private SellerPage () {}

    public static SellerPage getInstance() {
        return sellerPage;
    }

    public AllPages run() {
        return null;
    }
}
