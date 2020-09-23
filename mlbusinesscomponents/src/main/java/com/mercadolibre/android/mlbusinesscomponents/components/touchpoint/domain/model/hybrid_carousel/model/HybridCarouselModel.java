package com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.hybrid_carousel.model;

import androidx.annotation.Keep;
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
    public int getMaxHeightItemIndex() {
        double maxHeight = 0;
        int i = 0;
        int maxHeigtIndex = 0;
        for (final HybridCarouselCardContainerModel item : items) {
            if (maxHeight < item.getHeight()) {
                maxHeight = item.getHeight();
                maxHeigtIndex = i;
            }
            i++;
        }
        return maxHeigtIndex;
    }
}
