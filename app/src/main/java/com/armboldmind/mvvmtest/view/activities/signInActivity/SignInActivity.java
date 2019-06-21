package com.armboldmind.mvvmtest.view.activities.signInActivity;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.armboldmind.mvvmtest.R;
import com.armboldmind.mvvmtest.databinding.ActivitySignInBinding;
import com.armboldmind.mvvmtest.shared.utils.AppConstants;
import com.armboldmind.mvvmtest.view.activities.root.BaseActivity;
import com.armboldmind.mvvmtest.view.activities.signInActivity.fragments.SignInFragment;

public class SignInActivity extends BaseActivity {

    private ActivitySignInBinding mViewBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewBinding = DataBindingUtil.setContentView(this, R.layout.activity_sign_in);

        loadSignInFragment();
    }

    private void loadSignInFragment() {
        Fragment mFragment = new SignInFragment();
        FragmentManager mFragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmentTransaction.replace(mViewBinding.singInFragmentContainer.getId(), mFragment, AppConstants.SIGN_IN_FRAGMENT);
        fragmentTransaction.addToBackStack(AppConstants.SIGN_IN_FRAGMENT);
        fragmentTransaction.commit();
    }
}