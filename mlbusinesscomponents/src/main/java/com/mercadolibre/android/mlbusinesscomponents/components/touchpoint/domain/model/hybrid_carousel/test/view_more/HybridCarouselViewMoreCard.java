package com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.hybrid_carousel.test.view_more;

import android.support.annotation.Keep;
import java.io.Serializable;

@Keep
public class HybridCarouselViewMoreCard implements Serializable {

    private final String mainImage;
    private final ViewMoreMainTitle mainTitle;

    public HybridCarouselViewMoreCard(final String mainImage,
        final ViewMoreMainTitle mainTitle) {
        this.mainImage = mainImage;
        this.mainTitle = mainTitle;
    }
}
