package com.example.demo1;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class WelcomeAdminController {
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label WelcomeAdminLabel;

    @FXML
    private Button welcomeAdminPanelButton;

    @FXML
    void initialize() {
        WelcomeAdminLabel.setText(HelloController.globalLogin);
        welcomeAdminPanelButton.setOnAction(x->{
            try {
                openPage("adminPanel.fxml");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
    public void openPage(String str) throws IOException {  //Переход между строками
        welcomeAdminPanelButton.getScene().getWindow().hide();  //прячет окно
        FXMLLoader fxmlLoader=new FXMLLoader();   //открывает другое
        fxmlLoader.setLocation(getClass().getResource(str));
        fxmlLoader.load();
        Parent root=fxmlLoader.getRoot();
        Stage stage=new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }
}
