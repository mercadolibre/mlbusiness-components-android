package com.mercadolibre.android.mlbusinesscomponents.components.pickup

import com.mercadolibre.android.mlbusinesscomponents.R

enum class SizeType(val fontSize: Int, val imageSize: Int) {
    SMALL(R.dimen.ui_fontsize_xsmall, R.dimen.ui_2m),
    MEDIUM(R.dimen.ui_fontsize_small, R.dimen.ui_image_size_medium);

    companion object {
        @JvmStatic
        fun getFontSizeOrDefault(size: String?, defaultSize: Int) : Int {
            return size
                ?.let { values().firstOrNull { value -> value.name.equals(it, ignoreCase = true) } }
                ?.fontSize
                ?: defaultSize
        }

        @JvmStatic
        fun getImageSizeOrDefault(size: String?, defaultSize: Int) : Int {
            return size
                ?.let { values().firstOrNull { value -> value.name.equals(it, ignoreCase = true) } }
                ?.imageSize
                ?: defaultSize
        }
    }
}