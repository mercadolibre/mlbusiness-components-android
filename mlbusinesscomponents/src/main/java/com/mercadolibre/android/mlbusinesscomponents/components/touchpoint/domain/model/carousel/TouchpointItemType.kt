package com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.carousel

import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.tracking.TouchpointTrackeable
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.view.carousel.card.CarouselCardView

enum class TouchpointV2ItemType(
        private val model: Class<out TouchpointTrackeable>
) {
    DEFAULT(CarouselCardView::class.java),
    FULL(CarouselCardV2::class.java);
}