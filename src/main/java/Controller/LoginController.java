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
import javafx.concurrent.Task;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

    @FXML
    private Pane main_pane;

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

                    //TODO je n arrive pas a changer le main_pane
                        Task task = new Task<Void>() {
                            @Override public Void call()  {
                                System.out.println("1");
                                try {
                                    System.out.println("2");
                                    main_pane = MainController.getmMainPane();
                                    main_pane.getChildren().setAll((Pane) FXMLLoader.load(getClass().getResource("../Layout/sign_in.fxml")));


                                    System.out.println("end");
                                } catch (IOException e) {
                                    e.printStackTrace();
                                    System.out.println("error");
                                }
                                System.out.println("3");
                                return null;
                            }
                        };
                        new Thread(task).start();
                       System.out.println("run");

//                        main_pane.getChildren().add((Pane) FXMLLoader.load(getClass().getResource("../Layout/feed.fxml")));



                } else {
                    try {
                        JsonObject jObjError = new JsonObject();
                        final Gson gson = new Gson();
                        jObjError = gson.fromJson(response.errorBody().string(), JsonObject.class);
                        System.out.println("not sucess login = " + response.code() + " message = " + jObjError.get("message"));
                    } catch (Exception e) {
                        System.out.println("error string errorBody");
                    }
                }
            }

            public void onFailure(Call<Credential> call, Throwable t) {
                System.out.println("On failure login : " + t.getMessage());
            }
        });
    }
}
