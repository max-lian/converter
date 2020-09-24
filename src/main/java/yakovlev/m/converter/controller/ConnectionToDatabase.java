package yakovlev.m.converter.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@Component
@PropertySource("classpath:databaseConnection.properties")
public class ConnectionToDatabase
{

    // JDBC variables for opening and managing connection
    private static Connection con;

    private ConnectionToDatabase(@Value(value = "${url}") String url,
                                 @Value(value = "${user}") String user,
                                 @Value(value = "${password}") String password) throws SQLException
    {
        con = DriverManager.getConnection(url, user, password);
    }

    public Connection getConnection(){
        return con;
    }
}
