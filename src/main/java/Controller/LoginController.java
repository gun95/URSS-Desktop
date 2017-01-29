package Controller;

import Api.ApiManager;
import Model.request.loginRequest;
import Model.response.Credential;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by gun on 06/01/2017.
 * URSS-Desktop
 */
public class LoginController implements Initializable {

    @FXML
    private JFXButton login_button;
    @FXML
    private JFXPasswordField login_passwordfield_password;
    @FXML
    private JFXTextField login_textfield_email;
    @FXML
    private Pane main_pane;
    @FXML
    private Text login_text_error;


    public void initialize(URL location, ResourceBundle resources) {

        main_pane = MainController.getmMainPane();

        login_button.setOnMouseClicked(new EventHandler<javafx.scene.input.MouseEvent>() {
            public void handle(javafx.scene.input.MouseEvent event) {
                String email = login_textfield_email.getText();
                String password = login_passwordfield_password.getText();
                System.out.println("connection");
                System.out.println("email = " + email);
                System.out.println("password = " + password);
                login(email, password);
            }
        });

    }


    private void login(String email, String password) {
        ApiManager.get().login(new loginRequest(email, password)).enqueue(new Callback<Credential>() {
            public void onResponse(Call<Credential> call, Response<Credential> response) {
                System.out.println("response = " + response.raw().toString());
                if (response.isSuccessful()) {
                    System.out.println("login = " + response.body().toString());
                    updateUi();
                } else {
                    try {
                        JsonObject jObjError = new JsonObject();
                        final Gson gson = new Gson();
                        jObjError = gson.fromJson(response.errorBody().string(), JsonObject.class);
                        System.out.println("not sucess login = " + response.code() + " message = " + jObjError.get("message"));
                        updateUiError(jObjError.get("message").toString());
                    } catch (Exception e) {
                        System.out.println("error string errorBody");
                        updateUiError("error string errorBody");
                    }
                }
            }
            public void onFailure(Call<Credential> call, Throwable t) {
                System.out.println("On failure login : " + t.getMessage());
                updateUiError("On failure login : " + t.getMessage());
            }
        });
    }

    private void updateUiError(final String error)
    {
        Platform.runLater(new Runnable() {
            public void run() {
                login_text_error.setText(error);
            }
        });
    }

    void updateUi()
    {
        Platform.runLater(new Runnable() {
            public void run() {
                try {
                    main_pane = MainController.getmMainPane();
                    main_pane.getChildren().setAll((Pane) FXMLLoader.load(getClass().getResource("../Layout/feed.fxml")));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
