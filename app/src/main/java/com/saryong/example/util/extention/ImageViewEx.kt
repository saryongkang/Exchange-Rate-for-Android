package com.saryong.example.util.extention

import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.bumptech.glide.Glide
import java.io.IOException

const val DEFAULT_IMAGE_ASSET_FOLDER = "image/"

fun ImageView.loadFromUrl(url: String?) {
  Glide.with(context).load(url).into(this)
}

@Throws(IOException::class)
fun ImageView.loadFromAsset(name: String) {
  val ims = context.assets.open(DEFAULT_IMAGE_ASSET_FOLDER + name)
  val drawable = Drawable.createFromStream(ims, null)
  setImageDrawable(drawable)
}