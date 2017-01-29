package app; /**
 * Created by gun on 05/01/2017.
 * URSS-Desktop
 */

import Controller.LoginController;
import Controller.MainController;
import Model.response.User;
import Module.DatabaseHandler;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        DatabaseHandler.getInstance().initDb();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("../Layout/main.fxml"));
        Parent root = loader.load();
        primaryStage.setTitle("Urss");
        MainController controller = loader.getController();
        controller.setHostServices(getHostServices());
        primaryStage.setScene(new Scene(root, 1280, 720));
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
