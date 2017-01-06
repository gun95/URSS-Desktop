import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import javafx.scene.layout.AnchorPane;

import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by gun on 06/01/2017.
 */
public class Controller implements Initializable {

    @FXML
    private Button button;

    public void initialize(URL location, ResourceBundle resources) {
        button.setOnMouseClicked(new EventHandler<javafx.scene.input.MouseEvent>() {
            public void handle(javafx.scene.input.MouseEvent event) {
                System.out.println("lol");
            }
        });
    }
}
