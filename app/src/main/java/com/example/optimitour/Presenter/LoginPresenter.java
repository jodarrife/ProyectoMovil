package com.example.optimitour.Presenter;

public interface LoginPresenter {
    void validateCredentials(String username, String password);
    void onDestroy();
}
