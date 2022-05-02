package com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.view.flex_cover_carousel.flex_cover_card

import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.tracking.print.TouchpointTracking
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.cover_carousel.model.cover_card.CoverCardInterfaceModel
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.view.cover_carousel.cover_card.CoverCardInterfaceView
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.view.flex_cover_carousel.flex_cover_card.FlexCoverCardPresenter

class FlexCoverCardPresenter {
    private var link: String? = null
    private var tracking: TouchpointTracking? = null

    /**
     * Binds the view.
     *
     * @param model the data to bind.
     */
    fun bindView(model: CoverCardInterfaceModel?, view: CoverCardInterfaceView) {
        if (model == null) {
            view.showSkeleton()
            return
        }
        view.hideSkeleton()
        setCoverImage(model.content.cover, view)
        setOnClick(model.link, view)
        setTracking(model.tracking, view)
        setTopImageStatus(model.content.topImageStatus, view)
    }

    private fun setTopImageStatus(topImageStatus: String?, view: CoverCardInterfaceView) {
        if (topImageStatus != null && CLOSED == topImageStatus.toLowerCase()) {
            view.setTopImageToClosedtStatus()
        } else {
            view.setTopImageToDefaultStatus()
        }
    }

    private fun setCoverImage(cover: String, view: CoverCardInterfaceView) {
        view.setCoverImage(cover)
    }

    private fun setOnClick(link: String?, view: CoverCardInterfaceView) {
        if (link != null && !link.isEmpty()) {
            view.setOnClick(link)
            this.link = link
        } else {
            view.dismissClickable()
        }
    }

    private fun setTracking(tracking: TouchpointTracking?, view: CoverCardInterfaceView) {
        this.tracking = tracking
        view.setTracking(tracking)
    }

    fun setPressAnimationWithLink(view: CoverCardInterfaceView) {
        if (link != null && !link!!.isEmpty()) {
            view.setOnClickListenerWithAnimationAndLink(link, tracking)
        } else {
            view.dismissClickable()
        }
    }

    companion object {
        const val CLOSED = "closed"
    }
}