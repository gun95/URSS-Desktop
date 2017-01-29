package Model.response;

import com.google.gson.annotations.SerializedName;

/**
 * Created by gun on 14/01/2017.
 * URSS-Desktop
 */
public class User {

    private String email;
    private String password;
    @SerializedName("token")
    private String token;
    @SerializedName("userId")
    private String userId;
    @SerializedName("history")
    private String history;
    @SerializedName("credential")
    private String credential;

    public User(String token, String userId) {
        this.token = token;
        this.userId = userId;
    }

    public User() {
    }

    public String getHistory() {
        return history;
    }

    public void setHistory(String history) {
        this.history = history;
    }

    public String getCredential() {
        return credential;
    }

    public void setCredential(String credential) {
        this.credential = credential;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", token='" + token + '\'' +
                ", userId='" + userId + '\'' +
                ", history='" + history + '\'' +
                ", credential='" + credential + '\'' +
                '}';
    }
}
