package Api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by gun on 13/01/2017.
 * URSS-Desktop
 */

public class ApiManager {
    private static UrssDesktopApi restClient;
    private static Retrofit retrofit;
    private static String mUrl = "http://137.74.166.198";
    private static String mPort = ":4242/";

    static {
        setupRestClient();
    }

    private ApiManager() {
    }

    public static Retrofit getRetrofit() {
        return (retrofit);
    }

    public static UrssDesktopApi get() {
        return (restClient);
    }


    private static void setupRestClient() {
        retrofit = new Retrofit.Builder()
                .baseUrl(mUrl + mPort)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        restClient = retrofit.create(UrssDesktopApi.class);
    }
}