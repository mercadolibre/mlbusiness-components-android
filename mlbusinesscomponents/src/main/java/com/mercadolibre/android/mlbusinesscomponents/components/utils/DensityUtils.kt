package com.mercadolibre.android.mlbusinesscomponents.components.utils

import android.content.Context
import android.util.TypedValue
import kotlin.math.roundToInt

object DensityUtils {
    /**
     * Transforms a DP value into a Pixel value
     *
     * @param context the context
     * @param dp the size on DP scale
     * @return the same size on PX scale
     */
    fun getPxFromDp(context: Context, dp: Int): Int {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            dp.toFloat(),
            context.resources.displayMetrics
        ).roundToInt()
    }
}
