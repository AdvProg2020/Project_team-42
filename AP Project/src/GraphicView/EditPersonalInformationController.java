package GraphicView;

import Controller.AccountPagesController.ManagerPageController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


public class EditPersonalInformationController {
    @FXML protected TextField firstNameText;
    @FXML protected TextField lastNameText;
    @FXML protected TextField password;
    @FXML protected TextField email;
    @FXML protected TextField phoneNumber;
    @FXML protected Label warning;

    public void saveButton(ActionEvent actionEvent) {
        boolean firstName = false, lastName = false, password = false, email = false, phone = false;
        if (!firstNameText.getText().equals("")) {
            if (!Commands.NAMES.getMatcher(firstNameText.getText()).matches())
                firstName = true;
            else {
                warning.setText("username");
                return;
            }
        }  if (!lastNameText.getText().equals("")) {
            if (!Commands.NAMES.getMatcher(lastNameText.getText()).matches())
                lastName = true;
            else {
                warning.setText("last name");
                return;
            }
        }  if (!this.password.getText().equals("")) {
            if (!Commands.NAMES.getMatcher(this.password.getText()).matches())
                password = true;
            else {
                warning.setText("password");
                return;
            }
        }  if (!this.email.getText().equals("")) {
            if (!Commands.NAMES.getMatcher(this.email.getText()).matches())
                email = true;
            else {
                warning.setText("email");
                return;
            }
        }  if (!this.phoneNumber.getText().equals("")) {
            if (!Commands.NAMES.getMatcher(this.phoneNumber.getText()).matches())
                phone = true;
            else {
                warning.setText("phone number");
                return;
            }
        }
        if (firstName)
            ManagerPageController.getInstance().editPersonalInfo("first name", firstNameText.getText());
        if (lastName)
            ManagerPageController.getInstance().editPersonalInfo("last name", lastNameText.getText());
        if (password)
            ManagerPageController.getInstance().editPersonalInfo("password", this.password.getText());
        if (email)
            ManagerPageController.getInstance().editPersonalInfo("email", this.email.getText());
        if (phone)
            ManagerPageController.getInstance().editPersonalInfo("phone number", phoneNumber.getText());
    }

    public void backButton(ActionEvent event) {
        Main.managerSceneController.closePopup();
    }
}
