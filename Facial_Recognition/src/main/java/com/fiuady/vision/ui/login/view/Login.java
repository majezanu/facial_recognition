package com.fiuady.vision.ui.login.view;

import com.fiuady.vision.data.local.User;
import com.fiuady.vision.ui.login.presenter.LoginPresenter;
import com.fiuady.vision.ui.login.presenter.LoginPresenterImpl;
import com.jfoenix.controls.*;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class Login implements Initializable,LoginView {
    private String TAG = Login.class.getName();
    private LoginPresenter presenter;
    @FXML
    private AnchorPane rootPane;

    @FXML
    private JFXButton loginButton;

    @FXML
    private JFXTextField userNameField;

    @FXML
    private JFXPasswordField passwordField;

    @FXML
    private JFXToggleButton activateCamera;

    private JFXSnackbar loginSnackBar;
    private Stage stage;

    @FXML
    void login(ActionEvent event) {
        String username = userNameField.getText();
        String pass = passwordField.getText();
        presenter.validateLogin(new User(username,pass));
    }
    @FXML
    void facialRec(ActionEvent event) {
        presenter.activateCamera(activateCamera.isSelected());
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        presenter = new LoginPresenterImpl(this);
    }

    @Override
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @Override
    public Stage getStage() {
        return stage;
    }

    @Override
    public void showSnackBar(String s) {
        loginSnackBar =  new JFXSnackbar(rootPane);
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                loginSnackBar.show(s,2000);
            }
        });

    }

    @Override
    public void Log(String s) {
        System.out.println(TAG+": "+s);
    }

    @Override
    public void blockPasswordField(Boolean disable) {
        passwordField.setDisable(disable);
    }
}
