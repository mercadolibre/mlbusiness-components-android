package com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.hybrid_carousel.model.view_more;

import android.support.annotation.Keep;
import java.io.Serializable;

@Keep
public class ViewMoreMainTitle implements Serializable {

    private final String label;
    private final ViewMoreMainTitleFormat format;

    public ViewMoreMainTitle(final String label,
        final ViewMoreMainTitleFormat format) {
        this.label = label;
        this.format = format;
    }
}
