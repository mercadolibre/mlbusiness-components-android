package com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.view.flex_cover_carousel

import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.flex_cover_carousel.FlexCoverCard

interface FlexCoverCarouselViewInterface {
    fun setVisibilityGone()
    fun setItemsList(items: List<FlexCoverCard>)
    fun setViewPagerHeight(maxHeight: Int)
    fun decorate()
}