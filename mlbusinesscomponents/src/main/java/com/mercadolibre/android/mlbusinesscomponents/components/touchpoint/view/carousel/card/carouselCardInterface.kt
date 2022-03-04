package com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.view.carousel.card

import com.mercadolibre.android.mlbusinesscomponents.common.TouchpointImageLoader
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.callback.OnClickCallback
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.tracking.MLBusinessTouchpointTracker

interface carouselCardInterface {

    fun setOnClickCallback(onClickCallback: OnClickCallback)

    fun setTracker(tracker: MLBusinessTouchpointTracker)

    fun setExtraData(extraData: MutableMap<String, Any>)

    fun setCanOpenMercadoPago(isMPInstalled: Boolean)

    fun setImageLoader(touchPointImageLoader: TouchpointImageLoader)
}