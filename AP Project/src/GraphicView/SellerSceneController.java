package GraphicView;

import Controller.AccountPagesController.AccountPageController;
import Model.Accounts.CustomerAccount;
import Model.Accounts.ManagerAccount;
import Model.Accounts.SellerAccount;
import javafx.event.ActionEvent;
import javafx.scene.Parent;

public class SellerSceneController {
    private static Parent root;

    public static Parent getRoot() {
        return root;
    }

    public static void setRoot(Parent root) {
        SellerSceneController.root = root;
    }

    public void viewSalesHistory(ActionEvent event) {
    }

    public void manageProducts(ActionEvent event) {
    }

    public void addProduct(ActionEvent event) {
    }

    public void removeProduct(ActionEvent event) {
    }

    public void showCategories(ActionEvent event) {
    }

    public void viewOffs(ActionEvent event) {
    }

    public void logOut(ActionEvent event) {
    }

    public void editInformation(ActionEvent event) {
    }

    public void mainMenu(ActionEvent event) {
        Main.pagesHistory.add("main");
        Main.scene.setRoot(MainMenuSceneController.getRoot());
    }

    public void loginMenu(ActionEvent event) {
        Main.pagesHistory.add("login");
        Main.scene.setRoot(LoginPageSceneController.getRoot());
    }

    public void userPanel(ActionEvent event) {
        if (AccountPageController.getUser().getClass() == ManagerAccount.class) {
            Main.pagesHistory.add("manager");
            Main.scene.setRoot(ManagerSceneController.getRoot());
        } else if (AccountPageController.getUser().getClass() == SellerAccount.class) {
            Main.pagesHistory.add("seller");
            Main.scene.setRoot(getRoot());
        } else if (AccountPageController.getUser().getClass() == CustomerAccount.class) {
            Main.pagesHistory.add("customer");
            Main.scene.setRoot(CustomerSceneController.getRoot());
        }
    }

    public void ProductsPage(ActionEvent event) {
        Main.pagesHistory.add("products");
        Main.scene.setRoot(AllProductsSceneController.getRoot());
    }

    public void OffsPage(ActionEvent event) {
        Main.pagesHistory.add("offs");
        Main.scene.setRoot(OffsSceneController.getRoot());
    }


    public void back(ActionEvent event) {
        Main.pagesHistory.remove(Main.pagesHistory.size() - 1);
        switch (Main.pagesHistory.get(Main.pagesHistory.size() - 1)) {
            case "main":
                Main.scene.setRoot(MainMenuSceneController.getRoot());
                break;
            case "manager":
                Main.scene.setRoot(ManagerSceneController.getRoot());
                break;
            case "seller":
                Main.scene.setRoot(getRoot());
                break;
            case "customer":
                Main.scene.setRoot(CustomerSceneController.getRoot());
                break;
            case "products":
                Main.scene.setRoot(AllProductsSceneController.getRoot());
                break;
            case "offs":
                Main.scene.setRoot(OffsSceneController.getRoot());
                break;
            case "cart":
                Main.scene.setRoot(CartSceneController.getRoot());
                break;
        }
    }

    public void exit(ActionEvent event) {
        Main.stage.close();
    }
}
