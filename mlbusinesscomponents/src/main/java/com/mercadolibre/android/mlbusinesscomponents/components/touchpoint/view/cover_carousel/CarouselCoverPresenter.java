package com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.view.cover_carousel;

import com.mercadolibre.android.mlbusinesscomponents.components.common.action.ActionInterface;
import com.mercadolibre.android.mlbusinesscomponents.components.common.header.HeaderInterface;
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

        setCarouselHeader(response.getHeader());
        setItemsList(response.getItems());
    }

    private void setCarouselHeader(final HeaderInterface header) {
        if (header.getTitle() == null) {
            view.hideHeaderContainer();
            return;
        }
        view.setHeaderTitle(header.getTitle());

        setHeaderAction(header.getAction());
    }

    private void setHeaderAction(final ActionInterface action) {
        if (action.getActionTitle() == null || action.getActionLink() == null) {
            view.hideHeaderAction();
            return;
        }
        view.setHeaderActionTitle(action.getActionTitle());
        view.setHeaderActionClickListener(action.getActionLink());
    }

    private void setItemsList(final List<CoverCardInterface> items) {
        if (items == null || items.isEmpty()) {
            view.setVisibilityGone();
            return;
        }

        view.setItemsList(items);
    }
}
