package com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.view.flex_cover_carousel

import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.callback.OnClickCallback
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.flex_cover_carousel.FlexCoverCarouselResponse

class FlexCoverCarouselComponentPresenter(val view: FlexCoverCarouselComponentInterface) {

    private var model: FlexCoverCarouselResponse? = null

    fun bind(model: FlexCoverCarouselResponse, onClickCallback: OnClickCallback?) {
        this.model = model
        view.setCards(model.items, onClickCallback)
    }
}