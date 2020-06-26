package GraphicView;

import Controller.AccountPagesController.AccountPageController;
import Controller.AccountPagesController.CustomerPageController;
import Controller.AccountPagesController.ManagerPageController;
import Controller.AccountPagesController.SellerPageController;
import Controller.LoginAndRegisterPageController;
import Model.Accounts.CustomerAccount;
import Model.Accounts.ManagerAccount;
import Model.Accounts.SellerAccount;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Popup;

import java.io.IOException;


public class LoginPageSceneController {
    private static Parent root;
    private LoginAndRegisterPageController controller = LoginAndRegisterPageController.getInstance();
    @FXML
    protected TextField loginUserName;
    @FXML
    protected TextField registerUserName;
    @FXML
    protected TextField firstName;
    @FXML
    protected TextField lastName;
    @FXML
    protected TextField email;
    @FXML
    protected TextField phoneNumber;
    @FXML
    protected TextField accountType;
    @FXML
    protected TextField companyName;
    @FXML
    protected PasswordField loginPassword;
    @FXML
    protected PasswordField registerPassword;
    @FXML
    protected Label warningText;



    public static Parent getRoot() {
        return root;
    }

    public static void setRoot(Parent root) {
        LoginPageSceneController.root = root;
    }

    public void registerAction(ActionEvent event) {
        if (accountType.getText().equals(""))
            warningText.setText("Account type is empty.");
        else if (registerUserName.getText().equals(""))
            warningText.setText("Username is empty.");
        else if (registerPassword.getText().equals(""))
            warningText.setText("Password is empty.");
        else if (firstName.getText().equals(""))
            warningText.setText("First name is empty.");
        else if (lastName.getText().equals(""))
            warningText.setText("Last name is empty.");
        else if (phoneNumber.getText().equals(""))
            warningText.setText("Phone Number is empty.");
        else if (email.getText().equals(""))
            warningText.setText("Email is empty.");
        else if (companyName.getText().equals("") && accountType.getText().equals("seller"))
            warningText.setText("Company name is empty.");
        else if (!(accountType.getText().equalsIgnoreCase("customer") || accountType.getText().equalsIgnoreCase("seller")))
            warningText.setText("Account type is invalid.");
        else if (!Commands.NAMES.getMatcher(registerUserName.getText()).matches())
            warningText.setText("Username is invalid.");
        else if (!Commands.PASSWORD.getMatcher(registerPassword.getText()).matches())
            warningText.setText("Password is invalid.");
        else if (!Commands.NAMES.getMatcher(firstName.getText()).matches())
            warningText.setText("First name is invalid.");
        else if (!Commands.NAMES.getMatcher(lastName.getText()).matches())
            warningText.setText("Last name is invalid.");
        else if (!Commands.PHONE_NUMBER.getMatcher(phoneNumber.getText()).matches())
            warningText.setText("Phone Number is invalid.");
        else if (!Commands.EMAIL.getMatcher(email.getText()).matches())
            warningText.setText("Email is invalid.");
        else {
            switch (accountType.getText()) {
                case "seller":
                    try {
                        controller.createRegisterRequestSellerAccountController(registerUserName.getText(), firstName.getText(), lastName.getText(),
                                email.getText(), phoneNumber.getText(), registerPassword.getText(), "seller", companyName.getText());
                    } catch (Exception e) {
                        warningText.setText(e.getMessage());
                    }
                    break;
                case "customer":
                    try {
                        controller.createCustomerAccountController(registerUserName.getText(), firstName.getText(), lastName.getText(), email.getText(),
                                phoneNumber.getText(), registerPassword.getText(), "customer");
                        warningText.setText("");
                    } catch (Exception e) {
                        warningText.setText(e.getMessage());
                    }
            }
        }
    }

    public void loginAction(ActionEvent event) {
        if (loginUserName.getText().equals(""))
            warningText.setText("Username is empty.");
        else if (loginPassword.getText().equals(""))
            warningText.setText("password is empty.");
        else if (!Commands.NAMES.getMatcher(loginUserName.getText()).matches())
            warningText.setText("Username is invalid.");
        else if (!Commands.PASSWORD.getMatcher(loginPassword.getText()).matches())
            warningText.setText("Password is invalid.");
        else {
            boolean customer = false, seller = false, manager = false;
            ManagerAccount managerAccount = null;
            CustomerAccount customerAccount = null;
            SellerAccount sellerAccount = null;
            try {
                customerAccount = controller.loginCustomerAccountController(loginUserName.getText(), loginPassword.getText());
            } catch (Exception e) {
                customer = true;
            }
            try {
                sellerAccount = controller.loginSellerAccountController(loginUserName.getText(), loginPassword.getText());
            } catch (Exception e) {
                seller = true;
            }
            try {
                managerAccount = controller.loginManagerAccountController(loginUserName.getText(), loginPassword.getText());
            } catch (Exception e) {
                manager = true;
            }
            if (!manager) {
                ManagerPageController.getInstance().setUser(managerAccount);
                Main.scene.setRoot(ManagerSceneController.getRoot());
                Main.managerSceneController.setFields(managerAccount);
                warningText.setText("");
            } else if (!seller) {
                SellerPageController.getInstance().setUser(sellerAccount);
                Main.scene.setRoot(SellerSceneController.getRoot());
                warningText.setText("");
            } else if (!customer) {
                CustomerPageController.getInstance().setUser(customerAccount);
                Main.scene.setRoot(CustomerSceneController.getRoot());
                warningText.setText("");
            } else
                warningText.setText("Username or password is invalid.");
        }
    }

    public void mainMenu(ActionEvent event) {
        Main.pagesHistory.add("main");
        Main.scene.setRoot(MainMenuSceneController.getRoot());
    }

    public void loginMenu(ActionEvent event) {
        Main.pagesHistory.add("login");
        Main.scene.setRoot(getRoot());
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
                Main.scene.setRoot(CartSceneController.getRoot());
                break;
        }
    }

    public void exit(ActionEvent event) {
        Main.stage.close();
    }
}
