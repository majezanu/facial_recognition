package com.fiuady.vision.ui.login.presenter;

import com.fiuady.vision.data.local.User;

public interface LoginPresenter {
    void validateLogin(User u);
    void activateCamera(boolean b);
    void releaseCamera();
}
