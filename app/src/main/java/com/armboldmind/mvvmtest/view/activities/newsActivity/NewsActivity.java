package com.armboldmind.mvvmtest.view.activities.newsActivity;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.armboldmind.mvvmtest.R;
import com.armboldmind.mvvmtest.databinding.ActivityNewsBinding;
import com.armboldmind.mvvmtest.shared.utils.AppConstants;
import com.armboldmind.mvvmtest.view.activities.newsActivity.fragments.NewsDetailsFragment;
import com.armboldmind.mvvmtest.view.activities.newsActivity.fragments.NewsListFragment;
import com.armboldmind.mvvmtest.view.activities.root.BaseActivity;

public class NewsActivity extends BaseActivity {

    private ActivityNewsBinding mViewBinding;
    private FragmentManager mFragmentManager;
    private Fragment mFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewBinding = DataBindingUtil.setContentView(this, R.layout.activity_news);

        loadNewsListFragment();
    }

    private void loadNewsListFragment() {
        mFragment = new NewsListFragment();
        mFragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmentTransaction.replace(mViewBinding.newsFragmentContainer.getId(), mFragment, AppConstants.NEWS_LIST_FRAGMENT);
        fragmentTransaction.addToBackStack(AppConstants.NEWS_LIST_FRAGMENT);
        fragmentTransaction.commit();
    }

    public void loadNewsDetailsFragment(final long id) {
        mFragment = new NewsDetailsFragment();
        Bundle bundle = new Bundle();
        bundle.putLong(AppConstants.ID, id);
        mFragment.setArguments(bundle);
        mFragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmentTransaction.replace(mViewBinding.newsFragmentContainer.getId(), mFragment, AppConstants.NEWS_DETAILS_FRAGMENT);
        fragmentTransaction.addToBackStack(AppConstants.NEWS_DETAILS_FRAGMENT);
        fragmentTransaction.commit();
    }
}