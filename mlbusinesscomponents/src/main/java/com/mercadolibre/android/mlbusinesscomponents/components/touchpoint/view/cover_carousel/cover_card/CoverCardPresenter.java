package com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.view.cover_carousel.cover_card;

import com.mercadolibre.android.mlbusinesscomponents.components.row.model.TouchpointRowItemInterface;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.cover_carousel.model.cover_card.CoverCardInterfaceModel;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.tracking.print.TouchpointTracking;

public class CoverCardPresenter {

    public static final String CLOSED = "closed";
    private String link =  null;
    private TouchpointTracking tracking = null;

    /**
     * Binds the view.
     *
     * @param model the data to bind.
     */
    public void bindView(final CoverCardInterfaceModel model, final CoverCardInterfaceView view) {

        if (model == null) {
            view.showSkeleton();
            return;
        }

        view.hideSkeleton();
        setRow(model.getContent(), view);
        setCoverImage(model.getContent().getCover(), view);
        setOnClick(model.getLink(), view);
        setTracking(model.getTracking(), view);
        setTopImageStatus(model.getContent().getTopImageStatus(), view);
    }

    private void setTopImageStatus(final String topImageStatus, final CoverCardInterfaceView view) {
        if (topImageStatus != null  && CLOSED.equals(topImageStatus.toLowerCase())) {
            view.setTopImageToClosedtStatus();
        } else {
            view.setTopImageToDefaultStatus();
        }
    }

    private void setRow(final TouchpointRowItemInterface description, final CoverCardInterfaceView view) {
        view.setRow(description);
    }

    private void setCoverImage(final String cover, final CoverCardInterfaceView view) {
        view.setCoverImage(cover);
    }

    private void setOnClick(final String link, final CoverCardInterfaceView view) {
        if (link != null && !link.isEmpty()) {
            view.setOnClick(link);
            this.link = link;
        } else {
            view.dismissClickable();
        }
    }

    private void setTracking(final TouchpointTracking tracking, final CoverCardInterfaceView view) {
        this.tracking = tracking;
        view.setTracking(tracking);
    }

    public void setPressAnimationWithLink(final CoverCardInterfaceView view) {
        if (link != null && !link.isEmpty()) {
            view.setOnClickListenerWithAnimationAndLink(link, tracking);
        } else {
            view.dismissClickable();
        }
    }
}
