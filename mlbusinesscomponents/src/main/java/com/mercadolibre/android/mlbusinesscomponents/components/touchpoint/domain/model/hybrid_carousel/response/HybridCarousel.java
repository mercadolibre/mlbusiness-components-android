package com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.hybrid_carousel.response;

import android.support.annotation.Keep;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.TouchpointContent;
import java.io.Serializable;
import java.util.List;

@Keep
public class HybridCarousel implements TouchpointContent, Serializable {

    private final List<HybridCarouselCardContainer> items;

    public HybridCarousel(final List<HybridCarouselCardContainer> items) {
        this.items = items;
    }

    public List<HybridCarouselCardContainer> getItems() {
        return items;
    }
}
