package com.armboldmind.mvvmtest.shared.di.components;

import com.armboldmind.mvvmtest.shared.di.components.root.IAppComponent;
import com.armboldmind.mvvmtest.shared.di.modules.NewsModule;
import com.armboldmind.mvvmtest.shared.di.scopes.NewsScope;
import com.armboldmind.mvvmtest.viewModel.newsActivity.NewsDetailsViewModel;
import com.armboldmind.mvvmtest.viewModel.newsActivity.NewsListViewModel;

import dagger.Component;

@NewsScope
@Component(dependencies = {IAppComponent.class}, modules = {NewsModule.class})
public interface INewsComponent {
    void inject(NewsListViewModel viewModel);

    void inject(NewsDetailsViewModel viewModel);
}