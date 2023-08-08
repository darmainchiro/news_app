package id.ajiguna.newsapp.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import id.ajiguna.newsapp.R

@BindingAdapter("loadImage")
fun loadImage(imageView: ImageView, urlString: String?) {
    urlString?.let {
        Glide.with(imageView)
            .load(urlString)
            .placeholder(R.drawable.placeholder)
            .error(R.drawable.placeholder)
            .into(imageView)
    }
}