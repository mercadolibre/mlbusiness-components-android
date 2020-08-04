package com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.hybrid_carousel.test.default_card;

import android.support.annotation.Keep;
import java.io.Serializable;

@Keep
public class HybridCarouselDefaultCard implements Serializable {

    private final String topImage;
    private final String topImageAccessory;
    private final String middleTitle;
    private final String middleSubtitle;
    private final String bottomTopLabel;
    private final String bottomPrimaryLabel;
    private final String bottomSecondaryLabel;
    private final String bottomLabelStatus;
    private final CarouselBottomInfo bottomInfo;

    public HybridCarouselDefaultCard(final String topImage, final String topImageAccessory, final String middleTitle, final String middleSubtitle,
        final String bottomTopLabel, final String bottomPrimaryLabel, final String bottomSecondaryLabel, final String bottomLabelStatus,
        final CarouselBottomInfo bottomInfo) {
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
}
