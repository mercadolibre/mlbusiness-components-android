package com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.hybrid_carousel.model;

import android.support.annotation.Keep;
import java.util.List;

@Keep
public class HybridCarouselModel {

    private final List<HybridCarouselCardContainerModel> items;

    public HybridCarouselModel(
        final List<HybridCarouselCardContainerModel> items) {
        this.items = items;
    }

    public List<HybridCarouselCardContainerModel> getItems() {
        return items;
    }
}
