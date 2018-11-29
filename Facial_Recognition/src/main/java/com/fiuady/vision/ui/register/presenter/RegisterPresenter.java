package com.fiuady.vision.ui.register.presenter;

public interface RegisterPresenter {
    void activateCamera(boolean b);
    void releaseCamera();
    void validateUser(String username,String pass,String passAgain, String number);
}
