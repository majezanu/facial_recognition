package com.fiuady.vision;

import javafx.application.Application;
import javafx.stage.Stage;
import nu.pattern.OpenCV;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import org.opencv.imgcodecs.Imgcodecs;

public class Main extends Application {
    public static void main(String[] args) {
        //launch(args);
        //OpenCV.loadLocally();
        OpenCV.loadShared();
        launch(args);
        //nu.pattern.OpenCV.loadShared();

    }

    @Override
    public void start(Stage primaryStage) throws Exception {

    }
}
