package com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.view.carousel.card_full

import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.carousel.CarouselCard
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.carousel.CarouselTextFormat

internal class CarouselCardFullViewPresenter {

    fun onBind(card: CarouselCard?, view: CarouselCardFullView) {
        setImage(card?.image, view)
        setTitle(card?.title, view)
        setTitleFormat(card?.titleFormat, view)
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

    private fun setTitleFormat(titleFormat: CarouselTextFormat?, view: CarouselCardFullView) {
        if (titleFormat == null) {
            view.changeTitleFontStyleToDefault()
            return
        }
        if (!titleFormat.weight.isNullOrEmpty()) {
            view.changeTitleFontStyle(titleFormat.weight)
        }
        if (!titleFormat.color.isNullOrEmpty()) {
            view.changeTitleColor(titleFormat.color)
        }
        if (titleFormat.size > 0) {
            view.changeTitleSize(titleFormat.size)
        }
    }

    private fun setOnClickEvent(link: String?, view: CarouselCardFullView) {
        link?.let {
            view.onClick(it)
        }
    }
}
