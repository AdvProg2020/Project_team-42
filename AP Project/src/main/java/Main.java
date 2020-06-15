import Controller.LoginAndRegisterPageController;
import View.Page;
import View.Pages.LoginRegisterPage;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) {
        Page page = LoginRegisterPage.getInstance();

        try {
            LoginAndRegisterPageController.getInstance().parseResources();
        } catch (FileNotFoundException ignored) {}

        while (page != null) {
            page = page.run();
        }
    }
}
