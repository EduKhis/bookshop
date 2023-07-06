package com.example.demo1.Controllers;

import com.example.demo1.models.Genre;
import com.example.demo1.models.Book;
import com.example.demo1.repoz.BookRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ComboBox;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class AdminPanelController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button adminPanelAddbookButton;

    @FXML
    private TextField adminPanelAuthor;

    @FXML
    private ComboBox<Genre> adminPanelGenre;
    @FXML
    private TextField adminPanelName;

    @FXML
    private TextField adminPanelPrice;

    @FXML
    private ComboBox<Integer> adminPanelQuantity;

    @FXML
    private TextField adminPanelEditId;

    @FXML
    private TextField adminPanelEditPrice;

    @FXML
    private ComboBox<Integer> adminPanelEditQuantity;
    @FXML
    private Button adminPanelEditbookButton1;
    @FXML
    private TextField adminPanelEditName;
    @FXML
    private Label adminPanelMessage;

    @FXML
    void initialize() {
        List<Genre> listGenre = List.of(Genre.values());  //выпадаюющий список жанров
        ObservableList<Genre> number = FXCollections.observableArrayList(listGenre);
        adminPanelGenre.setItems(number);

        List<Integer> list = IntStream.range(0, 11).boxed().collect(Collectors.toList()); //выпадаюющий список количества
        ObservableList<Integer> number2 = FXCollections.observableArrayList(list);
        adminPanelQuantity.setItems(number2);
        adminPanelEditQuantity.setItems(number2);

        adminPanelAddbookButton.setOnAction(x-> {
            try {
                BookRepository.addBook(new Book(adminPanelName.getText(), adminPanelAuthor.getText(), adminPanelPrice.getText(),String.valueOf(adminPanelQuantity.getValue()), adminPanelGenre.getValue()));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            } catch (InstantiationException e) {
                throw new RuntimeException(e);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        });
        adminPanelEditbookButton1.setOnAction(x-> {  //кнопка Edit book
            try {
                if (adminPanelEditId.getText().isEmpty() && adminPanelEditName.getText().isEmpty()) {
                    adminPanelMessage.setText("Input Id or Name");
                }
                else {
                        if (adminPanelEditId.getText().isEmpty()) {  //если ID не заполнено, заменяем по названию
                            BookRepository.editBookName(adminPanelEditName.getText(), adminPanelEditPrice.getText(),adminPanelEditQuantity.getValue());
                        }
                        else if (adminPanelEditName.getText().isEmpty()) { //если название не заполнено, заменяем по ID
                            BookRepository.editBookId(Integer.parseInt(adminPanelEditId.getText()),adminPanelEditPrice.getText(),adminPanelEditQuantity.getValue());
                        }
                        else {
                            if (BookRepository.checkIdName(Integer.parseInt(adminPanelEditId.getText()), adminPanelEditName.getText())) { //проверяем есть ли в базе одновременно совпадение по ID и по названию
                                BookRepository.editBookId(Integer.parseInt(adminPanelEditId.getText()),adminPanelEditPrice.getText(),adminPanelEditQuantity.getValue());
                            }
                            else {
                                adminPanelMessage.setText("Id or Name incorrect");
                            }
                        }
                    }

            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            } catch (InstantiationException e) {
                throw new RuntimeException(e);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
