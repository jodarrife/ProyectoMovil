package com.example.optimitour.Model;

public interface LoginInteractor {

    interface OnLoginFinishedListener{
        void onUserNameError();
        void onPasswordError();
        void onSuccess();
    }
    void login(String username, String password, OnLoginFinishedListener listener);
}
