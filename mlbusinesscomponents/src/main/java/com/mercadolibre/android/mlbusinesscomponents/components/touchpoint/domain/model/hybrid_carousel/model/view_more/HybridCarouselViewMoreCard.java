package com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.hybrid_carousel.model.view_more;

import android.support.annotation.Keep;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.hybrid_carousel.TouchpointItemType;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.hybrid_carousel.model.TouchpointItem;
import java.io.Serializable;

@Keep
public class HybridCarouselViewMoreCard implements Serializable, TouchpointItem {

    private final String mainImage;
    private final ViewMoreMainTitle mainTitle;

    public HybridCarouselViewMoreCard(final String mainImage,
        final ViewMoreMainTitle mainTitle) {
        this.mainImage = mainImage;
        this.mainTitle = mainTitle;
    }

    @Override
    public int getItemType() {
        return TouchpointItemType.VIEW_MORE.ordinal();
    }
}
