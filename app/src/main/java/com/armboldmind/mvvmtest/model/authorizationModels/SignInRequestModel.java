package com.armboldmind.mvvmtest.model.authorizationModels;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.armboldmind.mvvmtest.BR;

public class SignInRequestModel extends BaseObservable {
    private String username;
    private String password;
    private boolean isLoadedSignInButton;
    private boolean isLoadedGuestButton;

    @Bindable
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
        notifyPropertyChanged(BR.username);
    }

    @Bindable
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
        notifyPropertyChanged(BR.password);
    }

    @Bindable
    public boolean getIsLoadedSignInButton() {
        return this.isLoadedSignInButton;
    }

    public void setIsLoadedSignInButton(boolean isLoaded) {
        this.isLoadedSignInButton = isLoaded;
        notifyPropertyChanged(BR.isLoadedSignInButton);
    }

    @Bindable
    public boolean getIsLoadedGuestButton() {
        return this.isLoadedGuestButton;
    }

    public void setIsLoadedGuestButton(boolean isLoaded) {
        this.isLoadedGuestButton = isLoaded;
        notifyPropertyChanged(BR.isLoadedGuestButton);
    }
}