package View.Pages;

import View.AllPages;
import View.Page;

public class OffsPage extends Page {
    private static OffsPage offsPage = new OffsPage();

    private OffsPage () {}

    public static OffsPage getInstance() {
        return offsPage;
    }

    public AllPages run() {
        return null;
    }
}
