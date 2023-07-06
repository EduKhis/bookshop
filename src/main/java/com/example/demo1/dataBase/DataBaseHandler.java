package com.example.demo1.dataBase;

import com.example.demo1.constants.Constants;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseHandler { //класс для подключения в базе данных
    public static Connection getConnection() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        String url = Constants.URL;
        String user = Constants.user;
        String password = Constants.password;

        Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
        return DriverManager.getConnection(url, user, password);
    }

}
