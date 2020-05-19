package Controller;

import Model.Product;
import Model.Shop;

public class AllProductsPageController {
    private static AllProductsPageController allProductsPageController = new AllProductsPageController();
    private AllProductsPageController() {}
    public static AllProductsPageController getInstance() {
        return allProductsPageController;
    }
    private Shop shop = Shop.getInstance();

    public void showOFFProducts(){
        for (Product product : shop.getAllProductOnOffsAndCount().keySet()) {
            System.out.println(product);
        }
    }
}
