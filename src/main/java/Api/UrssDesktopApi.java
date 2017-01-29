package Api;

import Model.request.RequestLogin;
import Model.response.Article;
import Model.response.History;
import Model.response.User;

import Model.response.Feed;
import retrofit2.Call;
import retrofit2.http.*;

/**
 * Created by gun on 13/01/2017.
 * URSS-Desktop
 */

public interface UrssDesktopApi {

    @POST("/auth/local")
    Call<User> login(@Body RequestLogin body);

    @POST("/api/users/createAccount")
    Call<User> createCredential(@Body RequestLogin body);

    @POST("/api/feeds/fromURL")
    Call<Feed> postFeed(@Body Feed url);

    @GET("/api/feeds/{id}")
    Call<Feed> getFeed(@Path("id") String feedId);

    @GET("/api/articles/{id}")
    Call<Article> getArticle(@Path("id") String articleId);

    @GET("/api/users/{id}")
    Call<User> getUser(@Header("Authorization")String authBearer ,@Path("id") String articleId);

    @GET("/api/histories/{id}")
    Call<History> getHistory(@Header("Authorization")String authBearer , @Path("id") String feedId);

    @PUT("/api/users/bookmarkFeed/{id}")
    Call<String> putBookmarks(@Header("Authorization")String authBearer ,@Path("id") String feedId);
}
