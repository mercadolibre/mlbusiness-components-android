package com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.view.cover_carousel;

import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.cover_carousel.model.cover_card.CoverCardInterface;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.cover_carousel.response.CoverCarouselInterface;
import java.util.List;

public class CarouselCoverPresenter {

    private final CoverCarouselViewInterface view;

    /**
     * Constructor
     *
     * @param view view to interact with
     */
    public CarouselCoverPresenter(final CoverCarouselViewInterface view) {
        this.view = view;
    }

    /**
     * Map response to model.
     *
     * @param response to map.
     */
    public void mapResponse(final CoverCarouselInterface response) {

        if (response == null) {
            view.setVisibilityGone();
            return;
        }

        setItemsList(response.getItems());
    }

    private void setItemsList(final List<CoverCardInterface> items) {
        if (items == null || items.isEmpty()) {
            view.setVisibilityGone();
            return;
        }

        view.setItemsList(items);
    }
}
