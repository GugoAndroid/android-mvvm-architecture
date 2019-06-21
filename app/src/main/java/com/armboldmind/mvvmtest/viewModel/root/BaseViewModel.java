package com.armboldmind.mvvmtest.viewModel.root;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.armboldmind.mvvmtest.shared.data.networking.NetworkError;
import com.bumptech.glide.load.HttpException;

public class BaseViewModel extends AndroidViewModel {

    public BaseViewModel(@NonNull Application application) {
        super(application);
    }

    private MutableLiveData<Boolean> mServerErrorLiveData = new MutableLiveData<>();
    private MutableLiveData<Boolean> mNetworkErrorLiveData = new MutableLiveData<>();
    private MutableLiveData<String> mToastMessageLiveData = new MutableLiveData<>();

    protected void errorToast(String errorMessage) {
        mToastMessageLiveData.setValue(errorMessage);
    }

    protected void errorSnackBar(String errorMessage) {
        mToastMessageLiveData.setValue(errorMessage);
    }

    protected void errorView(NetworkError networkError) {
        if (networkError.isServerError())
            mServerErrorLiveData.setValue(true);
        else if (networkError.isNetworkError())
            mNetworkErrorLiveData.setValue(true);
        else
            mServerErrorLiveData.setValue(true);
    }

    public MutableLiveData<Boolean> getServerErrorLiveData() {
        return mServerErrorLiveData;
    }

    public MutableLiveData<Boolean> getNetworkErrorLiveData() {
        return mNetworkErrorLiveData;
    }

    public MutableLiveData<String> getToastMessageLiveData() {
        return mToastMessageLiveData;
    }
}