package com.armboldmind.mvvmtest.shared.di.modules;


import com.armboldmind.mvvmtest.shared.data.api.INewsService;
import com.armboldmind.mvvmtest.shared.di.scopes.NewsScope;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class NewsModule {

    @Provides
    @NewsScope
    INewsService providesIMainService(Retrofit retrofit) {
        return retrofit.create(INewsService.class);
    }
}