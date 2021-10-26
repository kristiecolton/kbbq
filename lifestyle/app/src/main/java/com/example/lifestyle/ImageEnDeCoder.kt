package com.example.lifestyle

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import java.io.ByteArrayOutputStream

class ImageEnDeCoder {
    companion object {

        fun encodeImage(bm: Bitmap): String {
            val baos = ByteArrayOutputStream()
            bm.compress(Bitmap.CompressFormat.JPEG, 100, baos)
            val b = baos.toByteArray()
            return Base64.encodeToString(b, Base64.DEFAULT)
        }

        fun decodeImage(bmString: String?): Bitmap {
            val base = Base64.decode(bmString, Base64.DEFAULT)
            return BitmapFactory.decodeByteArray(base, 0, base.size)
        }
    }

}