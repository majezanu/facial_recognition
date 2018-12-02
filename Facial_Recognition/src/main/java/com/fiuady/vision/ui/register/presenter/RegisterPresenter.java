package com.fiuady.vision.ui.register.presenter;

import org.opencv.core.Mat;

public interface RegisterPresenter {
    void activateCamera(boolean b);
    void releaseCamera();
    void validateUser(String username,String pass,String passAgain, String number);
    void saveImage(Mat toSave);
}
