package com.armboldmind.mvvmtest.view.activities.signInActivity.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;

import com.armboldmind.mvvmtest.R;
import com.armboldmind.mvvmtest.databinding.FragmentSignInBinding;
import com.armboldmind.mvvmtest.model.authorizationModels.GuestModel;
import com.armboldmind.mvvmtest.model.authorizationModels.SignInRequestModel;
import com.armboldmind.mvvmtest.shared.utils.CommonUtils;
import com.armboldmind.mvvmtest.shared.utils.ViewUtils;
import com.armboldmind.mvvmtest.view.activities.newsActivity.NewsActivity;
import com.armboldmind.mvvmtest.view.activities.root.BaseFragment;
import com.armboldmind.mvvmtest.viewModel.signInActivity.SignInViewModel;

public class SignInFragment extends BaseFragment {

    private FragmentSignInBinding mBinding;
    private Activity mActivity;
    private SignInViewModel mViewModel;
    private SignInRequestModel mSignInRequestModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_sign_in, container, false);

        if (getActivity() != null)
            mActivity = getActivity();

        mSignInRequestModel = new SignInRequestModel();
        initBindingView();
        initViewModel();

        return mBinding.getRoot();
    }

    private void initBindingView() {
        mBinding.setClickListener(new SignInFragmentClickHandler());
        mBinding.setSignIn(mSignInRequestModel);
    }

    private void initViewModel() {
        mViewModel = createViewModel(SignInViewModel.class);
        mViewModel.getLiveDataSignIn().observe(this, signInResponseModel -> loadNewsListPage());
        mViewModel.getLiveDataSignUp().observe(this, signInResponseModel -> loadNewsListPage());
    }

    private void loadNewsListPage() {
        startActivity(new Intent(getActivity(), NewsActivity.class));
        mActivity.finish();
    }

    public class SignInFragmentClickHandler {
        public void onClickSignInButton() {
            mViewModel.signIn(mSignInRequestModel);
        }

        public void onClickGuestButton() {
            GuestModel mGuestModel = new GuestModel();
            mGuestModel.setOsTypeId(1);
            mGuestModel.setDeviceToken("cW05tWv5vlI:APA91bFFymMKfFkyyxAA25YZoSF7d5zxcAsyqFA1dG-3gfyyXkPiB3khMujF6yGJM1dKBleCoPYx4HwAMYTsKCOVNrnfqWAcPZfdpqgg16TOY9-AcqmCHmaLc9pkDnHhByO7h4WP-gLN");
            mGuestModel.setModelName(CommonUtils.getMobileModel());
            mGuestModel.setLanguageName("en");
            mGuestModel.setDeviceId(CommonUtils.getDeviceId(mActivity));
            mViewModel.signUpAsGuest(mGuestModel);
        }
    }
}