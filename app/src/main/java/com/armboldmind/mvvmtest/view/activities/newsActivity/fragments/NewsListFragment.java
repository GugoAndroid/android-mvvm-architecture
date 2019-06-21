package com.armboldmind.mvvmtest.view.activities.newsActivity.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.armboldmind.mvvmtest.R;
import com.armboldmind.mvvmtest.databinding.FragmentNewsListBinding;
import com.armboldmind.mvvmtest.view.activities.newsActivity.NewsActivity;
import com.armboldmind.mvvmtest.view.activities.root.BaseFragment;
import com.armboldmind.mvvmtest.view.adapters.NewsListAdapter;
import com.armboldmind.mvvmtest.viewModel.newsActivity.NewsListViewModel;

import java.util.Objects;

public class NewsListFragment extends BaseFragment {

    private FragmentNewsListBinding mBinding;
    private Activity mActivity;
    private NewsListViewModel mViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_news_list, container, false);

        if (getActivity() != null)
            mActivity = getActivity();

        initBindingView();
        initViewModel();
        initViews();

        return mBinding.getRoot();
    }

    private void initViewModel() {
        mViewModel = createViewModel(NewsListViewModel.class);

        mViewModel.getLiveData().observe(this, mainListItemModels -> {
            mBinding.newsRecyclerView.setAdapter(new NewsListAdapter(mainListItemModels, id -> ((NewsActivity) mActivity).loadNewsDetailsFragment(id)));
            mBinding.newsLoading.setVisibility(View.GONE);
        });


        mViewModel.getNewsList(0, 10);
    }

    private void initBindingView() {
    }

    private void initViews() {
        mBinding.newsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }
}