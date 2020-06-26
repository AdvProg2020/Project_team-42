package GraphicView;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Main extends Application {
    public static MainMenuSceneController mainMenuSceneController;
    public static LoginPageSceneController loginPageSceneController;
    public static ManagerSceneController managerSceneController;
    public static SellerSceneController sellerSceneController;
    public static CustomerSceneController customerSceneController;
    public static AllProductsSceneController allProductsSceneController;
    public static OffsSceneController offsSceneController;
    public static ProductSceneController productSceneController;
    public static CartSceneController cartSceneController;
    public static Scene scene;
    public static Stage stage;
    public static ArrayList<String> pagesHistory = new ArrayList<>();

    @Override
    public void start(Stage primaryStage) throws Exception{
        stage = primaryStage;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("MainMenu.fxml"));
        MainMenuSceneController.setRoot(loader.load());
        mainMenuSceneController = loader.getController();

        loader = new FXMLLoader(getClass().getResource("Manager.fxml"));
        ManagerSceneController.setRoot(loader.load());
        managerSceneController = loader.getController();

        loader = new FXMLLoader(getClass().getResource("LoginRegister.fxml"));
        LoginPageSceneController.setRoot(loader.load());
        loginPageSceneController = loader.getController();

        loader = new FXMLLoader(getClass().getResource("Seller.fxml"));
        SellerSceneController.setRoot(loader.load());
        sellerSceneController = loader.getController();

        loader = new FXMLLoader(getClass().getResource("Customer.fxml"));
        CustomerSceneController.setRoot(loader.load());
        customerSceneController = loader.getController();

        loader = new FXMLLoader(getClass().getResource("Product.fxml"));
        ProductSceneController.setRoot(loader.load());
        productSceneController = loader.getController();

        loader = new FXMLLoader(getClass().getResource("AllProducts.fxml"));
        AllProductsSceneController.setRoot(loader.load());
        allProductsSceneController = loader.getController();

        loader = new FXMLLoader(getClass().getResource("Cart.fxml"));
        CartSceneController.setRoot(loader.load());
        cartSceneController = loader.getController();

        loader = new FXMLLoader(getClass().getResource("Offs.fxml"));
        OffsSceneController.setRoot(loader.load());
        offsSceneController = loader.getController();

        pagesHistory.add("main");

        primaryStage.setTitle("Shop");
        primaryStage.setScene(scene = new Scene(MainMenuSceneController.getRoot(), 1000, 700));
        primaryStage.show();

        mainMenuSceneController.showMainManagerPopup();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
