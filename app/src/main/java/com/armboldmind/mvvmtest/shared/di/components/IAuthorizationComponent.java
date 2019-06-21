package com.armboldmind.mvvmtest.shared.di.components;

import com.armboldmind.mvvmtest.shared.di.components.root.IAppComponent;
import com.armboldmind.mvvmtest.shared.di.modules.AuthorizationModule;
import com.armboldmind.mvvmtest.shared.di.scopes.AuthorizationScope;
import com.armboldmind.mvvmtest.viewModel.signInActivity.SignInViewModel;

import dagger.Component;

@AuthorizationScope
@Component(dependencies = {IAppComponent.class}, modules = {AuthorizationModule.class})
public interface IAuthorizationComponent {
    void inject(SignInViewModel viewModel);
}