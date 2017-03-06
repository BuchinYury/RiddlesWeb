package io.buchin.models.connector;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 * Created by yuri on 24.02.17.
 */
public class ConnectionPool {
    private static Logger logger = Logger.getLogger(ConnectionPool.class);

    private LinkedList<Connection> availableConns = new LinkedList<>();
    private LinkedList<Connection> usedConns = new LinkedList<>();

    private final String DRIVER = "com.mysql.jdbc.Driver";

    private final String URL = "jdbc:mysql://localhost:3306/Riddles?useSSL=false";
    private final String USERNAME = "root";
    private final String PASS = "455415";

    public ConnectionPool() {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            logger.error("В системе отсутствует MySQL JDBC Driver");
        }
    }

    private Connection getConnection() throws SQLException {
        logger.trace("Генерируется новое подключение");
        Connection conn = DriverManager.getConnection(URL, USERNAME, PASS);

        return conn;
    }

    public synchronized Connection retrieve() throws SQLException {
        logger.trace("Получаем доступное подключение из пула");
        Connection newConn;
        if (availableConns.size() == 0) {
            logger.trace("AvailableConns size = 0");
            newConn = getConnection();
        } else {
            logger.trace("AvailableConns size != 0");
            newConn = availableConns.getLast();
            availableConns.remove(newConn);
        }
        usedConns.add(newConn);
        return newConn;
    }

    public synchronized void putback(Connection c) {
        logger.trace("Возвращаем подключение в пулл");

        if (c != null) {
            if (usedConns.remove(c)) {
                availableConns.add(c);
            } else {
                logger.error("Возвращено левое подключение");
            }
        }
    }

    public int getAvailableConnsCnt() {return availableConns.size();}

    public int getUsedConnsCnt() {return usedConns.size();}
}

