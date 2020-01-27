package com.mvproject.weatherapp.utils

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso
/*
@BindingAdapter("app:url" , "app:errorImage")
fun loadImage(view: ImageView, url: String,  errorImage : Drawable) {
         Picasso.get().load(url).error(errorImage).into(view)
}
*/
@BindingAdapter(value = ["app:url", "app:placeholder"], requireAll = false)
fun loadImage(view: ImageView,url: String?,placeHolder: Drawable
) {
    Picasso.get().load(url).placeholder(placeHolder).into(view)
}
