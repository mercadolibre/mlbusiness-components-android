package com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.hybrid_carousel.mapper;

import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.hybrid_carousel.model.HybridCarouselCardContainerModel;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.hybrid_carousel.model.HybridCarouselModel;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.hybrid_carousel.response.HybridCarousel;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.hybrid_carousel.response.HybridCarouselCardContainer;
import java.util.ArrayList;
import java.util.List;

public class HybridCarouselMapper {

    private final TouchpointItemContentMapper contentMapper;

    /**
     * Constructor
     *
     * @param contentMapper mapper to get a item model from json.
     */
    public HybridCarouselMapper(final TouchpointItemContentMapper contentMapper) {
        this.contentMapper = contentMapper;
    }

    /**
     * Convert a response to a model.
     *
     * @param response the dto to convert.
     * @return the model instance.
     */
    public HybridCarouselModel mapResponse(final HybridCarousel response) {
        return getModelFromResponse(response);
    }

    private HybridCarouselModel getModelFromResponse(final HybridCarousel response) {
        return new HybridCarouselModel(getItems(response.getItems()));
    }

    private List<HybridCarouselCardContainerModel> getItems(final List<HybridCarouselCardContainer> touchpointItems) {
        final List<HybridCarouselCardContainerModel> items = new ArrayList<>();
        if (touchpointItems != null) {
            for (final HybridCarouselCardContainer itemResponse : touchpointItems) {
                HybridCarouselCardContainerModel card =
                    contentMapper.map(itemResponse.getType(), itemResponse.getLink(),
                        itemResponse.getTracking(), itemResponse.getContent());
                if (card != null) {
                    items.add(card);
                }
            }
        }
        return items;
    }
}
