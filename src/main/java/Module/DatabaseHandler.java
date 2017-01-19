package Module;

/**
 * Created by gun on 19/01/2017.
 * URSS-Desktop
 */
public class DatabaseHandler {


    /**
     * Instance unique non préinitialisée
     */
    private static DatabaseHandler mInstance = null;

    /**
     * Constructeur privé
     */
    private DatabaseHandler() {
    }

    /**
     * Point d'accès pour l'instance unique du singleton
     */
    public static DatabaseHandler getInstance() {
        if (mInstance == null) {
            mInstance = new DatabaseHandler();
        }
        return mInstance;
    }


}