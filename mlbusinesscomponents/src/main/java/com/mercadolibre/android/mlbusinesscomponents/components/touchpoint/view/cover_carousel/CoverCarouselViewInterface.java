package com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.view.cover_carousel;

import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.cover_carousel.model.cover_card.CoverCardInterface;
import java.util.List;

public interface CoverCarouselViewInterface {

    void setVisibilityGone();

    void setItemsList(final List<CoverCardInterface> items);
}
