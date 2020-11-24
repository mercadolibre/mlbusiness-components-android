package com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.cover_carousel.model.cover_card;

import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.tracking.print.TouchpointTracking;

public interface CoverCardInterface {

    CoverCardContentInterface getContent();

    TouchpointTracking getTracking();

    String getLink();
}
