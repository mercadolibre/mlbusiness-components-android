package com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.view.cover_carousel;

import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.cover_carousel.model.cover_card.CoverCardInterface;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.view.cover_carousel.cover_card.CoverCardView;
import java.util.List;

public interface CoverCarouselViewInterface {

    void setVisibilityGone();

    void setItemsList(final List<CoverCardInterface> items);

    void hideHeaderContainer();

    void setHeaderTitle(String title);

    void hideHeaderAction();

    void setHeaderActionTitle(String title);

    void setHeaderActionClickListener(String link);

    void setAlphaAnimation();

    void setScaleAnimation();

    void setPressAnimation();

    void setViewPagerHeight(int maxHeight);

    void setElementsViews(List<CoverCardView> coverCardsViews);
}
