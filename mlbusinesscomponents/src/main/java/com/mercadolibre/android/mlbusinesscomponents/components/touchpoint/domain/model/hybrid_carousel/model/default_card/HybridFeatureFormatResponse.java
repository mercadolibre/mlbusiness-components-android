package com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.hybrid_carousel.model.default_card;

import androidx.annotation.Keep;
import com.mercadolibre.android.mlbusinesscomponents.components.pill.model.FeatureFormatResponseInterface;
import java.io.Serializable;

@Keep
public class HybridFeatureFormatResponse implements Serializable, FeatureFormatResponseInterface {

    private final String backgroundColor;
    private final String textColor;

    /**
     * Constructor
     *
     * @param backgroundColor the feature background color.
     * @param textColor the feature text color.
     */
    public HybridFeatureFormatResponse(final String backgroundColor, final String textColor) {
        this.backgroundColor = backgroundColor;
        this.textColor = textColor;
    }

    @Override
    public String getBackgroundColor() {
        return backgroundColor;
    }

    @Override
    public String getTextColor() {
        return textColor;
    }

}
