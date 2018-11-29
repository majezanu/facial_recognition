package com.fiuady.vision.ui.register.presenter;

import com.fiuady.vision.data.local.User;
import com.fiuady.vision.ui.register.view.RegisterView;
import com.fiuady.vision.vision.FaceDetecting.FaceDetecting;
import com.fiuady.vision.vision.FaceDetecting.FaceDetectingListener;
import com.fiuady.vision.vision.Utils.Utils;
import javafx.scene.image.Image;
import org.opencv.core.Mat;
import org.opencv.videoio.VideoCapture;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class RegisterPresenterImpl implements RegisterPresenter {
    private VideoCapture capture;
    private ScheduledExecutorService timer;
    private Mat frame = new Mat();
    private Mat frameWithFaces;
    private Image imageFrame;
    private FaceDetectingListener faceDetectingListener;
    private RegisterView view;
    private Utils visionUtils;
    public RegisterPresenterImpl(RegisterView view) {
        this.view = view;
        faceDetectingListener = new FaceDetecting();
        visionUtils = new Utils();
    }

    @Override
    public void activateCamera(boolean b) {

        if(b){

            video();

        }else {

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

    @Override
    public void validateUser(String username, String pass, String passAgain, String number) {
        boolean error = false;
        if((username+pass+passAgain+number).isEmpty()){
            error = true;
            view.showSnackBar("Debes llenar todos los campos");
        }else{
            if(username.isEmpty()){
                error = true;
                view.showSnackBar("Debes ingresar tu nombre de usuario");
            }
            if(pass.isEmpty()){
                error = true;
                view.showSnackBar("Debes ingresar una contraseña");
                if(passAgain.isEmpty()){
                    error = true;
                    view.showSnackBar("Debes validar tu contraseña");
                }
            }

            if(!pass.isEmpty() && !passAgain.isEmpty()){
                if(!pass.equals(passAgain)){
                    error = true;
                    view.showSnackBar("Las contraseñas no son iguales");
                }
            }

        }
        if(!error){
            User user = new User(username,pass,number);
            view.showSnackBar("Usuario '"+user.getUserName()+"' creado");
        }

    }
}
