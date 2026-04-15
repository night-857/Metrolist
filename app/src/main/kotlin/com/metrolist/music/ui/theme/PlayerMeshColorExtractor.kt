package com.metrolist.music.ui.theme

import android.graphics.Bitmap
import androidx.compose.ui.graphics.Color
import com.materialkolor.hct.Hct
import com.materialkolor.quantize.QuantizerCelebi

object PlayerMeshColorExtractor {

    private const val TARGET_CHROMA = 30.0
    private const val WEIGHT_PROPORTION = 0.4
    private const val WEIGHT_CHROMA_ABOVE = 0.6
    private const val DESIRED_COLORS = 6

    fun extract(bitmap: Bitmap): List<Color> {
        val scaledBitmap = Bitmap.createScaledBitmap(bitmap, 112, 112, true)
        val pixels = IntArray(scaledBitmap.width * scaledBitmap.height)
        scaledBitmap.getPixels(pixels, 0, scaledBitmap.width, 0, 0, scaledBitmap.width, scaledBitmap.height)

        val results = QuantizerCelebi.quantize(pixels, 128)
        val totalPopulation = results.values.sum().toDouble()

        val scoredHcts = mutableListOf<Pair<Hct, Double>>()
        var topNeutral: Hct? = null
        var maxNeutralPop = 0

        for ((argb, population) in results) {
            val hct = Hct.fromInt(argb)
            val proportion = population / totalPopulation

            if (hct.chroma < 30.0) {
                if (population > maxNeutralPop) {
                    topNeutral = hct
                    maxNeutralPop = population
                }
                continue
            }

            val proportionScore = proportion * 100 * WEIGHT_PROPORTION
            val chromaScore = (hct.chroma - TARGET_CHROMA) * WEIGHT_CHROMA_ABOVE
            scoredHcts.add(hct to (proportionScore + chromaScore))
        }

        scoredHcts.sortByDescending { it.second }

        val finalColors = mutableListOf<Color>()

        topNeutral?.let { finalColors.add(Color(it.toInt())) }

        for (pair in scoredHcts) {
            if (finalColors.size >= DESIRED_COLORS) break
            
            val colorToAdd = Color(pair.first.toInt())
            if (!finalColors.contains(colorToAdd)) {
                finalColors.add(colorToAdd)
            }
        }

        if (finalColors.isEmpty()) finalColors.add(Color.Black)
        
        while (finalColors.size < DESIRED_COLORS) {
            finalColors.add(finalColors.last())
        }

        return finalColors
    }
}
