package com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.cover_carousel.response;

import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.TouchpointContent;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.cover_carousel.model.cover_card.CoverCardInterfaceModel;
import java.util.List;

public interface CoverCarouselInterfaceModel extends TouchpointContent {

    String getTitle();

    String getLabel();

    String getLink();

    CarouselAnimationInterfaceModel getCarouselAnimation();

    List<CoverCardInterfaceModel> getItems();
}
