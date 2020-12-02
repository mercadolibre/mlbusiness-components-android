package com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.view.cover_carousel;

import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.cover_carousel.model.cover_card.CoverCardInterfaceModel;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.cover_carousel.response.CarouselAnimationInterfaceModel;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.cover_carousel.response.CoverCarouselInterfaceModel;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.view.cover_carousel.cover_card.CoverCardInterfaceView;
import java.util.List;

public class CoverCarouselPresenter {

    /**
     * Map response to model.
     *
     * @param response to map.
     */
    public void mapResponse(final CoverCarouselInterfaceModel response, final CoverCarouselViewInterface view) {

        if (response == null) {
            view.showSkeleton();
            return;
        }

        view.hideSkeleton();
        setCarouselHeader(response.getTitle(), response.getLabel(), response.getLink(), view);
        setCarouselAnimation(response.getCarouselAnimation(), view);
        setItemsList(response.getItems(), view);
    }

    private void setCarouselHeader(final String title, final String label, final String link, final CoverCarouselViewInterface view) {
        if (title == null) {
            view.hideHeaderContainer();
            return;
        }
        view.setHeaderTitle(title);

        setHeaderAction(label, link, view);
    }

    private void setHeaderAction(final String label, final String link, final CoverCarouselViewInterface view) {
        if (label == null || link == null) {
            view.hideHeaderAction();
            return;
        }
        view.setHeaderActionTitle(label);
        view.setHeaderActionClickListener(link);
    }

    private void setCarouselAnimation(final CarouselAnimationInterfaceModel carouselAnimation, final CoverCarouselViewInterface view) {
        //TODO: Add an if to set the margin between pages when scaled property is true.
        view.setMarginsForNonScaledAnimation();

        view.setAnimations(carouselAnimation.getAlphaAnimation(), carouselAnimation.getScaleAnimation(),
            carouselAnimation.getPressAnimation());
    }

    private void setItemsList(final List<CoverCardInterfaceModel> items, final CoverCarouselViewInterface view) {
        if (items == null || items.isEmpty()) {
            view.setVisibilityGone();
            return;
        }

        view.setItemsList(items);
    }

    public void getMaxHeight(final List<CoverCardInterfaceView> coverCardsViews, final CoverCarouselViewInterface view) {
        int maxCoverCardHeight = 0;
        boolean isSkeletonVisible = false;

        for (final CoverCardInterfaceView cardView : coverCardsViews) {
            final int itemHeight = cardView.getCoverCardHeight();
            isSkeletonVisible = cardView.getSkeletonState();

            maxCoverCardHeight = Math.max(maxCoverCardHeight, itemHeight);
        }

        view.setViewPagerHeight(maxCoverCardHeight, isSkeletonVisible);
    }
}
