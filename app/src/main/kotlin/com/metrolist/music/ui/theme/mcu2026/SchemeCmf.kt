/*
 * Copyright 2026 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.metrolist.music.ui.theme.mcu2026

import com.materialkolor.scheme.DynamicScheme
import com.materialkolor.scheme.Variant
import com.materialkolor.hct.Hct
import com.materialkolor.palettes.TonalPalette

/** A Dynamic Color theme with 2 source colors. */
class SchemeCmf(
  sourceColorHctList: List<Hct>,
  isDark: Boolean,
  contrastLevel: Double,
  platform: Platform = Platform.PHONE,
) :
  DynamicScheme(
    sourceColorHctList = sourceColorHctList,
    variant = Variant.TONAL_SPOT,
    isDark = isDark,
    contrastLevel = contrastLevel,
    platform = platform,
    colorSpec = ColorSpec2026(),
    primaryPalette =
      TonalPalette.fromHueAndChroma(
        sourceColorHctList.first().hue,
        sourceColorHctList.first().chroma,
      ),
    secondaryPalette =
      TonalPalette.fromHueAndChroma(
        sourceColorHctList.first().hue,
        sourceColorHctList.first().chroma * 0.5,
      ),
    tertiaryPalette = tertiaryPalette(sourceColorHctList),
    neutralPalette =
      TonalPalette.fromHueAndChroma(
        sourceColorHctList.first().hue,
        sourceColorHctList.first().chroma * 0.2,
      ),
    neutralVariantPalette =
      TonalPalette.fromHueAndChroma(
        sourceColorHctList.first().hue,
        sourceColorHctList.first().chroma * 0.2,
      ),
    errorPalette =
      TonalPalette.fromHueAndChroma(
        getErrorHue(sourceColorHctList.first().hue, tertiaryPalette(sourceColorHctList).hue),
        sourceColorHctList.first().chroma.coerceAtLeast(50.0),
      ),
  ) {

  constructor(
    sourceColorHct: Hct,
    isDark: Boolean,
    contrastLevel: Double,
    platform: Platform = Platform.PHONE,
  ) : this(listOf(sourceColorHct), isDark, contrastLevel, platform)

  companion object {
    private fun getErrorHue(primaryHue: Double, tertiaryHue: Double): Double {
      if (primaryHue <= 8) {
        return if (tertiaryHue <= 24) 28.0 else if (tertiaryHue <= 32) 16.0 else 20.0
      } else if (primaryHue <= 16) {
        return if (tertiaryHue <= 24) 32.0 else if (tertiaryHue <= 32) 20.0 else 24.0
      } else if (primaryHue <= 20) {
        return if (tertiaryHue <= 28) 32.0 else if (tertiaryHue <= 32) 24.0 else 28.0
      } else if (primaryHue <= 28) {
        return if (tertiaryHue <= 24) 32.0 else 16.0
      } else if (primaryHue <= 32) {
        return if (tertiaryHue <= 20) 24.0 else if (tertiaryHue <= 28) 16.0 else 20.0
      } else if (primaryHue <= 40) {
        return if (tertiaryHue > 20 && tertiaryHue <= 28) 16.0 else 24.0
      } else if (primaryHue <= 152) {
        return if (tertiaryHue > 24 && tertiaryHue <= 36) 20.0 else 32.0
      } else if (primaryHue <= 272) {
        return if (tertiaryHue > 20 && tertiaryHue <= 28) 16.0 else 24.0
      } else {
        return if (tertiaryHue > 12 && tertiaryHue <= 28) 32.0 else 16.0
      }
    }

    private fun tertiaryPalette(sourceColorHctList: List<Hct>): TonalPalette {
      val sourceColorHct = sourceColorHctList.first()
      val secondarySourceColorHct = sourceColorHctList.getOrNull(1) ?: sourceColorHct
      return if (sourceColorHct.toInt() == secondarySourceColorHct.toInt()) {
        TonalPalette.fromHueAndChroma(sourceColorHct.hue, sourceColorHct.chroma * 0.75)
      } else {
        TonalPalette.fromHueAndChroma(secondarySourceColorHct.hue, secondarySourceColorHct.chroma)
      }
    }
  }
}
