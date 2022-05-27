package com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.view.flex_cover_carousel

import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.flex_cover_carousel.FlexCoverCarouselResponse

class FlexCoverCarouselComponentPresenter(val view: FlexCoverCarouselComponent) {

    private var model: FlexCoverCarouselResponse? = null

    fun bind(model: FlexCoverCarouselResponse) {
        this.model = model
        view.setCards(model.items)
    }
}