package Controller;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by gun on 06/01/2017.
 */
public class MainController implements Initializable {

    @FXML
    private Button main_button_feed;
    @FXML
    private Button main_button_login;
    @FXML
    private Button main_button_sign_in;
    @FXML
    private Pane main_pane;

    @FXML
    private static Pane mMainPane;

    public static  Pane getmMainPane() {
        return mMainPane;
    }



    public void initialize(URL location, ResourceBundle resources) {

        mMainPane = main_pane;

        main_button_feed.setOnMouseClicked(new EventHandler<javafx.scene.input.MouseEvent>() {
            public void handle(javafx.scene.input.MouseEvent event) {
                System.out.println("feed");
                try {
                    main_pane.getChildren().setAll((Pane) FXMLLoader.load(getClass().getResource("../Layout/feed.fxml")));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        main_button_login.setOnMouseClicked(new EventHandler<javafx.scene.input.MouseEvent>() {
            public void handle(javafx.scene.input.MouseEvent event) {
                System.out.println("login");
                try {
                    main_pane.getChildren().setAll((Pane) FXMLLoader.load(getClass().getResource("../Layout/login.fxml")));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        main_button_sign_in.setOnMouseClicked(new EventHandler<javafx.scene.input.MouseEvent>() {
            public void handle(javafx.scene.input.MouseEvent event) {
                System.out.println("sign in");
                try {
                    main_pane.getChildren().setAll((Pane) FXMLLoader.load(getClass().getResource("../Layout/sign_in.fxml")));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }


}