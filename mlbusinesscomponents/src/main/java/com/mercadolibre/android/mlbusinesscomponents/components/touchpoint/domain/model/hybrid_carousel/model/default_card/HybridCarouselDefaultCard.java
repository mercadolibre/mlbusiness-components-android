package com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.hybrid_carousel.model.default_card;

import android.support.annotation.Keep;
import com.mercadolibre.android.mlbusinesscomponents.components.row.model.test.PillResponse;
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
    private final PillResponse bottomInfo;

    public HybridCarouselDefaultCard(final String topImage, final String topImageAccessory, final String middleTitle, final String middleSubtitle,
        final String bottomTopLabel, final String bottomPrimaryLabel, final String bottomSecondaryLabel, final String bottomLabelStatus,
        final PillResponse bottomInfo) {
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

    @Override
    public int getItemType() {
        return TouchpointItemType.DEFAULT.ordinal();
    }
}
