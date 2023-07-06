package com.example.demo1.Controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.example.demo1.repoz.BookRepository;
import com.example.demo1.repoz.UserRepository;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class HelloController {
    public static String globalLogin;
    public static String globalId;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button mainPageAutorisationButton;

    @FXML
    private TextField mainPageLoginInput;

    @FXML
    private PasswordField mainPagePasswordInput;

    @FXML
    private Button mainPageRegistrationButton;

    @FXML
    private Label mainPageMessage;


    @FXML
    void initialize() {
        mainPageRegistrationButton.setOnAction(x->{  //действие при нажатии на кнопку Регистрация
            try {
                openPage("registration.fxml");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        mainPageAutorisationButton.setOnAction(x->{ //кнопка авторизация
            try {
                if (!UserRepository.authentication(mainPageLoginInput.getText(), mainPagePasswordInput.getText())) {  //если в базе нет такого логина и пароля
                    if (UserRepository.authenticationLogin(mainPageLoginInput.getText())) {   //если в базе есть такой логин
                        mainPageMessage.setText("This password incorrect");
                    }
                    else {
                        mainPageMessage.setText("This login incorrect");
                    }
                }
                else if (UserRepository.authentificationAdmin(mainPageLoginInput.getText(), mainPagePasswordInput.getText()).equals("admin")) { //если в базе это админ
                    globalLogin=mainPageLoginInput.getText();
                    globalId=UserRepository.authentificationIdAdmin(mainPageLoginInput.getText(), mainPagePasswordInput.getText());
                    openPage("welcomeAdministrationAdmin.fxml");

                }
                else if (UserRepository.authentificationAdmin(mainPageLoginInput.getText(), mainPagePasswordInput.getText()).equals("USER")){  //если в базе это юзер
                    globalLogin=mainPageLoginInput.getText();
                    globalId=UserRepository.authentificationIdAdmin(mainPageLoginInput.getText(), mainPagePasswordInput.getText());
                    openPage("welcome.fxml");
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            } catch (InstantiationException e) {
                throw new RuntimeException(e);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
    public  void openPage(String str) throws IOException {  //Переход между строками
        mainPageRegistrationButton.getScene().getWindow().hide();  //прячет окно
        FXMLLoader fxmlLoader=new FXMLLoader();   //открывает другое
        fxmlLoader.setLocation(getClass().getResource(str));
        fxmlLoader.load();
        Parent root=fxmlLoader.getRoot();
        Stage stage=new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

}
