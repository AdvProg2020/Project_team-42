package View.Pages;

import Model.Product;
import Model.Shop;
import View.AllPages;
import View.Commands;
import View.Page;
import Controller.AllProductsPageController;
public class OffsPage extends Page {
    private static OffsPage offsPage = new OffsPage();
   private Shop shop = Shop.getInstance();
   private AllProductsPageController controller = AllProductsPageController.getInstance();
    private boolean isEnd;

    private OffsPage () {}

    public static OffsPage getInstance() {
        return offsPage;
    }

    public AllPages run() {
        showOffsProducts();
        String input;
        while(!Commands.EXIT.getMatcher(input = scanner.nextLine().trim()).matches())
        {
            if()
        }
    }
    public void showOffsProducts(){
        controller.showOFFProducts();
    }
}
