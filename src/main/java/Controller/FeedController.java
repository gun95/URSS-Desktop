package Controller;

import Api.ApiManager;
import Model.response.Article;
import Model.response.Feed;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import javafx.application.HostServices;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Hyperlink;
import javafx.scene.image.Image;
import javafx.scene.image.ImageViewBuilder;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import javax.swing.text.html.ImageView;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by gun on 06/01/2017.
 * URSS-Desktop
 */
public class FeedController implements Initializable {

    @FXML
    private JFXListView<HBox> feed_jfxlistview;
    @FXML
    private JFXTextField feed_jfxtextField_search;
    @FXML
    private JFXButton feed_button_valider;
    private Feed feed;
    @FXML
    Text feed_text_error;
    @FXML
    private Hyperlink hyperlink;

    private HostServices hostServices;

    public HostServices getHostServices() {
        return hostServices;
    }

    public void setHostServices(HostServices hostServices) {
        this.hostServices = hostServices;
    }

    @FXML
    public void openURL() {
        hostServices.showDocument(hyperlink.getText());
    }


    public void initialize(URL location, ResourceBundle resources) {

        /*for (int i = 0; i < 10; i++) {
            HBox hBox = new HBox();
            try {
                hBox.getChildren().setAll((Pane) FXMLLoader.load(getClass().getResource("../Layout/list_feed.fxml")));
                feed_jfxlistview.getItems().add(hBox);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }*/

        feed = new Feed();
        feed_button_valider.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(javafx.scene.input.MouseEvent event) {
                feed_text_error.setText("");
                feed.setUrl(feed_jfxtextField_search.getText());
                if (!feed.getUrl().isEmpty()) {
                    postFeed();
                } else
                    feed_text_error.setText("search empty");

                System.out.println("search");

            }
        });
    }

    private void postFeed() {
        ApiManager.get().postFeed(feed).enqueue(new Callback<Feed>() {
            public void onResponse(Call<Feed> call, Response<Feed> response) {
                System.out.println("response = " + response.raw().toString());
                if (response.isSuccessful()) {
                    feed.setFeedId(response.body().getFeedId());
                    System.out.println("postFeed = " + feed.toString());
                    getFeed();

                } else {
                    try {
                        JsonObject jObjError = new JsonObject();
                        final Gson gson = new Gson();
                        jObjError = gson.fromJson(response.errorBody().string(), JsonObject.class);
                        System.out.println("not sucess postFeed = " + response.code() + " message = " + jObjError.get("message"));
                        updateUiError( jObjError.get("message").toString());
                    } catch (Exception e) {
                        System.out.println("error string errorBody");
                        updateUiError("error string errorBody");
                    }
                }
            }

            public void onFailure(Call<Feed> call, Throwable t) {
                System.out.println("On failure postFeed : " + t.getMessage());
                updateUiError("On failure postFeed : " + t.getMessage());
            }
        });
    }

    private void getFeed() {
        ApiManager.get().getFeed(feed.getFeedId()).enqueue(new Callback<Feed>() {
            public void onResponse(Call<Feed> call, Response<Feed> response) {
                System.out.println("response = " + response.raw().toString());
                if (response.isSuccessful()) {
                    feed.setArticleIdArrayList(response.body().getArticleIdArrayList());
                    System.out.println("size = " + feed.getArticleIdArrayList().size());
                    for (int i = 0; i < feed.getArticleIdArrayList().size(); i++) {
                        getArticle(feed.getArticleIdArrayList().get(i));
                    }

                } else {
                    try {
                        JsonObject jObjError = new JsonObject();
                        final Gson gson = new Gson();
                        jObjError = gson.fromJson(response.errorBody().string(), JsonObject.class);
                        System.out.println("not sucess getFeed = " + response.code() + " message = " + jObjError.get("message"));
                    } catch (Exception e) {
                        System.out.println("error string errorBody");
                    }
                }
            }

            public void onFailure(Call<Feed> call, Throwable t) {
                System.out.println("On failure getFeed : " + t.getMessage());
            }
        });
    }

    private void getArticle(String articleId) {
        ApiManager.get().getArticle(articleId).enqueue(new Callback<Article>() {
            public void onResponse(Call<Article> call, Response<Article> response) {
                System.out.println("response = " + response.raw().toString());
                if (response.isSuccessful()) {
                    System.out.println(response.body().getTitle());
                    feed.addArticleArrayList(response.body());
                    updateUi(response.body());
                } else {
                    try {
                        JsonObject jObjError = new JsonObject();
                        final Gson gson = new Gson();
                        jObjError = gson.fromJson(response.errorBody().string(), JsonObject.class);
                        System.out.println("not sucess getArticle = " + response.code() + " message = " + jObjError.get("message"));
                    } catch (Exception e) {
                        System.out.println("error string errorBody");
                    }
                }
            }

            public void onFailure(Call<Article> call, Throwable t) {
                System.out.println("On failure getArticle : " + t.getMessage());
            }
        });
    }

    private void updateUiError(final String error) {
        Platform.runLater(new Runnable() {
            public void run() {
                feed_text_error.setText(error);
            }
        });
    }

    private void updateUi(final Article article) {
        Platform.runLater(new Runnable() {
            public void run() {
                System.out.println("size2 = " + feed.getArticleArrayList().size());
                HBox hBox = new HBox();
                try {
                    hBox.getChildren().setAll((HBox) FXMLLoader.load(getClass().getResource("../Layout/list_feed.fxml")));

                    Text title = (Text) hBox.lookup("#list_feed_text_title");
                    title.setText(article.getTitle());

                    Text description = (Text) hBox.lookup("#list_feed_text_description");
                    description.setText(article.getDescription());

                    TextFlow description2 = (TextFlow) hBox.lookup("#list_feed_textflow_description");

                    if (!article.getEnclosureUrl().isEmpty()) {
                        javafx.scene.image.ImageView image = (javafx.scene.image.ImageView) hBox.lookup("#list_feed_imageview_picture");
                        image.setImage(new Image(article.getEnclosureUrl()));
                    }

                    JFXButton button = (JFXButton) hBox.lookup("#list_feed_button_go");
                    button.setOnMouseClicked(new EventHandler<MouseEvent>() {
                        public void handle(javafx.scene.input.MouseEvent event) {
                            hostServices = MainController.getHostServices();
                            System.out.println("go = " + article.getLink());
                            hostServices.showDocument(article.getLink());
                        }
                    });

                    feed_jfxlistview.getItems().add(hBox);
                } catch (IOException e) {
                    e.printStackTrace();
                }



                /*for (int i = 0; i < feed.getArticleArrayList().size(); i++) {
                    HBox hBox = new HBox();
                    try {
                        hBox.getChildren().setAll((Pane) FXMLLoader.load(getClass().getResource("../Layout/list_feed.fxml")));
                        ListFeedController.getList_feed_text_title().setText(feed.getArticleArrayList().get(i).getTitle());
                        feed_jfxlistview.getItems().add(hBox);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }*/

            }
        });
    }
}
