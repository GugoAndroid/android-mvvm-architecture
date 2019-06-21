package com.armboldmind.mvvmtest.viewModel.signInActivity;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;

import com.armboldmind.mvvmtest.MyApplication;
import com.armboldmind.mvvmtest.model.authorizationModels.GuestModel;
import com.armboldmind.mvvmtest.model.authorizationModels.SignInRequestModel;
import com.armboldmind.mvvmtest.model.authorizationModels.SignInResponseModel;
import com.armboldmind.mvvmtest.model.requestModels.ResponseModel;
import com.armboldmind.mvvmtest.shared.data.api.IAuthorizationService;
import com.armboldmind.mvvmtest.shared.data.networking.ApiResponse;
import com.armboldmind.mvvmtest.shared.data.networking.CallResponseObserver;
import com.armboldmind.mvvmtest.shared.data.networking.NetworkError;
import com.armboldmind.mvvmtest.shared.di.scopes.NewsScope;
import com.armboldmind.mvvmtest.shared.helpers.SharedPreferencesHelper;
import com.armboldmind.mvvmtest.viewModel.root.BaseViewModel;

import javax.inject.Inject;

@NewsScope
public class SignInViewModel extends BaseViewModel {

    @Inject
    IAuthorizationService mAuthorizationService;
    @Inject
    SharedPreferencesHelper mShared;
    private final MediatorLiveData<SignInResponseModel> mLiveDataSignIn;
    private final MediatorLiveData<SignInResponseModel> mLiveDataSignUp;

    public SignInViewModel(@NonNull Application application) {
        super(application);
        mLiveDataSignIn = new MediatorLiveData<>();
        mLiveDataSignUp = new MediatorLiveData<>();
        MyApplication.getInstance().getAuthorizationComponent().inject(this);
    }

    public LiveData<SignInResponseModel> getLiveDataSignIn() {
        return mLiveDataSignIn;
    }

    public LiveData<SignInResponseModel> getLiveDataSignUp() {
        return mLiveDataSignUp;
    }

    public void signIn(final SignInRequestModel signInRequestModel) {
        LiveData<ApiResponse<ResponseModel<SignInResponseModel>>> sourceLiveData = mAuthorizationService.signIn(signInRequestModel);

        mLiveDataSignIn.addSource(sourceLiveData, new CallResponseObserver<SignInResponseModel>() {
            @Override
            public void onSuccess(ResponseModel<SignInResponseModel> responseModel) {
                if (responseModel.getSuccess()) {
                    mShared.setStringSharedPreferences("Token", responseModel.getData().getToken());
                    mLiveDataSignIn.setValue(responseModel.getData());
                    mLiveDataSignIn.removeSource(sourceLiveData);
                }
            }

            @Override
            public void onFailure(NetworkError networkError) {
                errorView(networkError);
            }

            @Override
            public void onError(String errorMessage) {
                errorToast(errorMessage);
            }
        });
    }

    public void signUpAsGuest(final GuestModel guestModel) {
        LiveData<ApiResponse<ResponseModel<SignInResponseModel>>> sourceLiveData = mAuthorizationService.signUpAsGuest(guestModel);

        mLiveDataSignUp.addSource(sourceLiveData, new CallResponseObserver<SignInResponseModel>() {
            @Override
            public void onSuccess(ResponseModel<SignInResponseModel> responseModel) {
                if (responseModel.getSuccess()) {
                    mShared.setStringSharedPreferences("Token", responseModel.getData().getToken());
                    mLiveDataSignUp.setValue(responseModel.getData());
                    mLiveDataSignUp.removeSource(sourceLiveData);
                }
            }

            @Override
            public void onFailure(NetworkError networkError) {
                errorView(networkError);
            }

            @Override
            public void onError(String errorMessage) {
                errorToast(errorMessage);
            }
        });
    }
}