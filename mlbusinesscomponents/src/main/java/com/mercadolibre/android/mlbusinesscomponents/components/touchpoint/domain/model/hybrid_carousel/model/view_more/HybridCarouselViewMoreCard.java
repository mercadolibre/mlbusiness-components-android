package com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.hybrid_carousel.model.view_more;

import android.support.annotation.Keep;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.hybrid_carousel.TouchpointItemType;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.hybrid_carousel.model.TouchpointItem;
import java.io.Serializable;

@Keep
public class HybridCarouselViewMoreCard implements Serializable, TouchpointItem {

    private final String mainImage;
    private final ViewMoreMainTitle mainTitle;

    private int position;

    public HybridCarouselViewMoreCard(final String mainImage,
        final ViewMoreMainTitle mainTitle) {
        this.mainImage = mainImage;
        this.mainTitle = mainTitle;
    }

    public String getMainImage() {
        return mainImage;
    }

    public ViewMoreMainTitle getMainTitle() {
        return mainTitle;
    }

    @Override
    public int getItemType() {
        return TouchpointItemType.VIEW_MORE.ordinal();
    }

    @Override
    public void setAdapterPosition(final int position) {
        this.position = position;
    }

    @Override
    public double getHeight() {
        boolean  hasMainTitle = false;

        if (mainTitle != null && mainTitle.isValid()) {
            hasMainTitle = true;
        }

        double spaceToMainLabel = 100, topLabelHeight = 14, spaceToBottom = 13;

        double maxItemHeight = spaceToMainLabel
            + (hasMainTitle ? topLabelHeight : 0.0)
            + spaceToBottom;

        return maxItemHeight;
    }

    @Override
    public int getAdapterPosition() {
        return position;
    }
}
