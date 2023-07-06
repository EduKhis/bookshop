package com.example.demo1;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;

import com.example.demo1.repoz.UserRepository;
import com.example.demo1.models.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class RegistrationController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button regPageAutorisationButton;

    @FXML
    private TextField regPageEmailInput;

    @FXML
    private TextField regPageLoginInput;

    @FXML
    private PasswordField regPagePasswordInput;

    @FXML
    private Button regPageRegistrationButton;

    @FXML
    private Label regPageMessage;

    @FXML
    private PasswordField regPageRepeatPasswordInput;

    @FXML
    void initialize() {
        regPageAutorisationButton.setOnAction(x->{
            try {
                openPage("hello-view.fxml");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        regPageRegistrationButton.setOnAction(x->{
            try {
                if (!regPagePasswordInput.getText().equals(regPageRepeatPasswordInput.getText())) {
                    regPageMessage.setText("Password should be same");
                }
                else if (UserRepository.checkLogin(regPageLoginInput.getText())) {
                    regPageMessage.setText("This login already used");
                }
                else if (UserRepository.checkEmail(regPageEmailInput.getText())) {
                    regPageMessage.setText("This email already used");
                }
                else {
                    UserRepository.addTable(new User(regPageLoginInput.getText(),regPagePasswordInput.getText(),new Date(),regPageEmailInput.getText()));
                    openPage("hello-view.fxml");
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
        regPageAutorisationButton.getScene().getWindow().hide();  //прячет окно
        FXMLLoader fxmlLoader=new FXMLLoader();   //открывает другое
        fxmlLoader.setLocation(getClass().getResource(str));
        fxmlLoader.load();
        Parent root=fxmlLoader.getRoot();
        Stage stage=new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

}
