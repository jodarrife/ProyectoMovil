package com.example.optimitour.Presenter;

import com.example.optimitour.Model.LoginInteractor;
import com.example.optimitour.View.LoginView;

public class LoginPresenterImpl implements LoginPresenter, LoginInteractor.OnLoginFinishedListener {

    private LoginView loginView;
    private LoginInteractor loginInteractor;

    public LoginPresenterImpl(LoginView loginView, LoginInteractor loginInteractor) {
        this.loginView = loginView;
        this.loginInteractor = loginInteractor;
    }



    @Override
    public void validateCredentials(String username, String password) {
        if(loginView != null){
            loginView.showProgress();
        }
        loginInteractor.login(username,password, this);
    }

    @Override
    public void onDestroy() {
        /**Evitar Fuga de memoria*/
        loginView= null;
    }




    @Override
    public void onUserNameError() {
        if (loginView!=null){
            loginView.setUserError();
            loginView.hideProgress();
        }
    }

    @Override
    public void onPasswordError() {
        if (loginView!=null){
            loginView.setPasswordError();
            loginView.hideProgress();
        }
    }

    @Override
    public void onSuccess() {
        if (loginView!=null){
            loginView.navigateToHome();
            loginView.hideProgress();
        }
    }

}
