package com.fiuady.vision.ui.login.view;

import com.fiuady.vision.ui.utils.Utils;
import javafx.scene.image.Image;

public interface LoginView extends Utils {
    void blockPasswordField(Boolean disabled);
    void setFacialImage(Image i);
}
