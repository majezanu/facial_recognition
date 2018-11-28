package com.fiuady.vision;

import javafx.application.Application;
import javafx.stage.Stage;
import nu.pattern.OpenCV;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;

public class Main extends Application {
    public static void main(String[] args) {
        //launch(args);
        OpenCV.loadLocally();

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Mat m = new Mat(5,10, CvType.CV_8UC1, new Scalar(1));
        System.out.println(m.dump());
    }
}
