package com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.view.cover_carousel;

import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.cover_carousel.model.cover_card.CoverCardInterface;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.cover_carousel.response.CarouselAnimationInterface;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.cover_carousel.response.CoverCarouselInterface;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.view.cover_carousel.cover_card.CoverCardView;
import java.util.List;

public class CoverCarouselPresenter {

    private final CoverCarouselViewInterface view;

    /**
     * Constructor
     *
     * @param view view to interact with
     */
    public CoverCarouselPresenter(final CoverCarouselViewInterface view) {
        this.view = view;
    }

    /**
     * Map response to model.
     *
     * @param response to map.
     */
    public void mapResponse(final CoverCarouselInterface response) {

        if (response == null) {
            view.showSkeleton();
            return;
        }

        view.hideSkeleton();
        setCarouselHeader(response.getTitle(), response.getLabel(), response.getLink());
        setCarouselAnimation(response.getCarouselAnimation());
        setItemsList(response.getItems());
    }

    private void setCarouselHeader(final String title, final String label, final String link) {
        if (title == null) {
            view.hideHeaderContainer();
            return;
        }
        view.setHeaderTitle(title);

        setHeaderAction(label, link);
    }

    private void setHeaderAction(final String label, final String link) {
        if (label == null || link == null) {
            view.hideHeaderAction();
            return;
        }
        view.setHeaderActionTitle(label);
        view.setHeaderActionClickListener(link);
    }

    private void setCarouselAnimation(final CarouselAnimationInterface carouselAnimation) {
        //TODO: Add an if to set the margin between pages when scaled property is true.
        view.setMarginsForNonScaledAnimation();

        view.setAnimations(carouselAnimation.getAlphaAnimation(), carouselAnimation.getScaleAnimation(),
            carouselAnimation.getPressAnimation());
    }

    private void setItemsList(final List<CoverCardInterface> items) {
        if (items == null || items.isEmpty()) {
            view.setVisibilityGone();
            return;
        }

        view.setItemsList(items);
    }

    public void getMaxHeight(final List<CoverCardView> coverCardsViews) {
        int maxCoverCardHeight = 0;
        boolean isSkeletonVisible = false;

        for (final CoverCardView view : coverCardsViews) {
            final int itemHeight = view.getCoverCardHeight();
            isSkeletonVisible = view.getSkeletonState();

            maxCoverCardHeight = Math.max(maxCoverCardHeight, itemHeight);
        }

        view.setViewPagerHeight(maxCoverCardHeight, isSkeletonVisible);
        view.setElementsViews(coverCardsViews);
    }
}
