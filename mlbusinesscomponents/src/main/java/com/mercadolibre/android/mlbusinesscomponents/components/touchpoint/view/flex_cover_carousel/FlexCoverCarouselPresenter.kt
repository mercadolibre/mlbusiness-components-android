package com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.view.flex_cover_carousel

import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.flex_cover_carousel.FlexCoverCard
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.flex_cover_carousel.FlexCoverCarouselResponse
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.view.flex_cover_carousel.flex_cover_card.FlexCoverCardInterfaceView

class FlexCoverCarouselPresenter {

    fun mapResponse(response: FlexCoverCarouselResponse, view: FlexCoverCarouselViewInterface) {
        setItemsList(response.items, view)
    }

    private fun setItemsList(items: List<FlexCoverCard>, view: FlexCoverCarouselViewInterface) {
        if (items.isNullOrEmpty()) {
            view.setVisibilityGone()
            return
        }
        view.setItemsList(items)
    }

}
