package Model.request;

/**
 * Created by gun on 19/01/2017.
 * URSS-Desktop
 */
public class loginRequest {
    final String email;
    final String password;

   public loginRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
