package com.armboldmind.mvvmtest.viewModel.newsActivity;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;

import com.armboldmind.mvvmtest.MyApplication;
import com.armboldmind.mvvmtest.model.newsModels.NewsDetailsModel;
import com.armboldmind.mvvmtest.model.requestModels.ResponseModel;
import com.armboldmind.mvvmtest.shared.data.api.INewsService;
import com.armboldmind.mvvmtest.shared.data.networking.ApiResponse;
import com.armboldmind.mvvmtest.shared.data.networking.CallResponseObserver;
import com.armboldmind.mvvmtest.shared.data.networking.NetworkError;
import com.armboldmind.mvvmtest.shared.di.scopes.NewsScope;
import com.armboldmind.mvvmtest.viewModel.root.BaseViewModel;

import javax.inject.Inject;

@NewsScope
public class NewsDetailsViewModel extends BaseViewModel {

    @Inject
    INewsService mNewsService;
    private final MediatorLiveData<NewsDetailsModel> mLiveData;

    public NewsDetailsViewModel(@NonNull Application application) {
        super(application);
        mLiveData = new MediatorLiveData<>();
        MyApplication.getInstance().getNewsComponent().inject(this);
    }

    public LiveData<NewsDetailsModel> getLiveData() {
        return mLiveData;
    }

    public void getNewsDetails(final Long newsId) {
        LiveData<ApiResponse<ResponseModel<NewsDetailsModel>>> sourceLiveData = mNewsService.getNewsDetails(newsId);

        mLiveData.addSource(sourceLiveData, new CallResponseObserver<NewsDetailsModel>() {
            @Override
            public void onSuccess(ResponseModel<NewsDetailsModel> responseModel) {
                if (responseModel.getSuccess()) {
                    mLiveData.setValue(responseModel.getData());
                    mLiveData.removeSource(sourceLiveData);
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