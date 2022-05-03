package com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.view.flex_cover_carousel

import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.cover_carousel.model.cover_card.CoverCardInterfaceModel
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.cover_carousel.response.CoverCarouselInterfaceModel
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.view.flex_cover_carousel.flex_cover_card.FlexCoverCardInterfaceView

class FlexCoverCarouselPresenter {
    /**
     * Map response to model.
     *
     * @param response to map.
     */
    fun mapResponse(response: CoverCarouselInterfaceModel?, view: FlexCoverCarouselViewInterface) {
        setItemsList(response?.items, view)
    }

    private fun setItemsList(
        items: List<CoverCardInterfaceModel>?,
        view: FlexCoverCarouselViewInterface
    ) {
        if (items == null || items.isEmpty()) {
            view.setVisibilityGone()
            return
        }
        view.setItemsList(items)
    }

    fun getMaxHeight(
        coverCardsViews: MutableList<FlexCoverCardInterfaceView>,
        view: FlexCoverCarouselViewInterface
    ) {
        var maxCoverCardHeight = 0
        for (cardView in coverCardsViews) {
            val itemHeight = cardView.getCoverCardHeight()
            maxCoverCardHeight = Math.max(maxCoverCardHeight, itemHeight)
        }
        view.setViewPagerHeight(maxCoverCardHeight)
        view.decorate()
    }
}