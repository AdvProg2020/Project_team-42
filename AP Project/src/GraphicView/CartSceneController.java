package GraphicView;

import Controller.AccountPagesController.AccountPageController;
import Model.Accounts.CustomerAccount;
import Model.Accounts.ManagerAccount;
import Model.Accounts.SellerAccount;
import javafx.event.ActionEvent;
import javafx.scene.Parent;

public class CartSceneController {
    private static Parent root;

    public static Parent getRoot() {
        return root;
    }

    public static void setRoot(Parent root) {
        CartSceneController.root = root;
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
            Main.scene.setRoot(SellerSceneController.getRoot());
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
                Main.scene.setRoot(SellerSceneController.getRoot());
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
                Main.scene.setRoot(getRoot());
                break;
        }
    }

    public void exit(ActionEvent event) {
        Main.stage.close();
    }
}
