package com.armboldmind.mvvmtest.viewModel.newsActivity;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;

import com.armboldmind.mvvmtest.MyApplication;
import com.armboldmind.mvvmtest.model.newsModels.NewsListModel;
import com.armboldmind.mvvmtest.model.requestModels.PaginationModel;
import com.armboldmind.mvvmtest.model.requestModels.ResponseModel;
import com.armboldmind.mvvmtest.shared.data.api.INewsService;
import com.armboldmind.mvvmtest.shared.data.networking.ApiResponse;
import com.armboldmind.mvvmtest.shared.data.networking.CallResponseObserver;
import com.armboldmind.mvvmtest.shared.data.networking.NetworkError;
import com.armboldmind.mvvmtest.shared.di.scopes.NewsScope;
import com.armboldmind.mvvmtest.viewModel.root.BaseViewModel;

import java.util.List;

import javax.inject.Inject;

@NewsScope
public class NewsListViewModel extends BaseViewModel {

    @Inject
    INewsService mNewsService;
    private final MediatorLiveData<List<NewsListModel>> mLiveData;

    public NewsListViewModel(@NonNull Application application) {
        super(application);
        mLiveData = new MediatorLiveData<>();
        MyApplication.getInstance().getNewsComponent().inject(this);
    }

    public LiveData<List<NewsListModel>> getLiveData() {
        return mLiveData;
    }

    public void getNewsList(final int page, final int size) {
        LiveData<ApiResponse<ResponseModel<PaginationModel<List<NewsListModel>>>>> sourceLiveData = mNewsService.getNewsList(page, size);

        mLiveData.addSource(sourceLiveData, new CallResponseObserver<PaginationModel<List<NewsListModel>>>() {
            @Override
            public void onSuccess(ResponseModel<PaginationModel<List<NewsListModel>>> responseModel) {
                if (responseModel.getSuccess()) {
                    mLiveData.setValue(responseModel.getData().getContent());
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