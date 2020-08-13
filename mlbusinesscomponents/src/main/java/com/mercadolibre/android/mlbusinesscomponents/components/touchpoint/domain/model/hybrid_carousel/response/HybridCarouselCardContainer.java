package com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.hybrid_carousel.response;

import android.support.annotation.Keep;
import android.support.annotation.Nullable;
import com.google.gson.JsonElement;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.tracking.print.TouchpointTracking;
import java.io.Serializable;

@Keep
public class HybridCarouselCardContainer implements Serializable {

    private final String type;
    private final String link;
    private final TouchpointTracking tracking;
    @Nullable public JsonElement content;

    public HybridCarouselCardContainer(final String type, final String link,
        final TouchpointTracking tracking, @Nullable final JsonElement content) {
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
    public JsonElement getContent() {
        return content;
    }
}
