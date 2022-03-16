@file:Suppress("KotlinConstantConditions")

package com.heinika.pokeg.utils

import android.content.ContentValues
import android.content.Context
import android.graphics.Bitmap
import android.os.Build
import android.provider.MediaStore
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.github.florent37.glidepalette.BitmapPalette
import com.github.florent37.glidepalette.GlidePalette

object ImageUtils {

  fun loadImage(imageView: ImageView, imageUrl: String) {
    if (com.heinika.pokeg.BuildConfig.FLAVOR == "google") {
      Glide.with(imageView)
        .load(imageUrl)
        .listener(
          GlidePalette.with(imageUrl)
            .use(BitmapPalette.Profile.MUTED_LIGHT)
            .intoCallBack { palette ->
              val rgb = palette?.dominantSwatch?.rgb
              if (rgb != null) {
                imageView.background.setTint(rgb)
              }
            }.crossfade(true)
        ).apply(RequestOptions.bitmapTransform(BlurTransformation(10, 1))).into(imageView)
    } else {
      Glide.with(imageView)
        .load(imageUrl)
        .listener(
          GlidePalette.with(imageUrl)
            .use(BitmapPalette.Profile.MUTED_LIGHT)
            .intoCallBack { palette ->
              val rgb = palette?.dominantSwatch?.rgb
              if (rgb != null) {
                imageView.background.setTint(rgb)
              }
            }.crossfade(true)
        ).into(imageView)
    }
  }

  fun saveBitmap2Gallery2(
    context: Context,
    bitmap: Bitmap,
    name: String = System.currentTimeMillis().toString()
  ): Boolean {
    val contentValues = ContentValues().apply {
      put(MediaStore.MediaColumns.DISPLAY_NAME, name)
      put(MediaStore.Images.Media.MIME_TYPE, "image/png")
    }
    val insert = context.contentResolver.insert(
      MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
      contentValues
    ) ?: return false //为空的话 直接失败返回了
    //这个打开了输出流  直接保存图片就好了
    context.contentResolver.openOutputStream(insert).use {
      it ?: return false
      bitmap.compress(Bitmap.CompressFormat.JPEG, 100, it)
    }
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
      contentValues.put(MediaStore.MediaColumns.IS_PENDING, false)
    }
    return true
  }

}
