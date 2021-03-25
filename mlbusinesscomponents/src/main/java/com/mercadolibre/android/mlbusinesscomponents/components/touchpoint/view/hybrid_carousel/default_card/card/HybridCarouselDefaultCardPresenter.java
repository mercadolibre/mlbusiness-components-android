package com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.view.hybrid_carousel.default_card.card;

import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.hybrid_carousel.model.HybridCarouselCardContainerModel;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.hybrid_carousel.model.default_card.HybridCarouselDefaultCard;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.hybrid_carousel.model.default_card.HybridPillResponse;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.tracking.print.TouchpointTracking;

public class HybridCarouselDefaultCardPresenter {
    private static final String BLOCKED = "blocked";
    private static final String CLOSED = "closed";

    private final HybridCarouselDefaultCardView view;

    /**
     * Constructor
     *
     * @param view
     */
    public HybridCarouselDefaultCardPresenter(final HybridCarouselDefaultCardView view) {
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
        setTopImageStatus(data.getTopImageStatus());
        setMiddleTitle(data.getMiddleTitle());
        setMiddleSubtitle(data.getMiddleSubtitle());
        setBottomTopLabel(data.getBottomTopLabel());
        setBottomPrimaryLabel(data.getBottomPrimaryLabel());
        setBottomSecondaryLabel(data.getBottomSecondaryLabel());
        setBottomInfo(data.getBottomInfo());
        setRightLabelStatus(data.getBottomLabelStatus());
        view.showView();
    }


    private void setTopImageStatus(final String topImageStatus) {
        if (CLOSED.equals(topImageStatus)) {
            view.setTopImageToClosedStatus();
        } else {
            view.setTopImageToDefaultStatus();
        }
    }

    private void setRightLabelStatus(final String bottomLabelStatus) {
        if (bottomLabelStatus == null || bottomLabelStatus.isEmpty()) {
            view.setBottomLabelsToDefaultStatus();
            return;
        }

        switch (bottomLabelStatus.toLowerCase()) {
        case BLOCKED:
            view.setBottomLabelsToBlockedStatus();
            break;
        case CLOSED:
            view.setBottomLabelsToClosedStatus();
            break;
        default:
            view.setBottomLabelsToDefaultStatus();
        }
    }

    private void setBottomInfo(final HybridPillResponse bottomInfo) {
        if (bottomInfo != null) {
            view.showRightBottomInfo(bottomInfo);
        } else {
            view.hideRightBottomInfo();
        }
    }

    private void setBottomSecondaryLabel(final String bottomSecondaryLabel) {
        if (bottomSecondaryLabel != null && !bottomSecondaryLabel.isEmpty()) {
            view.setBottomSecondaryLabel(bottomSecondaryLabel);
        } else {
            view.hideBottomSecondaryLabel();
        }
    }

    private void setBottomPrimaryLabel(final String bottomPrimaryLabel) {
        if (bottomPrimaryLabel != null && !bottomPrimaryLabel.isEmpty()) {
            view.setBottomPrimaryLabel(bottomPrimaryLabel);
        } else {
            view.hideBottomPrimaryLabel();
        }
    }

    private void setBottomTopLabel(final String bottomTopLabel) {
        if (bottomTopLabel != null && !bottomTopLabel.isEmpty()) {
            view.setBottomTopLabel(bottomTopLabel);
        } else {
            view.hideBottomTopLabel();
        }
    }

    private void setMiddleSubtitle(final String middleSubtitle) {
        if (middleSubtitle != null && !middleSubtitle.isEmpty()) {
            view.setMiddleSubtitle(middleSubtitle);
        } else {
            view.hideMiddleSubtitle();
        }
    }

    private void setMiddleTitle(final String middleTitle) {
        if (middleTitle != null && !middleTitle.isEmpty()) {
            view.setMiddleTitle(middleTitle);
        } else {
            view.hideMiddleTitle();
        }
    }

    private void setOnClick(final String link, final TouchpointTracking tracking) {
        if (link != null && !link.isEmpty()) {
            view.setOnClick(link, tracking);
        } else {
            view.dismissClickable();
        }
    }

    private void setImage(final String topImage, final String topImageAccessory) {
        if (topImage != null && !topImage.isEmpty()) {
            view.setImage(topImage);
            if (topImageAccessory != null && !topImageAccessory.isEmpty()) {
                view.setImageAccessory(topImageAccessory);
            } else {
                view.hideImageAccessory();
            }
        } else {
            view.hideTopImage();
        }
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
