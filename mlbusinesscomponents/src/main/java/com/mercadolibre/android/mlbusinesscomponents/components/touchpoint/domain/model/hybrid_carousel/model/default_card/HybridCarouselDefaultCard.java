package com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.hybrid_carousel.model.default_card;

import android.support.annotation.Keep;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.hybrid_carousel.TouchpointItemType;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.hybrid_carousel.model.TouchpointItem;
import java.io.Serializable;

@Keep
public class HybridCarouselDefaultCard implements Serializable, TouchpointItem {

    private final String topImage;
    private final String topImageAccessory;
    private final String middleTitle;
    private final String middleSubtitle;
    private final String bottomTopLabel;
    private final String bottomPrimaryLabel;
    private final String bottomSecondaryLabel;
    private final String bottomLabelStatus;
    private final HybridPillResponse bottomInfo;

    private int position;

    public HybridCarouselDefaultCard(final String topImage, final String topImageAccessory, final String middleTitle, final String middleSubtitle,
        final String bottomTopLabel, final String bottomPrimaryLabel, final String bottomSecondaryLabel, final String bottomLabelStatus,
        final HybridPillResponse bottomInfo) {
        this.topImage = topImage;
        this.topImageAccessory = topImageAccessory;
        this.middleTitle = middleTitle;
        this.middleSubtitle = middleSubtitle;
        this.bottomTopLabel = bottomTopLabel;
        this.bottomPrimaryLabel = bottomPrimaryLabel;
        this.bottomSecondaryLabel = bottomSecondaryLabel;
        this.bottomLabelStatus = bottomLabelStatus;
        this.bottomInfo = bottomInfo;
    }

    public String getTopImage() {
        return topImage;
    }

    public String getTopImageAccessory() {
        return topImageAccessory;
    }

    public String getMiddleTitle() {
        return middleTitle;
    }

    public String getMiddleSubtitle() {
        return middleSubtitle;
    }

    public String getBottomTopLabel() {
        return bottomTopLabel;
    }

    public String getBottomPrimaryLabel() {
        return bottomPrimaryLabel;
    }

    public String getBottomSecondaryLabel() {
        return bottomSecondaryLabel;
    }

    public String getBottomLabelStatus() {
        return bottomLabelStatus;
    }

    public HybridPillResponse getBottomInfo() {
        return bottomInfo;
    }

    @Override
    public int getItemType() {
        return TouchpointItemType.DEFAULT.ordinal();
    }

    @Override
    public void setAdapterPosition(final int position) {
        this.position = position;
    }

    @Override
    public int getAdapterPosition() {
        return position;
    }

    @Override
    public double getHeight() {
        boolean hasTopLabel = false, hasMainLabel = false, hasTitle = false, hasSubtitle = false, hasBottomInfo = false;

        if (bottomTopLabel != null && !bottomTopLabel.isEmpty()) {
            hasTopLabel = true;
        }
        if (bottomPrimaryLabel != null && !bottomPrimaryLabel.isEmpty()) {
            hasMainLabel = true;
        }
        if (middleTitle != null && !middleTitle.isEmpty()) {
            hasTitle = true;
        }
        if (middleSubtitle != null && !middleSubtitle.isEmpty()) {
            hasSubtitle = true;
        }
        if (bottomInfo != null) {
            hasBottomInfo = true;
        }

        double spaceToMainLabel = 100, topLabelHeight = 14, mainLabelHeight = 28, titleHeight = 23, subtitleHeight = 13, spaceToBottom = 13,
            spaceBottomInfo = 10;

        double maxItemHeight = spaceToMainLabel
            + (hasTopLabel ? topLabelHeight : 0.0)
            + (hasMainLabel ? mainLabelHeight : 0.0)
            + (hasTitle ? titleHeight : 0.0)
            + (hasSubtitle ? subtitleHeight : 0.0)
            + (hasBottomInfo ? spaceBottomInfo : 0.0)
            + spaceToBottom;

        return maxItemHeight;
    }
}
