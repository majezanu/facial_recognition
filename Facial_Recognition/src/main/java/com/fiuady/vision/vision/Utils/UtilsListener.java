package com.fiuady.vision.vision.Utils;

import javafx.scene.image.Image;
import org.opencv.core.Mat;

public interface UtilsListener {
    Image matToImage(Mat frame);
    Mat toGray(Mat colored);
    Mat equalizeHist(Mat m);
}
