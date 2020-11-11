package com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.cover_carousel.model.cover_card;

import androidx.annotation.Keep;
import com.mercadolibre.android.mlbusinesscomponents.components.row.model.TouchpointRowItemInterface;
import com.mercadolibre.android.mlbusinesscomponents.components.row.model.test.TouchpointRowItem;
import java.io.Serializable;

@Keep
public class CoverCard implements CoverCardInterface, Serializable{

    private final String cover;
    private final TouchpointRowItem description;

    public CoverCard(final String cover, final TouchpointRowItem description) {
        this.cover = cover;
        this.description = description;
    }

    @Override
    public String getCover() {
        return cover;
    }

    @Override
    public TouchpointRowItemInterface getDescription() {
        return description;
    }
}
