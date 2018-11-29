package com.fiuady.vision.vision.FaceDetecting;

import org.opencv.core.*;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;

public class FaceDetecting  implements FaceDetectingListener{
    private CascadeClassifier classifier;

    public FaceDetecting() {
        classifier = new CascadeClassifier("Resources/haarcascade_frontalface_alt.xml");
        if (classifier.empty()) {
            System.out.println("Error de lectura.");
            return;
        } else {
            System.out.println("Detector de rostros leido.");
        }
    }


    @Override
    public Mat detect(Mat frame) {
        Mat mRgba = new Mat();
        Mat mGrey = new Mat();
        MatOfRect rostros = new MatOfRect();
        frame.copyTo(mRgba);
        frame.copyTo(mGrey);
        Imgproc.cvtColor(mRgba, mGrey, Imgproc.COLOR_BGR2GRAY);
        Imgproc.equalizeHist(mGrey, mGrey);
        classifier.detectMultiScale(mGrey, rostros);
        System.out.println(String.format("Detectando %s rostros", rostros.toArray().length));
        for (Rect rect : rostros.toArray()) {
            //Se dibuja un rectángulo donde se ha encontrado el rostro
            Imgproc.rectangle(mRgba, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height), new Scalar(255, 0, 0));

        }
        return mRgba;
    }
}
