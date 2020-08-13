package com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.hybrid_carousel.model;

import android.support.annotation.Keep;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.view.carousel.HeightCalculatorDelegate;
import java.util.List;

@Keep
public class HybridCarouselModel implements HeightCalculatorDelegate {

    private final List<HybridCarouselCardContainerModel> items;

    public HybridCarouselModel(
        final List<HybridCarouselCardContainerModel> items) {
        this.items = items;
    }

    public List<HybridCarouselCardContainerModel> getItems() {
        return items;
    }

    @Override
    public double getFixedCardHeight() {
        double maxHeight = 0;
        for (final HybridCarouselCardContainerModel carouselCard : items) {
            if (maxHeight < carouselCard.getHeight()) {
                maxHeight = carouselCard.getHeight();
            }
        }
        return maxHeight;
    }
}
