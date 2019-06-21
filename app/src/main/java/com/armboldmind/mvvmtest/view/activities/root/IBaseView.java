package com.armboldmind.mvvmtest.view.activities.root;

public interface IBaseView {
    void onToast(String message);

    void onSnackBar(String message);

    void showServerError();

    void showNetworkError();
}