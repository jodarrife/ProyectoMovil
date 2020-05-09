package com.example.optimitour.View;

public interface LoginView {
    void showProgress();
    void hideProgress();
    void setUserError();
    void setPasswordError();
    void navigateToHome();
}
