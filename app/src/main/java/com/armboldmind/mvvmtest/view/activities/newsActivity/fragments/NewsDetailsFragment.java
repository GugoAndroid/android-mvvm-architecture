package com.armboldmind.mvvmtest.view.activities.newsActivity.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;

import com.armboldmind.mvvmtest.R;
import com.armboldmind.mvvmtest.databinding.FragmentNewsDetailsBinding;
import com.armboldmind.mvvmtest.shared.utils.AppConstants;
import com.armboldmind.mvvmtest.view.activities.root.BaseFragment;
import com.armboldmind.mvvmtest.viewModel.newsActivity.NewsDetailsViewModel;

public class NewsDetailsFragment extends BaseFragment {

    private FragmentNewsDetailsBinding mBinding;
    private Activity mActivity;
    private NewsDetailsViewModel mViewModel;
    private Long mNewsId;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_news_details, container, false);

        if (getActivity() != null)
            mActivity = getActivity();

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            mNewsId = bundle.getLong(AppConstants.ID);
        }

        initViewModel();
        initBindingView();

        return mBinding.getRoot();
    }

    private void initViewModel() {
        mViewModel = createViewModel(NewsDetailsViewModel.class, this);

        mViewModel.getLiveData().observe(this, newsDetailsModel -> {
            mBinding.setNews(newsDetailsModel);
            mBinding.newsLoading.setVisibility(View.GONE);
        });

        mViewModel.getNewsDetails(mNewsId);
    }

    private void initBindingView() {
    }
}