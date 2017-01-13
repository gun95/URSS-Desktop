package Api;

/**
 * Created by gun on 13/01/2017.
 * URSS-Desktop
 */

public class APIError {

    private int statusCode;
    private String message;

    public APIError() {
    }

    public int status() {
        return statusCode;
    }

    public String message() {
        return message;
    }

}