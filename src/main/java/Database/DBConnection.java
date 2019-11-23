package Database;

import java.sql.*;

public class DBConnection {

    private static final String dburl = "wouldyourather.cpgtolsgo7aa.us-east-1.rds.amazonaws.com";
    private static final String port = "3306";
    private static final String username = "admin";
    private static final String password = "hayastan5709";
    private static final String defaultDB = "dbvtwo";

    public static Connection getConnection() {
        Connection conn = null;
        try {
             conn = DriverManager.getConnection("jdbc:mysql://" + dburl + ":" + port + "/" +
                    defaultDB, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static void closeConnection(Connection conn, Statement stmt, ResultSet rs) {
        try {
            conn.close();
            stmt.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void closeConnection(Connection conn, PreparedStatement stmt, ResultSet rs) {
        try {
            conn.close();
            stmt.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void closeConnection(Connection conn, PreparedStatement stmt) {
        try {
            conn.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
