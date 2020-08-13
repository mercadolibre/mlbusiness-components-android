package com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.hybrid_carousel.model.default_card;

import android.support.annotation.Keep;
import com.mercadolibre.android.mlbusinesscomponents.components.pill.model.FeatureFormatResponseInterface;
import com.mercadolibre.android.mlbusinesscomponents.components.pill.model.PillResponseInterface;
import java.io.Serializable;

@Keep
public class HybridPillResponse implements Serializable, PillResponseInterface {

    private final String icon;
    private final String label;
    private final HybridFeatureFormatResponse format;

    /**
     * Constructor
     *
     * @param icon The icon
     * @param label The text
     * @param format The background and text color
     */
    public HybridPillResponse(final String icon, final String label,
        final HybridFeatureFormatResponse format) {
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
