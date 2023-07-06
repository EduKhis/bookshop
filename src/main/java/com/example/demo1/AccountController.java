package com.example.demo1.Controllers;

import com.example.demo1.ReadingBookController;
import com.example.demo1.models.Book;
import com.example.demo1.models.Genre;
import com.example.demo1.repoz.BookRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AccountController {

    ObservableList<Book> list2 = FXCollections.observableArrayList();
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableColumn<Book, String> personalPageAuthor;

    @FXML
    private TableView<Book> personalPageTable;

    @FXML
    private TableColumn<Book, String> personalPageId;

    @FXML
    private TableColumn<Book, String> personalPageName;
    @FXML
    private TableColumn<Book, Genre> personalPageGenre;
    @FXML
    private TableColumn<Book, Integer> personalPageQuantity;
    @FXML
    private Label personalPageSumPurchase;
    @FXML
    private Button personalPageButtonBack;

    @FXML
    void initialize() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        addButtonToTable();

        list2.addAll(BookRepository.addPersonalPanel(Integer.parseInt(HelloController.globalId)));
        personalPageAuthor.setCellValueFactory(new PropertyValueFactory<Book, String>("author"));
        personalPageId.setCellValueFactory(new PropertyValueFactory<Book, String>("id"));
        personalPageName.setCellValueFactory(new PropertyValueFactory<Book, String>("name"));
        personalPageGenre.setCellValueFactory(new PropertyValueFactory<Book, Genre>("genre"));
        personalPageQuantity.setCellValueFactory(new PropertyValueFactory<Book, Integer>("quantity"));
        personalPageTable.setItems(list2);
        personalPageSumPurchase.setText(String.valueOf(BookRepository.sumPurchase(Integer.parseInt(HelloController.globalId))));
        personalPageButtonBack.setOnAction(x->{  //действие при нажатии на кнопку Регистрация
            try {
                openPage("welcome.fxml");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

    }
        private void addButtonToTable () {
            TableColumn<Book, Void> colBtn = new TableColumn(" ");
            Callback<TableColumn<Book, Void>, TableCell<Book, Void>> cellFactory = new Callback<TableColumn<Book, Void>, TableCell<Book, Void>>() {
                @Override
                public TableCell<Book, Void> call(final TableColumn<Book, Void> param) {
                    final TableCell<Book, Void> cell = new TableCell<Book, Void>() {

                        private final Button btn = new Button("reading");

                        {
                            btn.setOnAction((ActionEvent event) -> {
                                Book data = getTableView().getItems().get(getIndex());
                                ReadingBookController.idGlobalBook = String.valueOf(data.getId());
                                ReadingBookController.nameBook(data.getName(), data.getAuthor());
                                try {
                                    openPageBook("readingBook.fxml", btn);
                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                }

                            });
                        }

                        @Override
                        public void updateItem(Void item, boolean empty) {
                            super.updateItem(item, empty);
                            if (empty) {
                                setGraphic(null);
                            } else {
                                setGraphic(btn);
                            }
                        }
                    };
                    return cell;
                }
            };

        colBtn.setCellFactory(cellFactory);

            personalPageTable.getColumns().add(colBtn);


    }
    public  void openPage(String str) throws IOException {  //Переход между строками
        personalPageButtonBack.getScene().getWindow().hide();  //прячет окно

        FXMLLoader fxmlLoader=new FXMLLoader();   //открывает другое
        fxmlLoader.setLocation(getClass().getResource(str));
        fxmlLoader.load();
        Parent root=fxmlLoader.getRoot();
        Stage stage=new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }
    public  void openPageBook(String str, Button a) throws IOException {  //Переход между строками
        a.getScene().getWindow().hide();  //прячет окно
        FXMLLoader fxmlLoader=new FXMLLoader();   //открывает другое
        fxmlLoader.setLocation(getClass().getResource(str));
        fxmlLoader.load();
        Parent root=fxmlLoader.getRoot();
        Stage stage=new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

}
