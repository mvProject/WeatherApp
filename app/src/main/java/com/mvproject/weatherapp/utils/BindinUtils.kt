package com.mvproject.weatherapp.utils

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso

// todo: good adapter! I like it
@BindingAdapter(value = ["app:url", "app:placeholder"], requireAll = false)
fun loadImage(view: ImageView,url: String?,placeHolder: Drawable
) {
    Picasso.get().load(url).placeholder(placeHolder).into(view)
}

