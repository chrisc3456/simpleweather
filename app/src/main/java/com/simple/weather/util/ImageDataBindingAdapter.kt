package com.simple.weather.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter

object ImageDataBindingAdapter {

    /**
     * Custom data binding adapter to allow the android:src attribute to be set from a provided data binding model
     * Within layout xml specify e.g. android:src="@{dataobject.image, default=@drawable/default_image}"
     */
    @BindingAdapter("android:src")
    @JvmStatic
    fun setImageViewResource(imageView: ImageView, resourceId: Int) {
        imageView.setImageResource(resourceId)
    }
}