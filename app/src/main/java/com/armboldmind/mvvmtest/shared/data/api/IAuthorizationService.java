package com.armboldmind.mvvmtest.shared.data.api;

import androidx.lifecycle.LiveData;

import com.armboldmind.mvvmtest.model.authorizationModels.GuestModel;
import com.armboldmind.mvvmtest.model.authorizationModels.SignInRequestModel;
import com.armboldmind.mvvmtest.model.authorizationModels.SignInResponseModel;
import com.armboldmind.mvvmtest.model.requestModels.ResponseModel;
import com.armboldmind.mvvmtest.shared.data.networking.ApiResponse;

import retrofit2.http.Body;
import retrofit2.http.POST;

public interface IAuthorizationService {
    @POST("api/token/signIn")
    LiveData<ApiResponse<ResponseModel<SignInResponseModel>>> signIn(@Body SignInRequestModel signInRequestModel);

    @POST("api/token/signUpAsGuesta")
    LiveData<ApiResponse<ResponseModel<SignInResponseModel>>> signUpAsGuest(@Body GuestModel guestModel);
}