package com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.view.hybrid_carousel;

import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.hybrid_carousel.mapper.HybridCarouselMapper;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.hybrid_carousel.mapper.TouchpointItemContentMapper;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.hybrid_carousel.model.HybridCarouselModel;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.hybrid_carousel.response.HybridCarousel;

public class HybridCarouselPresenter {

    private HybridCarouselInterfaceView view;
    private HybridCarouselModel model;
    private HybridCarouselMapper mapper;

    /**
     * Constructor
     *
     * @param view view to interact with
     */
    public HybridCarouselPresenter(final HybridCarouselInterfaceView view) {
        this.view = view;
        mapper = new HybridCarouselMapper(new TouchpointItemContentMapper());
    }

    /**
     * Map response to model.
     * @param response to map.
     */
    public void mapResponse(final HybridCarousel response) {
        model = mapper.mapResponse(response);
        view.showItems(model.getItems(), model);
    }
}
