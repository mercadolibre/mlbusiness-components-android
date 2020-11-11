package com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.cover_carousel.response;

import com.mercadolibre.android.mlbusinesscomponents.components.common.header.HeaderInterface;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.cover_carousel.model.cover_card.CoverCardInterface;
import java.util.List;

public interface CoverCarouselInterface {

    HeaderInterface getHeader();

    boolean getAlphaAnimation();

    boolean getScaleAnimation();

    boolean getPressAnimation();

    List<CoverCardInterface> getItems();
}
