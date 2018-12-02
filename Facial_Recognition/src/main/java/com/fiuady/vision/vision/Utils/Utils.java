package com.fiuady.vision.vision.Utils;

import javafx.scene.image.Image;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import java.io.ByteArrayInputStream;

public class Utils implements UtilsListener {
    @Override
    public Image matToImage(Mat frame) {
        MatOfByte buffer = new MatOfByte();
        Imgcodecs.imencode(".png", frame, buffer);
        return new Image(new ByteArrayInputStream(buffer.toArray()));
    }

    @Override
    public Mat toGray(Mat colored) {
        Mat mGrey = new Mat();
        Imgproc.cvtColor(colored, mGrey, Imgproc.COLOR_BGR2GRAY);
        return  mGrey;
    }

    @Override
    public Mat equalizeHist(Mat m) {
        Mat equalized = new Mat();
        Imgproc.equalizeHist(m,equalized);
        return equalized;
    }
}
