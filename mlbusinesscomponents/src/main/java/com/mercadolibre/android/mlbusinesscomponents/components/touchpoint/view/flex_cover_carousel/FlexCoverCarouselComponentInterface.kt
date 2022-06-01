package com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.view.flex_cover_carousel

import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.callback.OnClickCallback
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.AdditionalEdgeInsets
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.flex_cover_carousel.FlexCoverCard
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.flex_cover_carousel.FlexCoverCarouselResponse

interface FlexCoverCarouselComponentInterface {
   fun bind(model: FlexCoverCarouselResponse, onClickCallback: OnClickCallback?)
   fun setCards(cards: List<FlexCoverCard>, onClickCallback: OnClickCallback?)
   fun notifyPadding(additionalEdgeInsets: AdditionalEdgeInsets)
}