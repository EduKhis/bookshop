package com.example.demo1.repoz;

import com.example.demo1.Genre;
import com.example.demo1.models.Book;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.example.demo1.dataBase.DataBaseHandler.getConnection;

public class BookRepository {

    public static void addBook(Book book) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        //добавляем книгу в базу
        PreparedStatement prepareStatement=getConnection().prepareStatement("INSERT INTO books(name,author,price,quantity,genre) VALUES (?,?,?,?,?)" );
        prepareStatement.setString(1, book.getName());
        prepareStatement.setString(2, book.getAuthor());
        prepareStatement.setString(3, book.getPrice());
        prepareStatement.setString(4, book.getQuantity());
        prepareStatement.setString(5, String.valueOf(book.getGenre()));
        prepareStatement.executeUpdate();
    }
    public static void editBookId (int id, String price, Integer quantity) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        //изменение книги по ID
        if (price.equals("")) { //если цена не заполнена, меняем количество
            PreparedStatement prepareStatement=getConnection().prepareStatement("UPDATE books SET quantity=? WHERE id=? ");
            prepareStatement.setInt(1, quantity);
            prepareStatement.setInt(2, id);
            prepareStatement.executeUpdate();
        }
        else if (quantity==null) { //если количество не заполнено, меняем цену
            PreparedStatement prepareStatement=getConnection().prepareStatement("UPDATE books SET price=? WHERE id=? ");
            prepareStatement.setString(1, price);
            prepareStatement.setInt(2, id);
            prepareStatement.executeUpdate();
        }
        else  {
            PreparedStatement prepareStatement=getConnection().prepareStatement("UPDATE books SET price=?, quantity=? WHERE id=? ");
            prepareStatement.setString(1,price);
            prepareStatement.setInt(2, quantity);
            prepareStatement.setInt(3, id);
            prepareStatement.executeUpdate();
        }
    }
    public static void editBookName (String name, String price, Integer quantity) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        //изменение книги по названию
        if (price.equals("")) { //если цена не заполнена, меняем количество
            PreparedStatement prepareStatement=getConnection().prepareStatement("UPDATE books SET quantity=? WHERE name=? ");
            prepareStatement.setInt(1, quantity);
            prepareStatement.setString(2, name);
            prepareStatement.executeUpdate();
        }
        else if (quantity==null) {  //если количество не заполнено, меняем цену
            PreparedStatement prepareStatement=getConnection().prepareStatement("UPDATE books SET price=? WHERE id=? ");
            prepareStatement.setString(1, price);
            prepareStatement.setString(2, name);
            prepareStatement.executeUpdate();
        }
        else  {
            PreparedStatement prepareStatement=getConnection().prepareStatement("UPDATE books SET price=?, quantity=? WHERE name=? ");
            prepareStatement.setString(1,price);
            prepareStatement.setInt(2, quantity);
            prepareStatement.setString(3, name);
            prepareStatement.executeUpdate();
        }
    }
    public static boolean checkIdName(int id, String name) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        //проверяем есть ли в базе одновременно совпадение по ID и по названию
        PreparedStatement prepareStatement=getConnection().prepareStatement("SELECT * FROM books WHERE id=? AND name=?");
        prepareStatement.setInt(1,id);
        prepareStatement.setString(2,name);
        ResultSet rs = prepareStatement.executeQuery();
        while(rs.next())
        {
            return true;
        }
        return false;
    }
    public static List<Book> listAddAll () throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        List<Book> list1 = new ArrayList<>();
        PreparedStatement prepareStatement=getConnection().prepareStatement("SELECT * FROM books");
        ResultSet rs = prepareStatement.executeQuery();
        while(rs.next())
        {
            list1.add(new Book(Integer.parseInt(rs.getString(1)), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), Genre.valueOf(rs.getString(6))));
        }
        return list1;
    }
    public static List<Book> checkQuantity () throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        List<Book>list = listAddAll();
        for (int i = 0; i < list.size(); i++) {
            if (Integer.parseInt(list.get(i).getQuantity())>=1) {
                list.get(i).setQuantity("available");
            }
            else {
                list.get(i).setQuantity("unavailable");
            }
        }
        return list;
    }
}
