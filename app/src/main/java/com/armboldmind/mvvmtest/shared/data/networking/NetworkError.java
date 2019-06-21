package com.armboldmind.mvvmtest.shared.data.networking;

import android.content.Context;

import com.armboldmind.mvvmtest.MyApplication;
import com.armboldmind.mvvmtest.R;
import com.armboldmind.mvvmtest.shared.utils.NetworkStatusUtils;
import com.armboldmind.mvvmtest.view.activities.root.BaseActivity;

import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.util.Objects;

import retrofit2.HttpException;

import static java.net.HttpURLConnection.HTTP_FORBIDDEN;
import static java.net.HttpURLConnection.HTTP_UNAUTHORIZED;

public class NetworkError extends Throwable {

    private final Context mContext;
    private final Throwable mError;

    NetworkError(Throwable throwable) {
        super(throwable);
        mContext = MyApplication.getInstance();
        mError = throwable;
    }

    public boolean isServerError() {
        return isAuthFailure() || mError instanceof SocketTimeoutException;
    }

    public boolean isNetworkError() {
        return isAuthFailure() || mError instanceof IOException;
    }

    private Boolean isAuthFailure() {
        if (mError instanceof HttpException) {
            if (((HttpException) mError).code() == HTTP_UNAUTHORIZED || ((HttpException) mError).code() == HTTP_FORBIDDEN) {
                ((BaseActivity) mContext).openActivityOnUnauthorized();
                return true;
            } else
                return false;
        } else
            return false;
    }

    public Throwable getError() {
        return mError;
    }

    public String getAppErrorMessage() {
        if (!isAuthFailure()) {
            if (mError instanceof SocketTimeoutException)
                return mContext.getResources().getString(R.string.default_error_message);
            if (mError instanceof ConnectException && NetworkStatusUtils.isNetworkAvailable(mContext))
                return mContext.getResources().getString(R.string.default_error_message);
            if (mError instanceof ConnectException && !NetworkStatusUtils.isNetworkAvailable(mContext))
                return mContext.getResources().getString(R.string.network_error_message);
            if (mError instanceof IOException)
                return mContext.getResources().getString(R.string.network_error_message);
            if (!(mError instanceof HttpException))
                return mContext.getResources().getString(R.string.default_error_message);
        }

        return mContext.getResources().getString(R.string.default_error_message);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NetworkError that = (NetworkError) o;

        return Objects.equals(mError, that.mError);
    }

    @Override
    public int hashCode() {
        return mError != null ? mError.hashCode() : 0;
    }
}