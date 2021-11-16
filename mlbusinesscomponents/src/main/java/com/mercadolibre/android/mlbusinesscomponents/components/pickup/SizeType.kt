package com.mercadolibre.android.mlbusinesscomponents.components.pickup

import com.mercadolibre.android.mlbusinesscomponents.R
import java.lang.Exception

enum class SizeType(val fontSize: Int, val imageSize: Int) {
    SMALL(R.dimen.ui_fontsize_xsmall, R.dimen.ui_2m),
    MEDIUM(R.dimen.ui_fontsize_small, R.dimen.ui_image_size_medium);

    companion object {
        @JvmStatic
        fun getFontSizeOrDefault(size: String?, defaultSize: Int) : Int {
            size?.let {
                return if (values().any { it.name.equals(size, ignoreCase = true) }) {
                    valueOf(size).fontSize
                } else {
                    defaultSize
                }
            } ?: return defaultSize
        }

        @JvmStatic
        fun getImageSizeOrDefault(size: String?, defaultSize: Int) : Int {
            size?.let {
                return if (values().any { it.name.equals(size, ignoreCase = true) }) {
                    valueOf(size).imageSize
                } else {
                    defaultSize
                }
            } ?: return defaultSize
        }
    }
}