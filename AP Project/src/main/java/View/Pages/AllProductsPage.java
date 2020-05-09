package View.Pages;

import View.AllPages;
import View.Page;

public class AllProductsPage extends Page {
    private static AllProductsPage allProductsPage = new AllProductsPage();

    private AllProductsPage () {}

    public static AllProductsPage getInstance() {
        return allProductsPage;
    }

    public AllPages run() {
        return null;
    }
}
