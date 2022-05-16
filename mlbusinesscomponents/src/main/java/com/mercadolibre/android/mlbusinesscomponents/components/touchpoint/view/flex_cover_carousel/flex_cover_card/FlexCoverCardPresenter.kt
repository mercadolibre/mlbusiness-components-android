package com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.view.flex_cover_carousel.flex_cover_card

import com.mercadolibre.android.mlbusinesscomponents.components.rowpill.PillInterface
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.flex_cover_carousel.FlexCoverCard

class FlexCoverCardPresenter {

    fun bindView(model: FlexCoverCard?, view: FlexCoverCardInterfaceView) {
        setCoverImage(model?.imageHeader, view)
        setOnClick(model?.link, view)
        setBackground(model?.backgroundColor, view)
        setTitle(model?.title?.text, view)
        setSubtitle(model?.subtitle?.text, view)
        setDescription(model?.mainDescription?.text, view)
        setPill(model?.pill, view)
    }

    private fun setPill(pill: PillInterface?, view: FlexCoverCardInterfaceView) {
        pill?.let {
            view.showPill(pill, view)
        }
    }

    private fun setDescription(description: String?, view: FlexCoverCardInterfaceView) {
        description?.let {
            view.showDescription(description)
        }
    }

    private fun setSubtitle(subtitle: String?, view: FlexCoverCardInterfaceView) {
        subtitle?.let {
            view.showSubtitle(subtitle)
        }
    }

    private fun setTitle(title: String?, view: FlexCoverCardInterfaceView) {
        title?.let {
            view.showTitle(title)
        }
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

    private fun setBackground (backgroundColor: String?, view: FlexCoverCardInterfaceView){
       backgroundColor?.let {
           view.changeBackgroundColor(backgroundColor)
       }
    }
}