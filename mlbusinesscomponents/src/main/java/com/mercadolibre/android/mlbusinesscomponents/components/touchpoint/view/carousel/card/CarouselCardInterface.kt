package com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.view.carousel.card

import com.mercadolibre.android.mlbusinesscomponents.common.TouchpointImageLoader
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.callback.OnClickCallback
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.carousel.CarouselCard
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.tracking.MLBusinessTouchpointTracker

interface CarouselCardInterface {

    /**
     * Bind a Card.
     *
     * @param card The info of card to bind.
     * @param size The height of Card to draw.
     */
    fun bind(card: CarouselCard?, size: Int)

    /**
     * Set a callback on click.
     *
     * @param onClickCallback The callback to listen.
     */
    fun setOnClickCallback(onClickCallback: OnClickCallback)

    /**
     * Set the tracking information.
     *
     * @param tracker The tracking information.
     */
    fun setTracker(tracker: MLBusinessTouchpointTracker)

    /**
     * Set the extra data tracking information.
     *
     * @param extraData The extra data information.
     */
    fun setExtraData(extraData: MutableMap<String, Any>)

    /**
     * Show if MP is installed.
     *
     * @param isMPInstalled Return true if MP is installed, false otherwise.
     */
    fun setCanOpenMercadoPago(isMPInstalled: Boolean)

    /**
     * Sets the image loader.
     *
     * @param imageLoader The image loader.
     */
    fun setImageLoader(imageLoader: TouchpointImageLoader)
}
