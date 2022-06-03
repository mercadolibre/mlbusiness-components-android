package com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.view.flex_cover_carousel

import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.callback.OnClickCallback
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.flex_cover_carousel.FlexCoverCarouselResponse
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.tracking.MLBusinessTouchpointTracker
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.tracking.print.TouchpointPrintProvider

class FlexCoverCarouselComponentPresenter(val view: FlexCoverCarouselComponentInterface) {

    private var model: FlexCoverCarouselResponse? = null

    fun bind(
        model: FlexCoverCarouselResponse,
        onClickCallback: OnClickCallback?,
        tracker: MLBusinessTouchpointTracker?,
        printProvider: TouchpointPrintProvider
    ) {
        this.model = model
        view.setCards(model.items, onClickCallback)
        view.setTracker(tracker)
        view.setPrintProvide(printProvider)
    }
}