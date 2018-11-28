package com.fiuady.vision.ui.register.view;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import javax.swing.text.html.ImageView;
import java.net.URL;
import java.util.ResourceBundle;

public class Register implements Initializable,RegisterView {

    @FXML
    private AnchorPane rootPane;

    @FXML
    private JFXTextField userNameField;

    @FXML
    private JFXPasswordField validatePasswordField;

    @FXML
    private JFXButton registerButton;

    @FXML
    private ImageView facialImage;

    @FXML
    private JFXButton capturePhoto;

    @FXML
    private JFXPasswordField passwordField;

    @FXML
    private JFXPasswordField telNumber;

    @FXML
    void capture(ActionEvent event) {

    }

    @FXML
    void register(ActionEvent event) {

    }
    @Override
    public void setStage(Stage stage) {

    }

    @Override
    public Stage getStage() {
        return null;
    }

    @Override
    public void showSnackBar(String s) {

    }

    @Override
    public void Log(String s) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
