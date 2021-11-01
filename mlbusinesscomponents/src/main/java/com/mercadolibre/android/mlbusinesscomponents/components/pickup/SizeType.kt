package com.mercadolibre.android.mlbusinesscomponents.components.pickup

import com.mercadolibre.android.mlbusinesscomponents.R
import java.lang.Exception

enum class SizeType(val fontSize: Int, val imageSize: Int) {
    SMALL(R.dimen.ui_fontsize_xsmall, R.dimen.ui_2m),
    MEDIUM(R.dimen.ui_fontsize_small, R.dimen.ui_2m);

    companion object {
        @JvmStatic
        fun getSizeOrDefault(size: String?, defaultSize: Int): Int {
            return try {
                if (size.isNullOrEmpty()) defaultSize else valueOf(size).fontSize
            } catch (e: Exception) {
                defaultSize
            }
        }
    }
}