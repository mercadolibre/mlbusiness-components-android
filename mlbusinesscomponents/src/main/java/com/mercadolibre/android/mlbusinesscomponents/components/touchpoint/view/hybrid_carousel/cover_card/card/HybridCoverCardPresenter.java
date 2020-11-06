package com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.view.hybrid_carousel.cover_card.card;

import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.hybrid_carousel.model.HybridCarouselCardContainerModel;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.hybrid_carousel.model.default_card.HybridCarouselDefaultCard;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.hybrid_carousel.model.default_card.HybridPillResponse;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.tracking.print.TouchpointTracking;

public class HybridCoverCardPresenter {
    private static final String BLOCKED = "blocked";

    private final HybridCoverCardView view;

    /**
     * Constructor
     *
     * @param view
     */
    public HybridCoverCardPresenter(final HybridCoverCardView view) {
        this.view = view;
    }

    /**
     * Binds the view.
     *
     * @param model the data to bind.
     */
    public void bindView(final HybridCarouselCardContainerModel model) {
        final HybridCarouselDefaultCard data = setData(model);

        if (model == null || data == null) {
            hideView();
            return;
        }

        setOnClick(model.getLink(), model.getTracking());
        setImage(data.getTopImage(), data.getTopImageAccessory());
        setMiddleTitle(data.getMiddleTitle());
        setMiddleSubtitle(data.getMiddleSubtitle());
        setBottomTopLabel(data.getBottomTopLabel());
        setBottomPrimaryLabel(data.getBottomPrimaryLabel());
        setBottomSecondaryLabel(data.getBottomSecondaryLabel());
        setBottomInfo(data.getBottomInfo());
        setRightLabelStatus(data.getBottomLabelStatus());

        view.showView();
    }

    private void setRightLabelStatus(final String bottomLabelStatus) {
        if (bottomLabelStatus == null || bottomLabelStatus.isEmpty()) {
            return;
        }

    }

    private void setBottomInfo(final HybridPillResponse bottomInfo) {


    }

    private void setBottomSecondaryLabel(final String bottomSecondaryLabel) {

    }

    private void setBottomPrimaryLabel(final String bottomPrimaryLabel) {

    }

    private void setBottomTopLabel(final String bottomTopLabel) {

    }

    private void setMiddleSubtitle(final String middleSubtitle) {

    }

    private void setMiddleTitle(final String middleTitle) {

    }

    private void setOnClick(final String link, final TouchpointTracking tracking) {
        if (link != null && !link.isEmpty()) {
            view.setOnClick(link, tracking);
        } else {
            view.dismissClickable();
        }
    }

    private void setImage(final String topImage, final String topImageAccessory) {

    }

    private HybridCarouselDefaultCard setData(final HybridCarouselCardContainerModel model) {
        HybridCarouselDefaultCard card = null;

        try {
            card = (HybridCarouselDefaultCard) model.getContent();
        } catch (Exception e) {
            // no op..
        }

        return card;
    }

    private void hideView() {
        view.hideView();
    }
}
