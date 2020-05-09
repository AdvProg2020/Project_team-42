package View.Pages;

import View.AllPages;
import View.Page;

public class ProductPage extends Page {
    private static ProductPage productPage = new ProductPage();

    private ProductPage () {}

    public static ProductPage getInstance() {
        return productPage;
    }

    public AllPages run() {
        return null;
    }
}
