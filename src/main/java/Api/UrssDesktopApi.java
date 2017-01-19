package Api;

import Model.request.loginRequest;
import Model.response.Credential;

import retrofit2.Call;
import retrofit2.http.*;

/**
 * Created by gun on 13/01/2017.
 * URSS-Desktop
 */

public interface UrssDesktopApi {


    @POST("/auth/local")
    Call<Credential> login(@Body loginRequest body);

    @POST("/api/credentials/")
    Call<Credential> createCredential(@Body loginRequest body);
}
