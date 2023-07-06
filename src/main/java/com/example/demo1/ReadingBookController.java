package com.example.demo1;

import com.example.demo1.repoz.BookRepository;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ReadingBook {
    public static String idGlobalBook;
    public static String nameGlobalBook;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label readingBookNameBook;

    @FXML
    private TextArea readingBookTextBook;

    @FXML
    void initialize() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        readingBookTextBook.setText(BookRepository.openBook(Integer.parseInt(idGlobalBook)));
        readingBookNameBook.setText(nameGlobalBook);

    }
    public static void nameBook (String a, String b) {
        nameGlobalBook= a+ " " + b;
    }
}
