package com.example.demo1.repoz;

import com.example.demo1.models.User;

import java.io.IOException;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.example.demo1.dataBase.DataBaseHandler.getConnection;

public class UserRepository {  //класс для заполнения таблицы SQL
    public static void addTable (User user) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        PreparedStatement prepareStatement=getConnection().prepareStatement("INSERT INTO users(login,password, date, email, role) VALUES (?,?,?,?,?)" );
        prepareStatement.setString(1, user.getLogin());
        prepareStatement.setString(2, user.getPassword());
        prepareStatement.setDate(3, new Date(user.getDate().getTime()));
        prepareStatement.setString(4, user.getEmail());
        prepareStatement.setString(5,"USER");
        prepareStatement.executeUpdate();
    }

    public static boolean checkLogin(String login) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        PreparedStatement prepareStatement=getConnection().prepareStatement("SELECT * FROM users WHERE login=?");
        prepareStatement.setString(1,login);
        ResultSet rs = prepareStatement.executeQuery();
        while(rs.next())
        {
            return true;
        }
        return false;
    }
    public static boolean checkEmail(String email) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        PreparedStatement prepareStatement=getConnection().prepareStatement("SELECT * FROM users WHERE email=?");
        prepareStatement.setString(1,email);
        ResultSet rs = prepareStatement.executeQuery();
        while(rs.next())
        {
            return true;
        }
        return false;
    }
    public static boolean authentication(String login, String password) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        PreparedStatement prepareStatement=getConnection().prepareStatement("SELECT * FROM users WHERE login=? AND password=?");
        prepareStatement.setString(1,login);
        prepareStatement.setString(2,password);
        ResultSet rs = prepareStatement.executeQuery();
        while(rs.next())
        {
            return true;
        }
        return false;
    }
    public static boolean authenticationLogin (String login) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        PreparedStatement prepareStatement=getConnection().prepareStatement("SELECT * FROM users WHERE login=? ");
        prepareStatement.setString(1,login);
        ResultSet rs = prepareStatement.executeQuery();
        while(rs.next())
        {
            return true;
        }
        return false;
    }
    public static boolean authenticationPassword (String password) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        PreparedStatement prepareStatement=getConnection().prepareStatement("SELECT * FROM users WHERE password=?");
        prepareStatement.setString(1,password);
        ResultSet rs = prepareStatement.executeQuery();
        while(rs.next())
        {
            return true;
        }
        return false;
    }
    public static String authentificationAdmin ( String login, String password) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        PreparedStatement prepareStatement=getConnection().prepareStatement("SELECT role FROM users WHERE login=? AND password=?");
        prepareStatement.setString(1,login);
        prepareStatement.setString(2,password);
        ResultSet rs = prepareStatement.executeQuery();
        while(rs.next())
        {
            return rs.getString(1);
        }
        return null;
    }
    public static String authentificationIdAdmin ( String login, String password) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        PreparedStatement prepareStatement=getConnection().prepareStatement("SELECT id FROM users WHERE login=? AND password=?");
        prepareStatement.setString(1,login);
        prepareStatement.setString(2,password);
        ResultSet rs = prepareStatement.executeQuery();
        while(rs.next())
        {
            return rs.getString(1);
        }
        return null;
    }
}
