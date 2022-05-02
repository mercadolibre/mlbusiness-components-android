package com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.view.flex_cover_carousel

import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.cover_carousel.response.CoverCarouselInterfaceModel
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.view.cover_carousel.CoverCarouselViewInterface
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.cover_carousel.response.CarouselAnimationInterfaceModel
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.cover_carousel.model.cover_card.CoverCardInterfaceModel
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.view.cover_carousel.cover_card.CoverCardInterfaceView

class FlexCoverCarouselPresenter {
    /**
     * Map response to model.
     *
     * @param response to map.
     */
    fun mapResponse(response: CoverCarouselInterfaceModel?, view: CoverCarouselViewInterface) {
        if (response == null || response.items == null) {
            view.showSkeleton()
            return
        }
        view.hideSkeleton()
        setCarouselAnimation(response.carouselAnimation, view)
        setItemsList(response.items, view)
    }

    private fun setCarouselHeader(
        title: String?,
        label: String,
        link: String,
        view: CoverCarouselViewInterface
    ) {
        if (title == null) {
            view.hideHeaderContainer()
            return
        }
        view.setHeaderTitle(title)
        setHeaderAction(label, link, view)
    }

    private fun setHeaderAction(label: String?, link: String?, view: CoverCarouselViewInterface) {
        if (label == null || link == null) {
            view.hideHeaderAction()
            return
        }
        view.setHeaderActionTitle(label)
        view.setHeaderActionClickListener(link)
    }

    private fun setCarouselAnimation(
        carouselAnimation: CarouselAnimationInterfaceModel,
        view: CoverCarouselViewInterface
    ) {
        //TODO: Add an if to set the margin between pages when scaled property is true.
        view.setMarginsForNonScaledAnimation()
        view.setAnimations(
            carouselAnimation.alphaAnimation, carouselAnimation.scaleAnimation,
            carouselAnimation.pressAnimation
        )
    }

    private fun setItemsList(
        items: List<CoverCardInterfaceModel>?,
        view: CoverCarouselViewInterface
    ) {
        if (items == null || items.isEmpty()) {
            view.setVisibilityGone()
            return
        }
        view.setItemsList(items)
    }

    fun getMaxHeight(
        coverCardsViews: List<CoverCardInterfaceView>,
        view: CoverCarouselViewInterface
    ) {
        var maxCoverCardHeight = 0
        var isSkeletonVisible = false
        for (cardView in coverCardsViews) {
            val itemHeight = cardView.coverCardHeight
            isSkeletonVisible = cardView.skeletonState
            maxCoverCardHeight = Math.max(maxCoverCardHeight, itemHeight)
        }
        view.setViewPagerHeight(maxCoverCardHeight, isSkeletonVisible)
        view.decorate()
    }
}