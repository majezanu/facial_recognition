package com.fiuady.vision.vision.FaceDetecting;

import org.opencv.core.Mat;

public interface FaceDetectingListener {
    Mat detect(Mat frame);
}
