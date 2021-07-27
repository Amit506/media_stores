package com.example.media_stores

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Color
import androidx.palette.graphics.Palette
import io.flutter.plugin.common.MethodChannel

class PaletteHelper (context: Context, result: MethodChannel.Result) {

    private val result: MethodChannel.Result = result

    fun createPaletteAsync(byteArray: ByteArray) {
        Palette.from(BitmapFactory.decodeByteArray(byteArray , 0, byteArray!!.size)).generate { palette ->

            val defaultValue = Color.WHITE
            val map = hashMapOf<String, Any?>(
                    "darkMutedColor" to palette?.getDarkMutedColor(defaultValue),
                    "darkVibrantColor" to palette?.getDarkVibrantColor(defaultValue),
                    "dominantColor" to palette?.getDominantColor(defaultValue),
                    "lightMutedColor" to palette?.getLightMutedColor(defaultValue),
                    "lightVibrantColor" to palette?.getLightVibrantColor(defaultValue),
                    "mutedColor" to palette?.getMutedColor(defaultValue),
                    "vibrantColor" to palette?.getVibrantColor(defaultValue)

            )
            result.success(map)


        }


    }
}