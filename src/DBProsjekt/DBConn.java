package DBProsjekt;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;


public abstract class DBConn {

    private final String DB_USERNAME = "fs_tdt4145_1_gruppe33";
    private final String DB_PASSWORD = "hello123";
    private final String DB_NAME = "fs_tdt4145_1_gruppe33";

    protected Connection conn;
    public DBConn () {};
    public void connect() {
        try {
            //Class.forName("com.mysql.jdbc.Driver").newInstance();
            Class.forName("com.mysql.cj.jdbc.Driver"); //when you are using MySQL 8.0.
            // Properties for user and password.
            Properties p = new Properties();
            p.put("user", DB_USERNAME);
            p.put("password", DB_PASSWORD);
            //conn = DriverManager.getConnection("jdbc:mysql://mysql.ansatt.ntnu.no/sveinbra_ektdb?autoReconnect=true&useSSL=false",p);
            conn = DriverManager.getConnection("jdbc:mysql://mysql.stud.ntnu.no:3306/fs_tdt4145_1_gruppe33?autoReconnect=true&useSSL=false", p);
        } catch (Exception e) {
            throw new RuntimeException("Unable to connect", e);
        }
    }

    public static void main(String[] args) {
        System.out.println("Hello world");
    }

    }
