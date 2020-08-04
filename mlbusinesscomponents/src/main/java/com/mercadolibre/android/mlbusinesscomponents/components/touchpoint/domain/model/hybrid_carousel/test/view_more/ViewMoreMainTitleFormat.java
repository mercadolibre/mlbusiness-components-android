package com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.hybrid_carousel.test.view_more;

import android.support.annotation.Keep;
import java.io.Serializable;

@Keep
public class ViewMoreMainTitleFormat implements Serializable {

    private final String textColor;
    private final String backgroundColor;

    public ViewMoreMainTitleFormat(final String textColor, final String backgroundColor) {
        this.textColor = textColor;
        this.backgroundColor = backgroundColor;
    }
}
