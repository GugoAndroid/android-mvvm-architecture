package com.armboldmind.mvvmtest;

import androidx.multidex.MultiDexApplication;

import com.armboldmind.mvvmtest.shared.di.components.DaggerIAuthorizationComponent;
import com.armboldmind.mvvmtest.shared.di.components.DaggerINewsComponent;
import com.armboldmind.mvvmtest.shared.di.components.IAuthorizationComponent;
import com.armboldmind.mvvmtest.shared.di.components.INewsComponent;
import com.armboldmind.mvvmtest.shared.di.components.root.DaggerIAppComponent;
import com.armboldmind.mvvmtest.shared.di.components.root.IAppComponent;
import com.armboldmind.mvvmtest.shared.di.modules.AuthorizationModule;
import com.armboldmind.mvvmtest.shared.di.modules.NewsModule;
import com.armboldmind.mvvmtest.shared.di.modules.root.AppModule;
import com.armboldmind.mvvmtest.shared.di.modules.root.NetModule;

public class MyApplication extends MultiDexApplication {

    private static MyApplication mInstance;
    private IAppComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;

        mAppComponent = DaggerIAppComponent.builder()
                .appModule(new AppModule(this))
                .netModule(new NetModule())
                .build();
    }

    public static synchronized MyApplication getInstance() {
        return mInstance;
    }

    public IAuthorizationComponent getAuthorizationComponent() {
        return DaggerIAuthorizationComponent.builder()
                .iAppComponent(mAppComponent)
                .authorizationModule(new AuthorizationModule())
                .build();
    }

    public INewsComponent getNewsComponent() {
        return DaggerINewsComponent.builder()
                .iAppComponent(mAppComponent)
                .newsModule(new NewsModule())
                .build();
    }
}