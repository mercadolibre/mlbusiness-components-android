package com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.view.flex_cover_carousel.flex_cover_card

import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.flex_cover_carousel.FlexCoverCard

class FlexCoverCardPresenter {

    fun bindView(model: FlexCoverCard?, view: FlexCoverCardInterfaceView) {
        setCoverImage(model?.imageHeader, view)
        setOnClick(model?.link, view)
    }

    private fun setCoverImage(cover: String?, view: FlexCoverCardInterfaceView) {
        cover?.let {
            view.setCoverImage(it)
        }
    }

    private fun setOnClick(link: String?, view: FlexCoverCardInterfaceView) {
        link?.let {
            view.setOnClick(it)
        }
    }
}