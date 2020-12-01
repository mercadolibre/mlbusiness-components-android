package com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.view.cover_carousel;

import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.cover_carousel.model.cover_card.CoverCardInterfaceModel;
import java.util.List;

public interface CoverCarouselViewInterface {

    void setVisibilityGone();

    void setItemsList(final List<CoverCardInterfaceModel> items);

    void hideHeaderContainer();

    void setHeaderTitle(String title);

    void hideHeaderAction();

    void setHeaderActionTitle(String title);

    void setHeaderActionClickListener(String link);

    void setViewPagerHeight(int maxHeight, final boolean isSkeletonVisible);

    void setAnimations(boolean alphaAnimation, boolean scaleAnimation, boolean pressAnimation);

    void setMarginsForScaledAnimation();

    void setMarginsForNonScaledAnimation();

    void showSkeleton();

    void hideSkeleton();
}
