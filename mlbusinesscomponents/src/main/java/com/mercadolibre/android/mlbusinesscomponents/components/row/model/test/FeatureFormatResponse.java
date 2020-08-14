package com.mercadolibre.android.mlbusinesscomponents.components.row.model.test;

import androidx.annotation.Keep;
import com.mercadolibre.android.mlbusinesscomponents.components.pill.model.FeatureFormatResponseInterface;
import java.io.Serializable;

@Keep
public class FeatureFormatResponse implements Serializable, FeatureFormatResponseInterface {

    private final String backgroundColor;
    private final String textColor;

    /**
     * Constructor
     *
     * @param backgroundColor the feature background color.
     * @param textColor the feature text color.
     */
    public FeatureFormatResponse(final String backgroundColor, final String textColor) {
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
