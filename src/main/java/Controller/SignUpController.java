package Controller;

import Api.ApiManager;
import Model.request.RequestLogin;
import Model.response.User;
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
 */
public class SignUpController implements Initializable {

    @FXML
    JFXButton sign_up_button;
    @FXML
    JFXTextField sign_up_textfield_email;
    @FXML
    JFXPasswordField sign_up_jfxpasswordfield_password;
    @FXML
    JFXPasswordField sign_up_jfxpasswordfield_confirm_password;
    @FXML
    Text sign_up_text_error;

    public void initialize(URL location, ResourceBundle resources) {

        sign_up_button.setOnMouseClicked(new EventHandler<javafx.scene.input.MouseEvent>() {
            public void handle(javafx.scene.input.MouseEvent event) {
                String email = sign_up_textfield_email.getText();
                String password = sign_up_jfxpasswordfield_password.getText();
                String passwordConfirm = sign_up_jfxpasswordfield_confirm_password.getText();
                System.out.println("sign_up");
                System.out.println("email = " + email);
                System.out.println("password = " + password);

                if (email.isEmpty()) {
                    sign_up_text_error.setText("email empty");
                } else if (password.isEmpty()) {
                    sign_up_text_error.setText("password empty");
                } else if (!password.equals(passwordConfirm))
                    sign_up_text_error.setText("password doesn't match");
                else
                    signIn(email, password);

            }
        });
    }

    private void signIn(String email, String password) {
        ApiManager.get().createCredential(new RequestLogin(email, password)).enqueue(new Callback<User>() {
            public void onResponse(Call<User> call, Response<User> response) {
                System.out.println("response = " + response.raw().toString());
                if (response.isSuccessful()) {
                    // normal que il y a que null pour le moment le serveur renvoi "id" au lieu de "userId" attendre que shadow change ca
                    System.out.println("create credential = " + response.body().toString());
                    updateUi();
                } else {
                    try {
                        JsonObject jObjError = new JsonObject();
                        final Gson gson = new Gson();
                        jObjError = gson.fromJson(response.errorBody().string(), JsonObject.class);
                        System.out.println("not sucess create credential = " + response.code() + " message = " + jObjError.get("message"));
                        updateUiError(jObjError.get("message").toString());
                    } catch (Exception e) {
                        System.out.println("error string errorBody");
                        updateUiError("error string errorBody");
                    }
                }
            }

            public void onFailure(Call<User> call, Throwable t) {
                System.out.println("On failure create credential : " + t.getMessage());
                updateUiError("On failure create credential : " + t.getMessage());
            }
        });
    }

    private void updateUiError(final String error) {
        Platform.runLater(new Runnable() {
            public void run() {
                sign_up_text_error.setText(error);
            }
        });
    }

    void updateUi() {
        Platform.runLater(new Runnable() {
            public void run() {
                try {
                    Pane main_pane;
                    main_pane = MainController.getmMainPane();
                    main_pane.getChildren().setAll((Pane) FXMLLoader.load(getClass().getResource("../Layout/login.fxml")));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

}
