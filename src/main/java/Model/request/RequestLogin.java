package Model.request;

/**
 * Created by gun on 19/01/2017.
 * URSS-Desktop
 */
public class RequestLogin {

    private String email;
    private String password;

    public RequestLogin(String email, String password) {
        this.email = email;
        this.password = password;
    }
}

