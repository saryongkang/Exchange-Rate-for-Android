package com.saryong.example.presentation.common

import android.databinding.BindingAdapter
import android.widget.ImageView
import com.saryong.example.util.extention.loadFromAsset
import com.saryong.example.util.extention.loadFromUrl

@BindingAdapter("imageUrl")
fun loadImage(imageView: ImageView, url: String) {
  imageView.loadFromUrl(url)
}

@BindingAdapter("imageAsset")
fun loadAssetImage(imageView: ImageView, filename: String) {
  imageView.loadFromAsset(filename)
}

