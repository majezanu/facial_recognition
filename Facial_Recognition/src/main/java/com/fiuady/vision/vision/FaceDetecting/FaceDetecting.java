package com.fiuady.vision.vision.FaceDetecting;

import com.fiuady.vision.ui.login.presenter.LoginPresenter;
import com.fiuady.vision.ui.register.presenter.RegisterPresenter;
import com.fiuady.vision.vision.SaveFaces.SaveFaces;
import com.fiuady.vision.vision.SaveFaces.SaveFacesListener;
import com.fiuady.vision.vision.Utils.Utils;
import com.fiuady.vision.vision.Utils.UtilsListener;
import org.opencv.core.*;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;

public class FaceDetecting  implements FaceDetectingListener{
    private CascadeClassifier classifier;

    private RegisterPresenter regPresenter;
    private LoginPresenter logPresenter;
    private UtilsListener utilsListener;
    public FaceDetecting() {
        classifier = new CascadeClassifier("Resources/haarcascade_frontalface_alt.xml");
        if (classifier.empty()) {
            System.out.println("Error de lectura.");
            return;
        } else {
            System.out.println("Detector de rostros leido.");
        }
        utilsListener = new Utils();

    }


    @Override
    public Mat detect(Mat frame) {
        Mat mRgba = new Mat();
        MatOfRect rostros = new MatOfRect();
        frame.copyTo(mRgba);
        Mat mGrey = new Mat();
        mGrey = utilsListener.equalizeHist(utilsListener.toGray(mRgba));
        classifier.detectMultiScale(mGrey, rostros);

        for (Rect rect : rostros.toArray()) {
            //Se dibuja un rectángulo donde se ha encontrado el rostro
            Imgproc.rectangle(mRgba, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height), new Scalar(255, 0, 0));
            if(regPresenter != null){
                Mat roi = new Mat(mGrey,rect);
                regPresenter.saveImage(roi);
            }
            if(logPresenter != null){

            }
        }
        return mRgba;
    }

    public RegisterPresenter getRegPresenter() {
        return regPresenter;
    }

    public void setRegPresenter(RegisterPresenter regPresenter) {
        this.logPresenter = null;
        this.regPresenter = regPresenter;
    }

    public LoginPresenter getLogPresenter() {
        return logPresenter;
    }

    public void setLogPresenter(LoginPresenter logPresenter) {
        this.regPresenter = null;
        this.logPresenter = logPresenter;
    }
}
