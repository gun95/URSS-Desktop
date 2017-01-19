package Model.response;

import com.google.gson.annotations.SerializedName;

/**
 * Created by gun on 14/01/2017.
 * URSS-Desktop
 */
public class Credential {
    @SerializedName("token")
    private String token;

    @SerializedName("userId")
    private String userId;


    public Credential(String token, String userId) {
        this.token = token;
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getuserId() {
        return userId;
    }

    public void setuserId(String userId) {
        this.userId = userId;
    }


    @Override
    public String toString() {
        return "Credential{" +
                "token='" + token + '\'' +
                ", userId='" + userId + '\'' +
                '}';
    }
}
