package com.metrolist.music.ui.theme

import android.graphics.Bitmap
import com.materialkolor.quantize.QuantizerCelebi
import com.materialkolor.score.Score

object PlayerMeshColorExtractor {

    private const val DESIRED_POOL_SIZE = 6

    fun extract(bitmap: Bitmap): List<Int> {
        val scaledBitmap = Bitmap.createScaledBitmap(bitmap, 112, 112, true)
        val pixels = IntArray(scaledBitmap.width * scaledBitmap.height)
        scaledBitmap.getPixels(pixels, 0, scaledBitmap.width, 0, 0, scaledBitmap.width, scaledBitmap.height)

        val results = QuantizerCelebi.quantize(pixels, 128)

        return Score.score(
            colorsToPopulation = results,
            desired = DESIRED_POOL_SIZE,
            fallbackColorArgb = FALLBACK_COLOR,
            filter = false
        )
    }
}
