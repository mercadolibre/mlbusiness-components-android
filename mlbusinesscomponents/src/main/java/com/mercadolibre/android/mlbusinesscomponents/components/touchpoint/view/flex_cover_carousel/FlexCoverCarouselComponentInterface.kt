package com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.view.flex_cover_carousel

import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.callback.OnClickCallback
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.AdditionalEdgeInsets
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.flex_cover_carousel.FlexCoverCard
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.flex_cover_carousel.FlexCoverCarouselResponse
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.tracking.MLBusinessTouchpointTracker
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.tracking.print.TouchpointPrintProvider

interface FlexCoverCarouselComponentInterface {
   fun bind(
       model: FlexCoverCarouselResponse,
       onClickCallback: OnClickCallback?,
       tracker: MLBusinessTouchpointTracker?,
       printProvider: TouchpointPrintProvider
   )
   fun setCards(cards: List<FlexCoverCard>, onClickCallback: OnClickCallback?)
   fun notifyPadding(additionalEdgeInsets: AdditionalEdgeInsets)
   fun setTracker(tracker: MLBusinessTouchpointTracker?)
   fun setPrintProvider(printProvider: TouchpointPrintProvider)
}