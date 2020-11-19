package com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.cover_carousel.model.cover_card;

import com.mercadolibre.android.mlbusinesscomponents.components.row.model.test.TouchpointRowItem;

public class CoverCardContent extends TouchpointRowItem implements CoverCardContentInterface {

    private final String cover;

    public CoverCardContent(final String cover) {
        this.cover = cover;
    }

    @Override
    public String getCover() {
        return cover;
    }
}
