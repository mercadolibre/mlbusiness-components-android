package com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.hybrid_carousel.test.default_card;

import android.support.annotation.Keep;
import java.io.Serializable;

@Keep
public class CarouselBottomInfo implements Serializable {

    private final String icon;
    private final String label;
    private final CarouselBottomInfoFormat format;

    public CarouselBottomInfo(final String icon, final String label,
        final CarouselBottomInfoFormat format) {
        this.icon = icon;
        this.label = label;
        this.format = format;
    }
}
