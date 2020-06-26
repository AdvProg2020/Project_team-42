package GraphicView;

import Controller.LoginAndRegisterPageController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class CreateManagerController {
    @FXML protected TextField userNameText;
    @FXML protected PasswordField passwordText;
    @FXML protected TextField firstNameText;
    @FXML protected TextField lastNameText;
    @FXML protected TextField emailText;
    @FXML protected TextField phoneNumberText;
    @FXML protected Label errorLabel;

    public void createManageAccount(ActionEvent actionEvent) {
        if (userNameText.getText().equals(""))
            errorLabel.setText("Username is empty.");
        else if (passwordText.getText().equals(""))
            errorLabel.setText("Password is empty.");
        else if (firstNameText.getText().equals(""))
            errorLabel.setText("First name is empty.");
        else if (lastNameText.getText().equals(""))
            errorLabel.setText("Last name is empty.");
        else if (emailText.getText().equals(""))
            errorLabel.setText("email is empty.");
        else if (phoneNumberText.getText().equals(""))
            errorLabel.setText("phone number is empty.");
        else if (!Commands.NAMES.getMatcher(userNameText.getText()).matches())
            errorLabel.setText("Username is invalid.");
        else if (!Commands.PASSWORD.getMatcher(passwordText.getText()).matches())
            errorLabel.setText("Password is invalid.");
        else if (!Commands.NAMES.getMatcher(firstNameText.getText()).matches())
            errorLabel.setText("First name is invalid.");
        else if (!Commands.NAMES.getMatcher(lastNameText.getText()).matches())
            errorLabel.setText("Last name is invalid.");
        else if (!Commands.EMAIL.getMatcher(emailText.getText()).matches())
            errorLabel.setText("email is invalid.");
        else if (!Commands.PHONE_NUMBER.getMatcher(phoneNumberText.getText()).matches())
            errorLabel.setText("phone number is invalid.");
        else {
            try {
                LoginAndRegisterPageController.getInstance().CreateFirstManagerAccountController(userNameText.getText(), firstNameText.getText(),
                        lastNameText.getText(), emailText.getText(), phoneNumberText.getText(), passwordText.getText(), "manager");
                errorLabel.setText("");
                Main.mainMenuSceneController.setMainManagerIn(true);
                Main.mainMenuSceneController.closeMainManagerPopup();
            } catch (Exception e) {
                errorLabel.setText(e.getMessage());
            }
        }
    }

    public void back(ActionEvent actionEvent) {
        Main.mainMenuSceneController.closeMainManagerPopup();
    }
}
