package DBProsjekt;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;


public abstract class DBConn {

    /*
    private final String DB_USERNAME = "fs_tdt4145_1_gruppe33";
    private final String DB_PASSWORD = "hello123";
     */

    private final String DB_NAME = "fs_tdt4145_1_gruppe33";
    /* navn og brukernavn på databasen må være sånn her for å kunne opprette en database i NTNU sine servere,
    så det er derfor de ser litt rare ut. Ikke bry deg så mye om akkurat hvordan de ser ut
     */

    protected Connection conn;
    public DBConn () {}; //unødvendig her, men why not :'))
    public void connect() {
        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            // Properties for user and password.
            Properties p = new Properties();
            p.put("user", privateShit.DB_USERNAME);
            p.put("password", privateShit.DB_PASSWORD);

            /* conn er objektet som kobles til databasen fs_tdt4145_1_gruppe33 hos domenet mysql.stud.ntnu.no */
            //conn = DriverManager.getConnection("jdbc:mysql://mysql.stud.ntnu.no:3306/fs_tdt4145_1_gruppe33?autoReconnect=true&useSSL=false", p);
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/DBProsjekt?autoReconnect=true&useSSL=false", p);
        } catch (Exception e) {
            throw new RuntimeException("Unable to connect", e);
        }
    }

    public static void main(String[] args) {
        System.out.println("Hello world");
    }

    }
