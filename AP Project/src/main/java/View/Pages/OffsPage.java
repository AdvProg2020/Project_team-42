package View.Pages;

import Controller.AllProductsPageController;
import Model.Shop;
import View.Commands;
import View.Page;
public class OffsPage extends Page {
    private static OffsPage offsPage = new OffsPage();
   private Shop shop = Shop.getInstance();
   private AllProductsPageController controller = AllProductsPageController.getInstance();
    private boolean isEnd;

    private OffsPage () {}

    public static OffsPage getInstance() {
        return offsPage;
    }

    public Page run() {
        showOffsProducts();
        String input;
        while(!Commands.EXIT.getMatcher(input = scanner.nextLine().trim()).matches())
        {

        }
        return null;
    }
    public void showOffsProducts(){
        controller.showOFFProducts();
    }
}
