package View.Pages;

import Model.Shop;
import View.Commands;
import View.Page;

public class AllProductsPage extends Page {
    private static AllProductsPage allProductsPage = new AllProductsPage();
    private Shop shop = Shop.getInstance();

    private AllProductsPage() {
    }

    public static AllProductsPage getInstance() {
        return allProductsPage;
    }

    public Page run() {

        String input;
        while (!Commands.EXIT.getMatcher(input = scanner.nextLine().trim()).matches()) {
             if (Commands.HELP.getMatcher(input).matches()) {
                help();
            }else if (Commands.V.getMatcher(input).matches()) {
                help();
            }else if (Commands.HELP.getMatcher(input).matches()) {
                help();
            }else if (Commands.HELP.getMatcher(input).matches()) {
                help();
            }else if (Commands.HELP.getMatcher(input).matches()) {
                help();
            }else if (Commands.HELP.getMatcher(input).matches()) {
                help();
            }else if (Commands.HELP.getMatcher(input).matches()) {
                help();
            }else if (Commands.HELP.getMatcher(input).matches()) {
                help();
            }else if (Commands.HELP.getMatcher(input).matches()) {
                help();
            }else if (Commands.HELP.getMatcher(input).matches()) {
                help();
            }
        }
    }

    public void help():
}
