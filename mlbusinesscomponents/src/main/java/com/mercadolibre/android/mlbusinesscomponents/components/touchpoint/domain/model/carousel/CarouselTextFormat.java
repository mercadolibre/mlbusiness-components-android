package com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.carousel;

import java.io.Serializable;

public class CarouselTextFormat implements Serializable {
    private final int size;
    private final String color;
    private final String weight;

    public CarouselTextFormat(final int size, final String color, final String weight) {
        this.size = size;
        this.color = color;
        this.weight = weight;
    }

    public int getSize() {
        return size;
    }

    public String getColor() {
        return color;
    }

    public String getWeight() {
        return weight;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CarouselTextFormat)) {
            return false;
        }

        final CarouselTextFormat that = (CarouselTextFormat) o;

        if (size != that.size) {
            return false;
        }
        if (color != null ? !color.equals(that.color) : that.color != null) {
            return false;
        }
        return weight != null ? weight.equals(that.weight) : that.weight == null;
    }

    @Override
    public int hashCode() {
        int result = size;
        result = 31 * result + (color != null ? color.hashCode() : 0);
        result = 31 * result + (weight != null ? weight.hashCode() : 0);
        return result;
    }
}
