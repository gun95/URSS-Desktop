package Api;

import Model.request.loginRequest;
import Model.response.Article;
import Model.response.Credential;

import Model.response.Feed;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

/**
 * Created by gun on 13/01/2017.
 * URSS-Desktop
 */

public interface UrssDesktopApi {


    @POST("/auth/local")
    Call<Credential> login(@Body loginRequest body);

    @POST("/api/credentials/")
    Call<Credential> createCredential(@Body loginRequest body);

    @POST("/api/feeds/fromURL")
    Call<Feed> postFeed(@Body Feed url);

    @GET("/api/feeds/{id}")
    Call<Feed> getFeed(@Path("id") String feedId);

    @GET("/api/articles/{id}")
    Call<Article> getArticle(@Path("id") String articleId);
}
