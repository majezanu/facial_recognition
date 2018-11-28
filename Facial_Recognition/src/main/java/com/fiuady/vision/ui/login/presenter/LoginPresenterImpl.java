package com.fiuady.vision.ui.login.presenter;

import com.fiuady.vision.data.local.User;
import com.fiuady.vision.ui.login.view.LoginView;
import javafx.scene.image.Image;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.videoio.VideoCapture;

import java.io.ByteArrayInputStream;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class LoginPresenterImpl implements LoginPresenter {
    private LoginView view;
    private String fieldsEmpty =  "Debes ingresar todos los campos";
    private VideoCapture capture;
    private ScheduledExecutorService timer;
    private Mat frame = new Mat();
    private Image imageFrame;
    public LoginPresenterImpl(LoginView view) {
        this.view = view;
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
        if(!capture.isOpened()){
            capture.open(0);
        }
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
                    MatOfByte buffer = new MatOfByte();
                    Imgcodecs.imencode(".png", frame, buffer);
                    imageFrame = new Image(new ByteArrayInputStream(buffer.toArray()));

                }

            } catch (Exception e) {
                // log the error
                e.printStackTrace();
            }
        }

    }
    private void closeCamera(){
        if(capture.isOpened()){
            capture.release();
        }
    }
}
