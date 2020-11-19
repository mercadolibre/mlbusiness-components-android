package com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.view.cover_carousel.cover_card;

import com.mercadolibre.android.mlbusinesscomponents.components.row.model.TouchpointRowItemInterface;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.cover_carousel.model.cover_card.CoverCardInterface;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.tracking.print.TouchpointTracking;

public class CoverCardPresenter {

    private final CoverCardView view;

    /**
     * Constructor
     *
     * @param view the CoverView
     */
    public CoverCardPresenter(final CoverCardView view) {
        this.view = view;
    }

    /**
     * Binds the view.
     *
     * @param model the data to bind.
     */
    public void bindView(final CoverCardInterface model) {

        if (model == null) {
            view.showSkeleton();
            return;
        }

        setRow(model.getContent());
        setCoverImage(model.getContent().getCover());
        setOnClick(model.getLink(), model.getTracking());

        view.showView();
    }

    private void setRow(final TouchpointRowItemInterface description) {
        view.setRow(description);
    }

    private void setCoverImage(final String cover) {
        view.setCoverImage(cover);
    }

    private void setOnClick(final String link, final TouchpointTracking tracking) {
        if (link != null && !link.isEmpty()) {
            view.setOnClick(link, tracking);
        } else {
            view.dismissClickable();
        }
    }
}
