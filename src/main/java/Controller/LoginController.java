package Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by gun on 06/01/2017.
 */
public class LoginController implements Initializable {

    @FXML
    JFXButton login_button;
    @FXML
    JFXPasswordField login_passwordfield_password;
    @FXML
    JFXTextField login_textfield_email;
    public void initialize(URL location, ResourceBundle resources) {


        login_button.setOnMouseClicked(new EventHandler<javafx.scene.input.MouseEvent>() {
            public void handle(javafx.scene.input.MouseEvent event) {
                String email = login_textfield_email.getText();
                String password = login_passwordfield_password.getText();
                System.out.println("connection");
                System.out.println("email = " + email);
                System.out.println("password = " + password);
            }
        });

    }
}
