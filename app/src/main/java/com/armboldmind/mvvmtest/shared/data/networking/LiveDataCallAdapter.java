package com.armboldmind.mvvmtest.shared.data.networking;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import java.lang.reflect.Type;
import java.util.concurrent.atomic.AtomicBoolean;

import retrofit2.Call;
import retrofit2.CallAdapter;
import retrofit2.Callback;
import retrofit2.Response;

public class LiveDataCallAdapter<R> implements CallAdapter<R, LiveData<ApiResponse<R>>> {

    private final Type mResponseType;

    LiveDataCallAdapter(Type responseType) {
        mResponseType = responseType;
    }

    @NonNull
    @Override
    public Type responseType() {
        return mResponseType;
    }

    @NonNull
    @Override
    public LiveData<ApiResponse<R>> adapt(@NonNull Call<R> call) {
        return new LiveData<ApiResponse<R>>() {
            AtomicBoolean started = new AtomicBoolean(false);

            @Override
            protected void onActive() {
                super.onActive();
                if (started.compareAndSet(false, true)) {
                    call.enqueue(new Callback<R>() {
                        @Override
                        public void onResponse(@NonNull Call<R> call1, @NonNull Response<R> response) {
                            if (response.isSuccessful()) {
                                postValue(new ApiResponse<>(response));
                            } else
                                postValue(new ApiResponse<>(new Throwable()));
                        }

                        @Override
                        public void onFailure(@NonNull Call<R> call1, @NonNull Throwable t) {
                            postValue(new ApiResponse<>(t));
                        }
                    });
                }
            }
        };
    }
}