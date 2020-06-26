package GraphicView;

import Controller.AccountPagesController.AccountPageController;
import Model.Accounts.CustomerAccount;
import Model.Accounts.ManagerAccount;
import Model.Accounts.SellerAccount;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Popup;

import java.io.IOException;

public class MainMenuSceneController {
    private static Parent root;
    private Popup mainManagerPopup;
    private boolean mainManagerIn = false;


    public static Parent getRoot() {
        return root;
    }

    public static void setRoot(Parent root) {
        MainMenuSceneController.root = root;
    }

    public void productsPageAction(ActionEvent event) {
        Main.pagesHistory.add("products");
        Main.scene.setRoot(AllProductsSceneController.getRoot());
    }

    public void loginPage(ActionEvent event) {
        Main.pagesHistory.add("login");
        Main.scene.setRoot(LoginPageSceneController.getRoot());
    }

    public void offPage(ActionEvent event) {
        Main.pagesHistory.add("offs");
        Main.scene.setRoot(OffsSceneController.getRoot());
    }

    public void userPage(ActionEvent event) {
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

    public void showMainManagerPopup() {
        if (!mainManagerIn) {
            try {
                mainManagerPopup = new Popup();
                mainManagerPopup.getContent().add(FXMLLoader.load(getClass().getResource("createManager.fxml")));
                mainManagerPopup.show(Main.stage);
            } catch (IOException ignored) {
            }
        }
    }

    public void setMainManagerIn(boolean mainManagerIn) {
        this.mainManagerIn = mainManagerIn;
    }

    public void closeMainManagerPopup() {
        if (mainManagerIn)
            mainManagerPopup.hide();
    }


    public void back(ActionEvent event) {
        if (Main.pagesHistory.size() != 1) {
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
                    Main.scene.setRoot(CartSceneController.getRoot());
                    break;
            }
        }
    }

    public void exit(ActionEvent event) {
        Main.stage.close();
    }
}
