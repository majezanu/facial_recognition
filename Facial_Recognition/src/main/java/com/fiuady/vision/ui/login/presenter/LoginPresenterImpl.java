package com.fiuady.vision.ui.login.presenter;

import com.fiuady.vision.data.local.User;
import com.fiuady.vision.ui.login.view.LoginView;

public class LoginPresenterImpl implements LoginPresenter {
    private LoginView view;
    private String fieldsEmpty =  "Debes ingresar todos los campos";;
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
        }else {
            fieldsEmpty = "Debes ingresar todos los campos";
        }
    }
}
