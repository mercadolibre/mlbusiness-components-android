package com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.view.carousel.card

import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.carousel.CarouselCard
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.carousel.CarouselCardV2

internal class CarouselCardV2Presenter {

    fun onBind(card: CarouselCard?, view: CarouselCardV2) {
        setImage(card?.image, view)
        setTitle(card?.subtitle, view)
        setOnClickEvent(card?.link, view)
        view.tracking = card?.tracking
    }

    private fun setImage(image: String?, view: CarouselCardV2) {
        image?.let {
            view.showImage(it)
        } ?: run {
            view.hideImage()
        }
    }

    private fun setTitle(title: String?, view: CarouselCardV2) {
        title?.let {
            view.showMainLabel(it)
        } ?: run {
            view.hideMainLabel()
        }
    }

    private fun setOnClickEvent(link: String?, view: CarouselCardV2) {
        link?.let {
            view.onClick(it)
        }
    }
}