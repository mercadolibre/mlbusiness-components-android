package com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.hybrid_carousel.test.default_card;

import android.support.annotation.Keep;
import java.io.Serializable;

@Keep
public class CarouselBottomInfoFormat implements Serializable {

    private final String textColor;
    private final String backgroundColor;

    public CarouselBottomInfoFormat(final String textColor, final String backgroundColor) {
        this.textColor = textColor;
        this.backgroundColor = backgroundColor;
    }
}
