package com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.view.carousel.card

import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.carousel.CarouselCard
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.carousel.CarouselCardFullView

internal class CarouselCardFullViewPresenter {

    fun onBind(card: CarouselCard?, view: CarouselCardFullView) {
        setImage(card?.image, view)
        setTitle(card?.subtitle, view)
        setOnClickEvent(card?.link, view)
        view.tracking = card?.tracking
    }

    private fun setImage(image: String?, view: CarouselCardFullView) {
        image?.let {
            view.showImage(it)
        } ?: run {
            view.hideImage()
        }
    }

    private fun setTitle(title: String?, view: CarouselCardFullView) {
        title?.let {
            view.showMainLabel(it)
        } ?: run {
            view.hideMainLabel()
        }
    }

    private fun setOnClickEvent(link: String?, view: CarouselCardFullView) {
        link?.let {
            view.onClick(it)
        }
    }
}
