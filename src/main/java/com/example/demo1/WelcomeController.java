package com.example.demo1.Controllers;

import com.example.demo1.Controllers.HelloController;
import com.example.demo1.models.Book;
import com.example.demo1.models.Genre;
import com.example.demo1.repoz.BookRepository;
import com.example.demo1.repoz.UserRepository;
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

public class WelcomeController {
    ObservableList<Book> list2 = FXCollections.observableArrayList();
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    @FXML
    private Label WelcomeLabel;
    @FXML
    private Label WelcomeBalance;

    @FXML
    private Button WelcomeCoinsButtonPutOn;

    @FXML
    private TextField WelcomeCoinsPutOn;
    @FXML
    private Label WelcomeInfo;
    @FXML
    private TableView<Book> WelcomeTable;

    @FXML
    private TableColumn<Book, String> WelcomeTableAuthor;

    @FXML
    private TableColumn<Book, Genre> WelcomeTableGenre;

    @FXML
    private TableColumn<Book, String> WelcomeTableName;

    @FXML
    private TableColumn<Book, String> WelcomeTablePrice;
    @FXML
    private TableColumn<Book, String> WelcomeTableId;

    @FXML
    private TableColumn<Book, String> WelcomeTableAvailable;
    @FXML
    private Button goToPersonalPage;


    public WelcomeController() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
    }

    @FXML
    void initialize() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        initData();
        addButtonToTable();

        WelcomeCoinsButtonPutOn.setOnAction(x->{  //действие при нажатии на кнопку пополнения баланса
            try {
                UserRepository.putOnBalance(Integer.parseInt(HelloController.globalId), Integer.parseInt(WelcomeCoinsPutOn.getText()));
                WelcomeBalance.setText(String.valueOf(UserRepository.checkBalanceUser(Integer.parseInt(HelloController.globalId))));
            } catch (SQLException | ClassNotFoundException | InstantiationException |
                     IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        });
        goToPersonalPage.setOnAction(x->{  //действие при нажатии на кнопку Регистрация
            try {
                openPage("personalPage.fxml");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private void addButtonToTable() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {

        TableColumn<Book, Void> colBtn = new TableColumn(" ");
        Callback<TableColumn<Book, Void>, TableCell<Book, Void>> cellFactory = new Callback<TableColumn<Book, Void>, TableCell<Book, Void>>() {
            @Override
            public TableCell<Book, Void> call(final TableColumn<Book, Void> param) {
                final TableCell<Book, Void> cell = new TableCell<Book, Void>() {

                    private final Button btn = new Button("buy");

                    {
                        btn.setOnAction((ActionEvent event) -> {
                            Book data = getTableView().getItems().get(getIndex());
                            System.out.println("selectedData: " + data);
                            try {
                                if (checkBalance(data.getPrice())&&data.getQuantity().equals("available")){ //проверка баланса, есть ли возможность купить книгу
                                    //&&Integer.parseInt(data.getQuantity())>0
                                    BookRepository.addPurchase(Integer.parseInt(HelloController.globalId), data.getId());
                                    UserRepository.createBalance(Integer.parseInt(HelloController.globalId),Integer.parseInt(data.getPrice()));
                                    BookRepository.createQuantity(data.getId());
                                    initData();
                                }
                                else {
                                    btn.setDisable(true);
                                    WelcomeInfo.setText("Put on balance");
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

            WelcomeTable.getColumns().add(colBtn);



    }
    private boolean checkBalance (String price) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        //проверка баланса, есть ли возможность купить книгу
        if (Integer.parseInt(UserRepository.checkBalanceUser(Integer.parseInt(HelloController.globalId)))<Integer.parseInt(price)) {
                return false;
        }
        else {
            return true;
        }
    }
    public  void openPage(String str) throws IOException {  //Переход между строками
        goToPersonalPage.getScene().getWindow().hide();  //прячет окно
        FXMLLoader fxmlLoader=new FXMLLoader();   //открывает другое
        fxmlLoader.setLocation(getClass().getResource(str));
        fxmlLoader.load();
        Parent root=fxmlLoader.getRoot();
        Stage stage=new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }
    public void initData() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        WelcomeLabel.setText(HelloController.globalLogin);  //вывод логина на экран//
        WelcomeBalance.setText(String.valueOf(UserRepository.checkBalanceUser(Integer.parseInt(HelloController.globalId))));//вывод баланса на экран
        //WelcomeBalance.setText(""+(UserRepository.checkBalanceUser(Integer.parseInt(HelloController.globalId))));
        //UserRepository.checkBalanceUser(12);
        list2.clear();
        list2.addAll(BookRepository.checkQuantity());  //выводим табличку на экран, подгружается из базы книг
        WelcomeTableName.setCellValueFactory(new PropertyValueFactory<Book, String>("name"));
        WelcomeTableId.setCellValueFactory(new PropertyValueFactory<Book, String>("id"));
        WelcomeTableId.setVisible(false);
        WelcomeTableAuthor.setCellValueFactory(new PropertyValueFactory<Book, String>("author"));
        WelcomeTablePrice.setCellValueFactory(new PropertyValueFactory<Book, String>("price"));
        WelcomeTableGenre.setCellValueFactory(new PropertyValueFactory<Book, Genre>("genre"));
        WelcomeTableAvailable.setCellValueFactory(new PropertyValueFactory<Book, String>("quantity"));

        WelcomeTable.setItems(list2);

    }
}
