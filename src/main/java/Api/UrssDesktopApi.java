package Api;

import Model.Credential;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by gun on 13/01/2017.
 * URSS-Desktop
 */

public interface UrssDesktopApi {

    @FormUrlEncoded
    @POST("/auth/local")
    Call<Credential> Login(@Field("email") String email,
                                      @Field("password") String password);

    @FormUrlEncoded
    @POST("/api/credentials/")
    Call<Credential> createCredential(@Field("email") String email,
                                      @Field("password") String password);
}
