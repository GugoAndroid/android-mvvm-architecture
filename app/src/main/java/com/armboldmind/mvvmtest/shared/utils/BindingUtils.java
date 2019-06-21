package com.armboldmind.mvvmtest.shared.utils;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;
import androidx.databinding.BindingConversion;

import com.bumptech.glide.Glide;

public class BindingUtils {
    @BindingAdapter({"app:url", "app:errorImage"})
    public static void loadImage(ImageView view, String url, Drawable errorImage) {
        Glide.with(view.getContext()).load(url).error(errorImage).into(view);
    }

    @BindingConversion
    public static int convertBooleanToVisibility(boolean visible) {
        return visible ? View.VISIBLE : View.GONE;
    }
}