package com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.view.carousel

import androidx.annotation.StringDef
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.view.carousel.CardCarouselItemType.Companion.DEFAULT
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.view.carousel.CardCarouselItemType.Companion.FULL

@Retention(AnnotationRetention.SOURCE)
@StringDef(DEFAULT, FULL)
annotation class CardCarouselItemType() {
    companion object {
        const val DEFAULT = "DEFAULT"
        const val FULL = "FULL"
    }
}
