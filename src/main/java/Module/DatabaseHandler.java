package Module;

import Model.request.RequestLogin;
import Model.response.User;

import java.sql.*;

/**
 * Created by gun on 19/01/2017.
 * URSS-Desktop
 */
public class DatabaseHandler {

    private Connection mConnection;
    private static String DB_URL = "jdbc:sqlite:urss.db";
    private static String DB_NAME = "org.sqlite.JDBC";
    private static final String KEY_ID = "rowId";
    private Statement stmt = null;

    // Tables names
    private static final String TABLE_USER = "user";
    private static final String TABLE_ = "bookmark";

    // user Table Columns names
    private static final String KEY_USER_EMAIL = "email";
    private static final String KEY_USER_PASSWORD = "password";

    //

    /**
     * Instance unique non préinitialisée
     */
    private static DatabaseHandler mInstance = null;

    /**
     * Point d'accès pour l'instance unique du singleton
     */
    public static DatabaseHandler getInstance() {
        if (mInstance == null) {
            try {
                mInstance = new DatabaseHandler();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return mInstance;
    }

    /**
     * Constructeur public
     */
    private DatabaseHandler() throws SQLException {
    }

    public void initDb() {
        try {
            Class.forName(DB_NAME);
            mConnection = DriverManager.getConnection(DB_URL);
            createTableUser();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Opened database successfully");
    }

    private void createTableUser() {
        try {
            stmt = mConnection.createStatement();
            String CREATE_USER_TABLE = "CREATE TABLE if not exists " + TABLE_USER + "("
                    + KEY_ID + " INTEGER PRIMARY KEY,"
                    + KEY_USER_EMAIL + " TEXT,"
                    + KEY_USER_PASSWORD + " TEXT" + ");";

            stmt.executeUpdate(CREATE_USER_TABLE);
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addUser(User user) {
        try {
            stmt = mConnection.createStatement();
            String add = "INSERT INTO " + TABLE_USER + " (" + KEY_ID + " ," + KEY_USER_EMAIL + " ," + KEY_USER_PASSWORD + ") " +
                    "VALUES (1,'" + user.getEmail() + "', '" + user.getPassword() + "');";
            stmt.executeUpdate(add);
            System.out.println("add = " + add);
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteUser(User user)
    {
        try {
            stmt = mConnection.createStatement();
            String del = "DELETE from " + TABLE_USER +" where "+ KEY_USER_EMAIL + "='" + user.getEmail() + "';";
            stmt.executeUpdate(del);
            System.out.println("del = " + del);
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User getAllUser() {
        try {
            stmt = mConnection.createStatement();
            String get = "SELECT * FROM " + TABLE_USER + ";";
            ResultSet rs = stmt.executeQuery(get);
            while (rs.next()) {
                int id = rs.getInt(KEY_ID);
                String email = rs.getString(KEY_USER_EMAIL);
                String password = rs.getString(KEY_USER_PASSWORD);
                System.out.println("ID = " + id);
                System.out.println("email = " + email);
                System.out.println("password = " + password);
                User user = new User();
                user.setEmail(email);
                user.setPassword(password);
                return user;
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}