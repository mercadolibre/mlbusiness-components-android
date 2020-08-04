package com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.hybrid_carousel.test;

import android.support.annotation.Keep;
import android.support.annotation.Nullable;
import com.google.gson.JsonElement;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.tracking.print.TouchpointTracking;
import java.io.Serializable;

@Keep
public class HybridCarouselCardContainer implements Serializable {

    private final String cardType;
    private final String link;
    private final TouchpointTracking tracking;
    @Nullable public JsonElement cardContent;

    public HybridCarouselCardContainer(final String cardType, final String link,
        final TouchpointTracking tracking, @Nullable final JsonElement cardContent) {
        this.cardType = cardType;
        this.link = link;
        this.tracking = tracking;
        this.cardContent = cardContent;
    }
}
