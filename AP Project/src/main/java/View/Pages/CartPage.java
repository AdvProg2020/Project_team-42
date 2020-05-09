package View.Pages;

import View.AllPages;
import View.Page;

public class CartPage extends Page {
    private static CartPage cartPage = new CartPage();

    private CartPage () {}

    public static CartPage getInstance() {
        return cartPage;
    }

    public AllPages run() {
        return null;
    }
}
