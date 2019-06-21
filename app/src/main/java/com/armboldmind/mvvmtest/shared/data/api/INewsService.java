package com.armboldmind.mvvmtest.shared.data.api;

import androidx.lifecycle.LiveData;

import com.armboldmind.mvvmtest.model.newsModels.NewsDetailsModel;
import com.armboldmind.mvvmtest.model.newsModels.NewsListModel;
import com.armboldmind.mvvmtest.model.requestModels.PaginationModel;
import com.armboldmind.mvvmtest.model.requestModels.ResponseModel;
import com.armboldmind.mvvmtest.shared.data.networking.ApiResponse;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;

public interface INewsService {
    @GET("api/news/getList?")
    LiveData<ApiResponse<ResponseModel<PaginationModel<List<NewsListModel>>>>>
    getNewsList(@Query("page") int page, @Query("size") int size);

    @GET("api/news/getById?")
    LiveData<ApiResponse<ResponseModel<NewsDetailsModel>>> getNewsDetails(@Query("id") long id);
}