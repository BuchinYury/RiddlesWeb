package models.connector;

/**
 * Created by yuri on 24.02.17.
 */
public class DBConst {
    public static final String USERNAME = "root";
    public static final String PASS = "455415";
    public static final String URL = "jdbc:mysql://localhost:3306/studentdb?useSSL=false";
    public static final String DRIVER = "com.mysql.jdbc.Driver";

    public static ConnectionPool connectionPool = new ConnectionPool();
}
