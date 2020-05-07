package com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.carousel;

import android.support.annotation.Keep;
import java.io.Serializable;

@Keep
public class CarouselPill implements Serializable {

    private static final long serialVersionUID = 9141249547623194230L;

    private final String icon;
    private final String label;
    private final CarouselFormat format;

    /**
     * Constructor
     *
     * @param icon The icon
     * @param label The text
     * @param format The background and text color
     */
    public CarouselPill(final String icon, final String label,
        final CarouselFormat format) {
        this.icon = icon;
        this.label = label;
        this.format = format;
    }

    public String getIcon() {
        return icon;
    }

    public String getLabel() {
        return label;
    }

    public CarouselFormat getFormat() {
        return format;
    }

    //CPD-OFF
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || !getClass().equals(o.getClass())) {
            return false;
        }

        final CarouselPill that = (CarouselPill) o;

        if (getIcon() == null ? that.getIcon() != null : !getIcon().equals(that.getIcon())) {
            return false;
        }
        if (getLabel() == null ? that.getLabel() != null : !getLabel().equals(that.getLabel())) {
            return false;
        }
        return getFormat() == null ? that.getFormat() == null
            : getFormat().equals(that.getFormat());
    }

    @Override
    @SuppressWarnings("checkstyle:magicnumber")
    public int hashCode() {
        int result = getIcon() == null ? 0 : getIcon().hashCode();
        result = 31 * result + (getLabel() == null ? 0 : getLabel().hashCode());
        result = 31 * result + (getFormat() == null ? 0 : getFormat().hashCode());
        return result;
    }
}
