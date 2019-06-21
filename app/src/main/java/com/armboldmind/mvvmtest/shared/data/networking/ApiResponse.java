package com.armboldmind.mvvmtest.shared.data.networking;

import retrofit2.Response;

public class ApiResponse<T> {

    private NetworkError mNetworkError;
    private int mCode;
    private T mBody;

    ApiResponse(Throwable throwable) {
        mNetworkError = new NetworkError(throwable);
    }

    ApiResponse(Response<T> response) {
        mCode = response.code();
        mBody = response.body();
    }

    boolean isSuccessful() {
        return mCode >= 200 && mCode < 300;
    }

    T getBody() {
        return mBody;
    }

    NetworkError getNetworkError() {
        return mNetworkError;
    }
}