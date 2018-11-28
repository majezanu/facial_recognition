package com.fiuady.vision;

import com.fiuady.vision.ui.login.view.Login;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import nu.pattern.OpenCV;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import org.opencv.imgcodecs.Imgcodecs;

public class App extends Application {
    public static void main(String[] args) {
        //launch(args);
        //OpenCV.loadLocally();
        OpenCV.loadShared();
        launch(args);
        //nu.pattern.OpenCV.loadShared();

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/login/login.fxml"));
        Parent root = (Parent)loader.load();
        Login login = (Login)loader.getController();
        login.setStage(primaryStage);
        Scene scene = new Scene(root);
        primaryStage.setTitle("Login");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
