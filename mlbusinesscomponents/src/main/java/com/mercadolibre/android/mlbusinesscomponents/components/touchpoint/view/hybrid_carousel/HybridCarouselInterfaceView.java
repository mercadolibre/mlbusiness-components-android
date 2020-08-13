package com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.view.hybrid_carousel;

import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.hybrid_carousel.model.HybridCarouselCardContainerModel;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.view.carousel.HeightCalculatorDelegate;
import java.util.List;

public interface HybridCarouselInterfaceView {

    /**
     * Show items in the list.
     *
     * @param items to show.
     */
    void showItems(List<HybridCarouselCardContainerModel> items, final HeightCalculatorDelegate heightCalculator);
}
