package com.armboldmind.mvvmtest.view.activities.root;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProviders;

import com.armboldmind.mvvmtest.R;
import com.armboldmind.mvvmtest.view.activities.signInActivity.SignInActivity;
import com.armboldmind.mvvmtest.viewModel.root.BaseViewModel;
import com.google.android.material.snackbar.Snackbar;

@SuppressLint("Registered")
public abstract class BaseActivity extends AppCompatActivity implements IBaseView {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    protected <T extends BaseViewModel> T createViewModel(@NonNull Class<T> modelClass) {
        T viewModel = ViewModelProviders.of(this).get(modelClass);
        viewModel.getServerErrorLiveData().observe(this, aBoolean -> showServerError());
        viewModel.getNetworkErrorLiveData().observe(this, aBoolean -> showNetworkError());
        viewModel.getToastMessageLiveData().observe(this, this::onToast);
        return viewModel;
    }

    @Override
    public void onToast(String message) {
        if (message != null) {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, getResources().getString(R.string.default_error_message),
                    Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onSnackBar(String message) {
        Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), message,
                Snackbar.LENGTH_LONG);
        View sbView = snackbar.getView();
        sbView.setBackgroundColor(ContextCompat.getColor(this, android.R.color.white));
        TextView textView = sbView.findViewById(R.id.snackbar_text);
        textView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        textView.setTextColor(getResources().getColor(android.R.color.black));
        snackbar.show();
    }

    @Override
    public void showServerError() {
        onSnackBar(getResources().getString(R.string.default_error_message));
    }

    @Override
    public void showNetworkError() {
        onSnackBar(getResources().getString(R.string.network_error_message));
    }

    public void openActivityOnUnauthorized() {
        startActivity(new Intent(this, SignInActivity.class));
        finish();
    }

    @TargetApi(Build.VERSION_CODES.M)
    public void requestPermissionsSafely(String[] permissions, Integer requestCode) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(permissions, requestCode);
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    public boolean hasPermission(String permission) {
        return Build.VERSION.SDK_INT < Build.VERSION_CODES.M
                || checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED;
    }
}