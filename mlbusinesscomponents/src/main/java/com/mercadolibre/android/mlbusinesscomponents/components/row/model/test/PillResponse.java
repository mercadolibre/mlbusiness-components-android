package com.mercadolibre.android.mlbusinesscomponents.components.row.model.test;

import androidx.annotation.Keep;
import com.mercadolibre.android.mlbusinesscomponents.components.pill.model.FeatureFormatResponseInterface;
import com.mercadolibre.android.mlbusinesscomponents.components.pill.model.PillResponseInterface;
import java.io.Serializable;

@Keep
public class PillResponse implements Serializable, PillResponseInterface {

    private final String icon;
    private final String label;
    private final FeatureFormatResponse format;

    /**
     * Constructor
     *
     * @param icon The icon
     * @param label The text
     * @param format The background and text color
     */
    public PillResponse(final String icon, final String label,
        final FeatureFormatResponse format) {
        this.icon = icon;
        this.label = label;
        this.format = format;
    }

    @Override
    public String getIcon() {
        return icon;
    }

    @Override
    public String getLabel() {
        return label;
    }

    @Override
    public FeatureFormatResponseInterface getFormat() {
        return format;
    }

}
