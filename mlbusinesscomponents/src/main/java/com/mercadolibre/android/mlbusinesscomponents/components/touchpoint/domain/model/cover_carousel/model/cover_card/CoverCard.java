package com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.cover_carousel.model.cover_card;

import androidx.annotation.Keep;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.tracking.print.TouchpointTracking;
import java.io.Serializable;

@Keep
public class CoverCard implements CoverCardInterface, Serializable {

    private final CoverCardContent content;
    private final TouchpointTracking tracking;
    private final String link;

    public CoverCard(
        final CoverCardContent content,
        final TouchpointTracking tracking, final String link) {
        this.content = content;
        this.tracking = tracking;
        this.link = link;
    }

    @Override
    public CoverCardContent getContent() {
        return content;
    }

    @Override
    public TouchpointTracking getTracking() {
        return tracking;
    }

    @Override
    public String getLink() {
        return link;
    }
}
