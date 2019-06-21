package com.armboldmind.mvvmtest.shared.data.networking;

import androidx.lifecycle.Observer;

import com.armboldmind.mvvmtest.model.requestModels.ResponseModel;

public abstract class CallResponseObserver<T> implements Observer<ApiResponse<ResponseModel<T>>> {

    public abstract void onSuccess(ResponseModel<T> responseModel);

    public abstract void onFailure(NetworkError networkError);

    public abstract void onError(String errorMessage);

    @Override
    public void onChanged(ApiResponse<ResponseModel<T>> apiResponse) {
        if (apiResponse.isSuccessful() && apiResponse.getBody() != null) {
            if (apiResponse.getBody().getSuccess()) {
                onSuccess(apiResponse.getBody());
            } else
                onError(apiResponse.getBody().getMessage());
        } else
            onFailure(apiResponse.getNetworkError());
    }
}