package com.mercadolibre.android.mlbusinesscomponents.components.utils

import com.mercadolibre.android.ui.font.Font

internal object FontHelper {

    fun from(fontName: String): Font {
        Font.values().forEach {
            if (it.name.equals(fontName, true)) {
                return it
            }
        }
        return Font.REGULAR
    }

}