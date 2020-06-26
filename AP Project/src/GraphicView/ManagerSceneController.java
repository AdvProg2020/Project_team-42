package GraphicView;

import Controller.AccountPagesController.AccountPageController;
import Controller.AccountPagesController.CustomerPageController;
import Controller.AccountPagesController.ManagerPageController;
import Controller.AccountPagesController.SellerPageController;
import Model.Accounts.CustomerAccount;
import Model.Accounts.ManagerAccount;
import Model.Accounts.SellerAccount;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.stage.Popup;

import java.io.IOException;


public class ManagerSceneController {
    private static Parent root;
    private Popup popup;

    @FXML
    protected Label username;
    @FXML
    protected Label name;
    @FXML
    protected Label email;
    @FXML
    protected Label phone;

    public void setFields(ManagerAccount account) {
        username.setText(account.getUserName());
        name.setText(account.getFirstName() + " " + account.getLastName());
        email.setText(account.getEmail());
        phone.setText(account.getPhoneNumber());
    }

    public void closePopup () {
        popup.hide();
    }

    public static Parent getRoot() {
        return root;
    }

    public static void setRoot(Parent root) {
        ManagerSceneController.root = root;
    }

    public void viewDiscountCodes(ActionEvent event) {
        try {
            popup = new Popup();
            popup.getContent().add(FXMLLoader.load(getClass().getResource("viewDiscountCode.fxml")));
            popup.show(Main.stage);
        } catch (IOException ignored) {
        }
    }

    public void manageCategories(ActionEvent event) {
        try {
            popup = new Popup();
            popup.getContent().add(FXMLLoader.load(getClass().getResource("ManageCategories.fxml")));
            popup.show(Main.stage);
        } catch (IOException ignored) {
        }
    }

    public void manageAllProducts(ActionEvent event) {
        try {
            popup = new Popup();
            popup.getContent().add(FXMLLoader.load(getClass().getResource("ManageAllProducts.fxml")));
            popup.show(Main.stage);
        } catch (IOException ignored) {
        }
    }

    public void manageUsers(ActionEvent event) {
        try {
            popup = new Popup();
            popup.getContent().add(FXMLLoader.load(getClass().getResource("ManageUsers.fxml")));
            popup.show(Main.stage);
        } catch (IOException ignored) {
        }
    }

    public void createDiscoutCode(ActionEvent event) {
        try {
            popup = new Popup();
            popup.getContent().add(FXMLLoader.load(getClass().getResource("createDiscountCode.fxml")));
            popup.show(Main.stage);
        } catch (IOException ignored) {
        }
    }

    public void manageRequests(ActionEvent event) {
    }

    public void logOut(ActionEvent event) {
        AccountPageController.setUser(null);
        SellerPageController.getInstance().setUser(null);
        ManagerPageController.getInstance().setUser(null);
        CustomerPageController.getInstance().setUser(null);
        Main.pagesHistory.clear();
        Main.scene.setRoot(MainMenuSceneController.getRoot());
    }

    public void editInformation(ActionEvent event) {
        try {
            popup = new Popup();
            popup.getContent().add(FXMLLoader.load(getClass().getResource("EditPersonalInformation.fxml")));
            popup.show(Main.stage);
        } catch (IOException ignored) {
        }
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
            Main.scene.setRoot(getRoot());
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
                Main.scene.setRoot(getRoot());
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

    public void exit(ActionEvent event) {
        Main.stage.close();
    }
}
