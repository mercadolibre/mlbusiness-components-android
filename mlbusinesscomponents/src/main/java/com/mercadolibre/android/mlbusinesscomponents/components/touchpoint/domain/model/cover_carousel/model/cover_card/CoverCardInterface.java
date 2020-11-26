package com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.cover_carousel.model.cover_card;

import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.tracking.TouchpointTrackeable;

public interface CoverCardInterface extends TouchpointTrackeable {

    CoverCardContentInterface getContent();

    String getLink();
}
