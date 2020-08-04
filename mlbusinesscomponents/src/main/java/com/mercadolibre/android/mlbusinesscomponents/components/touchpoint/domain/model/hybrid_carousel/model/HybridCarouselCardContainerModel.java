package com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.hybrid_carousel.model;

import android.support.annotation.Keep;
import android.support.annotation.Nullable;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.tracking.print.TouchpointTracking;

@Keep
public class HybridCarouselCardContainerModel {

    private final String type;
    private final String link;
    private final TouchpointTracking tracking;
    @Nullable public TouchpointItem content;

    public HybridCarouselCardContainerModel(final String type, final String link,
        final TouchpointTracking tracking, @Nullable final TouchpointItem content) {
        this.type = type;
        this.link = link;
        this.tracking = tracking;
        this.content = content;
    }

    public String getType() {
        return type;
    }

    public String getLink() {
        return link;
    }

    public TouchpointTracking getTracking() {
        return tracking;
    }

    @Nullable
    public TouchpointItem getContent() {
        return content;
    }
}
