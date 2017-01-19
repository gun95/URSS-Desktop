package Controller;

import Api.ApiManager;
import Model.request.loginRequest;
import Model.response.Credential;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by gun on 06/01/2017.
 */
public class SignInController implements Initializable {

    @FXML
    JFXButton sign_in_button;
    @FXML
    JFXTextField sign_in__textfield_email;
    @FXML
    JFXTextField sign_in__textfield_password;
    @FXML
    JFXTextField sign_in__textfield_confirm_password;

    public void initialize(URL location, ResourceBundle resources) {

        sign_in_button.setOnMouseClicked(new EventHandler<javafx.scene.input.MouseEvent>() {
            public void handle(javafx.scene.input.MouseEvent event) {
                String email = sign_in__textfield_email.getText();
                String password = sign_in__textfield_password.getText();
                String passwordConfirm = sign_in__textfield_confirm_password.getText();
                System.out.println("sign_in");
                System.out.println("email = " + email);
                System.out.println("password = " + password);

                if (password.equals(passwordConfirm))
                    signIn(email, password);
                else
                    System.out.println("password doesn't match");
            }
        });
    }

    private void signIn(String email, String password) {
        ApiManager.get().createCredential(new loginRequest(email, password)).enqueue(new Callback<Credential>() {
            public void onResponse(Call<Credential> call, Response<Credential> response) {
                System.out.println("response = " + response.raw().toString());
                if (response.isSuccessful()) {
                    // normal que il y a que null pour le moment le serveur renvoi "id" au lieu de "userId" attendre que shadow change ca
                    System.out.println("create credential = " + response.body().toString());
                } else {
                    try {
                        JsonObject jObjError = new JsonObject();
                        final Gson gson = new Gson();
                        jObjError = gson.fromJson(response.errorBody().string(), JsonObject.class);
                        System.out.println("not sucess create credential = " + response.code() + " message = " + jObjError.get("message"));
                    } catch (Exception e) {
                        System.out.println("error string errorBody");
                    }
                }
            }

            public void onFailure(Call<Credential> call, Throwable t) {
                System.out.println("On failure create credential : " + t.getMessage());
            }
        });
    }


}
