package com.fiuady.vision.ui.register.view;

import com.fiuady.vision.ui.utils.Utils;
import javafx.scene.image.Image;

public interface RegisterView extends Utils {
    void setFacialImage(Image i);
    void blockCaptureButton(boolean block);
}
