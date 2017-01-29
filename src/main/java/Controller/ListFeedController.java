package Controller;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by gun on 28/01/2017.
 * URSS-Desktop
 */
public class ListFeedController implements Initializable {

    @FXML
    private  ImageView list_feed_imageview_picture;
    @FXML
    private  Text list_feed_text_title;
    @FXML
    private  Text list_feed_text_description;
    @FXML
    private  JFXButton list_feed_button_go;
    @FXML
    private TextFlow list_feed_textflow_description;


    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("listfeed contoler");
    }
}
