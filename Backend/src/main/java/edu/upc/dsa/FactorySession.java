package edu.upc.dsa;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;

public class FactorySession {

    public static Session openSession() throws IOException {

        Connection conn = getConnection();
        Session session = new SessionImpl(conn);
        return session;
    }



    private static Connection getConnection() throws IOException {

        Properties prop = new Properties();
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        InputStream stream = loader.getResourceAsStream("Credenciales.properties");
        prop.load(stream);

        String host = prop.getProperty("host");
        String port = prop.getProperty("port");
        String db = prop.getProperty("db");
        String user = prop.getProperty("user");
        String pass = prop.getProperty("pass");

        Connection conn = null;
        try {
            conn =
                    DriverManager.getConnection("jdbc:mariadb://"+host+":"+port+"/"+db);
                            //"?user="+user+"&password="+pass);

        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        return conn;
    }
}
