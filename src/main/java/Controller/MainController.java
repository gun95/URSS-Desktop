package Controller;

import Api.ApiManager;
import Model.request.RequestLogin;
import Model.response.History;
import Model.response.User;
import Module.DatabaseHandler;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.jfoenix.controls.JFXButton;
import javafx.application.HostServices;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by gun on 06/01/2017.
 */
public class MainController implements Initializable {

    @FXML
    private JFXButton main_button_feed;
    @FXML
    private JFXButton main_button_login;
    @FXML
    private JFXButton main_button_sign_in;
    @FXML
    private Pane main_pane;

    private static HostServices hostServices ;

    public static HostServices getHostServices() {
        return hostServices ;
    }

    public  void setHostServices(HostServices hostServices) {
        this.hostServices = hostServices ;
    }

    @FXML
    private static Pane mMainPane;

    public static  Pane getmMainPane() {
        return mMainPane;
    }

    @FXML
    private static JFXButton mMainButtonLogin;
    @FXML
    private static JFXButton mMainButtonSignUp;

    public static void hideButton()
    {
        mMainButtonLogin.setVisible(false);
        mMainButtonLogin.setManaged(false);

        mMainButtonSignUp.setManaged(false);
        mMainButtonSignUp.setVisible(false);
    }

    private static History mHistory;
    private static User mUser;

    public static History getmHistory() {
        return mHistory;
    }

    public static void setmHistory(History mHistory) {
        MainController.mHistory = mHistory;
    }

    public static User getmUser() {
        return mUser;
    }

    public static void setmUser(User user) {
        mUser = user;
    }

    public void initialize(URL location, ResourceBundle resources) {

        mMainPane = main_pane;
        mMainButtonLogin = main_button_login;
        mMainButtonSignUp = main_button_sign_in;


        mUser = DatabaseHandler.getInstance().getAllUser();

        if (mUser != null && !mUser.getEmail().isEmpty() && !mUser.getPassword().isEmpty())
                login(mUser.getEmail(), mUser.getPassword());

            try {
            main_pane.getChildren().setAll((Pane) FXMLLoader.load(getClass().getResource("../Layout/feed.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
        }
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
                    main_pane.getChildren().setAll((Pane) FXMLLoader.load(getClass().getResource("../Layout/sign_up.fxml")));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void login(String email, String password) {
        ApiManager.get().login(new RequestLogin(email, password)).enqueue(new Callback<User>() {
            public void onResponse(Call<User> call, Response<User> response) {
                System.out.println("response = " + response.raw().toString());
                if (response.isSuccessful()) {
                    System.out.println("login = " + response.body().toString());
                    mUser.setToken(response.body().getToken());
                    mUser.setUserId(response.body().getUserId());
                    getUser();
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
            public void onFailure(Call<User> call, Throwable t) {
                System.out.println("On failure login : " + t.getMessage());
                updateUiError("On failure login : " + t.getMessage());
            }
        });
    }

    public void getUser() {
        System.out.println("token = " + mUser.getToken());
        ApiManager.get().getUser("Bearer " + mUser.getToken(), mUser.getUserId()).enqueue(new Callback<User>() {
            public void onResponse(Call<User> call, Response<User> response) {
                System.out.println("response = " + response.raw().toString());
                if (response.isSuccessful()) {
                    System.out.println("getUser = " + response.body().toString());
                    mUser.setHistory(response.body().getHistory());
                    mUser.setCredential(response.body().getCredential());
                    getHistory();
                } else {
                    try {
                        JsonObject jObjError = new JsonObject();
                        final Gson gson = new Gson();
                        jObjError = gson.fromJson(response.errorBody().string(), JsonObject.class);
                        System.out.println("not sucess getUser = " + response.code() + " message = " + jObjError.get("message"));


                        updateUiError(jObjError.get("message").toString());
                    } catch (Exception e) {
                        System.out.println("error string errorBody");
                        updateUiError("error string errorBody");
                    }
                }
            }
            public void onFailure(Call<User> call, Throwable t) {
                System.out.println("On failure getUser : " + t.getMessage());
                updateUiError("On failure getUser : " + t.getMessage());
            }
        });
    }

    public void getHistory() {
        System.out.println("token = " + mUser.getToken());
        ApiManager.get().getHistory("Bearer " + mUser.getToken(), mUser.getHistory()).enqueue(new Callback<History>() {
            public void onResponse(Call<History> call, Response<History> response) {
                System.out.println("response = " + response.raw().toString());
                if (response.isSuccessful()) {
                    System.out.println("getHistory = " + response.body().toString());
                    mHistory = response.body();
                } else {
                    try {
                        JsonObject jObjError = new JsonObject();
                        final Gson gson = new Gson();
                        jObjError = gson.fromJson(response.errorBody().string(), JsonObject.class);
                        System.out.println("not sucess getHistory = " + response.code() + " message = " + jObjError.get("message"));
                        updateUiError(jObjError.get("message").toString());
                    } catch (Exception e) {
                        System.out.println("error string errorBody");
                        updateUiError("error string errorBody");
                    }
                }
            }
            public void onFailure(Call<History> call, Throwable t) {
                System.out.println("On failure getHistory : " + t.getMessage());
                updateUiError("On failure getHistory : " + t.getMessage());
            }
        });
    }

    private void updateUiError(final String error)
    {
        Platform.runLater(new Runnable() {
            public void run() {
            }
        });
    }

    private void updateUi()
    {
        Platform.runLater(new Runnable() {
            public void run() {
                try {
                    main_pane.getChildren().setAll((Pane) FXMLLoader.load(getClass().getResource("../Layout/feed.fxml")));
                    hideButton();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

}