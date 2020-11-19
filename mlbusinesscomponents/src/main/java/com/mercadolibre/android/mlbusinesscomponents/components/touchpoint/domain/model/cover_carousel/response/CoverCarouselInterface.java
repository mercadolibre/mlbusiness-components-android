package com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.cover_carousel.response;

import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.cover_carousel.model.cover_card.CoverCardInterface;
import java.util.List;

public interface CoverCarouselInterface {

    String getTitle();

    String getLabel();

    String getLink();

    CarouselAnimationInterface getCarouselAnimation();

    List<CoverCardInterface> getItems();
}
