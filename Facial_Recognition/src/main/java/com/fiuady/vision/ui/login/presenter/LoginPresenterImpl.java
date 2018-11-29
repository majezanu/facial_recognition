package com.fiuady.vision.ui.login.presenter;

import com.fiuady.vision.data.local.User;
import com.fiuady.vision.ui.login.view.LoginView;
import com.fiuady.vision.vision.FaceDetecting.FaceDetecting;
import com.fiuady.vision.vision.FaceDetecting.FaceDetectingListener;
import com.fiuady.vision.vision.Utils.Utils;
import javafx.scene.image.Image;
import org.opencv.core.Mat;
import org.opencv.videoio.VideoCapture;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class LoginPresenterImpl implements LoginPresenter {
    private LoginView view;
    private String fieldsEmpty =  "Debes ingresar todos los campos";
    private VideoCapture capture;
    private ScheduledExecutorService timer;
    private Mat frame = new Mat();
    private Mat frameWithFaces;
    private Image imageFrame;
    private FaceDetectingListener faceDetectingListener;
    private Utils visionUtils;
    public LoginPresenterImpl(LoginView view) {
        this.view = view;
        faceDetectingListener = new FaceDetecting();
        visionUtils = new Utils();
    }
    @Override
    public void validateLogin(final User u) {
        User input = u;
        boolean error = false;
        if(input.getUserName().isEmpty() && input.getUserName().isEmpty()){
            error = true;
        }
        if(error){

            view.showSnackBar(fieldsEmpty);
        }
    }

    @Override
    public void activateCamera(boolean b) {
        view.blockPasswordField(b);
        if(b){
            fieldsEmpty = "Debes ingresar el nombre de usuario";
            video();

        }else {
            fieldsEmpty = "Debes ingresar todos los campos";
            closeCamera();
        }
    }
    private void video(){
        capture = new VideoCapture();
        capture.open(0);
        Runnable frameGrabber = new Runnable() {

            @Override
            public void run() {
                // effectively grab and process a single frame
                grabFrame();
                // convert and show the frame
                //TODO trigger listener up here
            }
        };

        this.timer = Executors.newSingleThreadScheduledExecutor();
        this.timer.scheduleAtFixedRate(frameGrabber, 0, 33, TimeUnit.MILLISECONDS);
    }
    private void grabFrame() {
        if (capture.isOpened()) {
            try {
                // read the current frame
                capture.read(frame);

                // if the frame is not empty, process it
                if (!frame.empty()){

                    view.setFacialImage(visionUtils.matToImage(faceDetectingListener.detect(frame)));
                }

            } catch (Exception e) {
                // log the error
                e.printStackTrace();
            }
        }

    }
    private void closeCamera(){
        if(capture != null){
            if(capture.isOpened()){
                timer.shutdownNow();
                capture.release();
            }
        }

    }

    @Override
    public void releaseCamera() {
        closeCamera();
    }
}
