package com.fiuady.vision.ui.register.view;

import com.fiuady.vision.data.local.User;
import com.fiuady.vision.ui.login.view.Login;
import com.fiuady.vision.ui.register.presenter.RegisterPresenter;
import com.fiuady.vision.ui.register.presenter.RegisterPresenterImpl;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXSnackbar;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Register implements Initializable,RegisterView {

    private JFXSnackbar loginSnackBar;
    private Stage stage;
    private RegisterPresenter presenter;
    private String userName;
    private String password;
    private String passwordAgain;
    private String number;
    private User user;
    @FXML
    private AnchorPane rootPane;

    @FXML
    private JFXTextField userNameField;

    @FXML
    private JFXPasswordField validatePasswordField;

    @FXML
    private JFXButton registerButton;

    @FXML
    private JFXButton capturePhoto;

    @FXML
    private JFXPasswordField passwordField;

    @FXML
    private JFXPasswordField telNumber;

    @FXML
    private JFXButton loginButton;

    @FXML
    private ImageView imageFaces;

    @FXML
    void capture(ActionEvent event) {

    }

    @FXML
    void login(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/login/login.fxml"));
            Parent loginParent = (Parent) loader.load();
            Stage loginStage = new Stage();
            Login login = (Login)loader.getController();
            loginStage.setTitle("Login");
            loginStage.setScene(new Scene(loginParent));
            loginStage.show();
            login.setStage(loginStage);

            stage.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void register(ActionEvent event) {
        getFields();
        presenter.validateUser(userName,password,passwordAgain,number);
    }

    @Override
    public void setStage(Stage stage) {
        this.stage = stage;
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent we) {
                System.out.println("Stage is closing");
                presenter.releaseCamera();
            }
        });
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
        System.out.println("Se capturan las fotos");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        presenter = new RegisterPresenterImpl(this);
        presenter.activateCamera(true);
    }

    @Override
    public void setFacialImage(Image i) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                imageFaces.setImage(i);
            }
        });
    }

    private void getFields(){
        userName = userNameField.getText();
        password = passwordField.getText();
        passwordAgain = validatePasswordField.getText();
        number = telNumber.getText();
    }
}
