package com.armboldmind.mvvmtest.model.authorizationModels;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.armboldmind.mvvmtest.BR;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GuestModel extends BaseObservable {
    private String deviceId;
    private String deviceToken;
    private String modelName;
    private String languageName;
    private int osTypeId;
    private boolean isLoaded;

    @Bindable
    public boolean getIsLoaded() {
        return this.isLoaded;
    }

    public void setIsLoaded(boolean isLoaded) {
        this.isLoaded = isLoaded;
        notifyPropertyChanged(BR.isLoaded);
    }
}