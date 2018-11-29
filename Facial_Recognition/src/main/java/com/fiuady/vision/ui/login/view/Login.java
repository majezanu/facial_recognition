package com.fiuady.vision.ui.login.view;

import com.fiuady.vision.data.local.User;
import com.fiuady.vision.ui.login.presenter.LoginPresenter;
import com.fiuady.vision.ui.login.presenter.LoginPresenterImpl;
import com.fiuady.vision.ui.register.view.Register;
import com.jfoenix.controls.*;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;
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

    @FXML
    private ImageView facialImage;

    @FXML
    private Label registerView;

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
    public void setFacialImage(Image i) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                facialImage.setImage(i);
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
    @FXML
    void Register(MouseEvent event) {
        Log("Open view rergister");
        presenter.releaseCamera();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/register/register.fxml"));
            Parent registerParent = (Parent) loader.load();
            Stage registerStage = new Stage();
            Register register = (Register) loader.getController();
            registerStage.setTitle("Registro");
            registerStage.setScene(new Scene(registerParent));
            registerStage.show();
            register.setStage(registerStage);
            stage.close();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
