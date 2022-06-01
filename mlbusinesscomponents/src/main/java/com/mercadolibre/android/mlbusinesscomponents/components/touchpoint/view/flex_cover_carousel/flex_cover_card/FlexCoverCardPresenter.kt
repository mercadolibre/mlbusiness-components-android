package com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.view.flex_cover_carousel.flex_cover_card

import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.flex_cover_carousel.FlexCoverCard
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.flex_cover_carousel.Logo
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.flex_cover_carousel.Text
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.tracking.print.TouchpointTracking
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.view.flex_cover_carousel.pill_touchpoint.PillInterface

class FlexCoverCardPresenter {

    private var tracking: TouchpointTracking? = null

    fun bindView(model: FlexCoverCard?, view: FlexCoverCardInterfaceView) {
        setCoverImage(model?.imageHeader, view)
        setOnClick(model?.link, view)
        setBackground(model?.backgroundColor, view)
        setTitle(model?.title, view)
        setSubtitle(model?.subtitle, view)
        setDescription(model?.mainDescription, view)
        setPill(model?.pill, view)
        setLogo(model?.logos, view)
        setTracking(model?.tracking, view)
    }

    private fun setLogo(logos: List<Logo>?, view: FlexCoverCardInterfaceView) {
        if(!logos.isNullOrEmpty()){
            view.showLogo(logos, view)
        }
    }

    private fun setPill(pill: PillInterface?, view: FlexCoverCardInterfaceView) {
        pill?.let {
            view.showPill(pill, view)
        }
    }

    private fun setDescription(description: Text?, view: FlexCoverCardInterfaceView) {
        description?.text?.let {
            view.showDescription(description)
        }
    }

    private fun setSubtitle(subtitle: Text?, view: FlexCoverCardInterfaceView) {
        subtitle?.text?.let {
            view.showSubtitle(subtitle)
        }
    }

    private fun setTitle(title: Text?, view: FlexCoverCardInterfaceView) {
        title?.text?.let {
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

    private fun setTracking(tracking: TouchpointTracking?, view: FlexCoverCardInterfaceView) {
        this.tracking = tracking
        view.setTracking(tracking)
    }
}