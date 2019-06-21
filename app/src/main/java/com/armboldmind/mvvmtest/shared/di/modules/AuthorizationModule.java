package com.armboldmind.mvvmtest.shared.di.modules;


import com.armboldmind.mvvmtest.shared.data.api.IAuthorizationService;
import com.armboldmind.mvvmtest.shared.di.scopes.AuthorizationScope;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class AuthorizationModule {

    @Provides
    @AuthorizationScope
    IAuthorizationService providesIAuthorizationService(Retrofit retrofit) {
        return retrofit.create(IAuthorizationService.class);
    }
}