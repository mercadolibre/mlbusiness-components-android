package com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.view.flex_cover_carousel

import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.cover_carousel.model.cover_card.CoverCardInterfaceModel

interface FlexCoverCarouselViewInterface {
    fun setVisibilityGone()
    fun setItemsList(items: List<CoverCardInterfaceModel?>?)
    fun setViewPagerHeight(maxHeight: Int)
    fun decorate()
}