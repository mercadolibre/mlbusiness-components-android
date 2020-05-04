package com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.carousel;

import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.TouchpointContent;
import java.io.Serializable;
import java.util.List;

public class CarouselResponse implements TouchpointContent, Serializable {

    private static final long serialVersionUID = 7998836654820333977L;

    private final List<CarouselCardResponse> items;

    /**
     * Constructor
     * @param items A list of {@link CarouselCardResponse} cards
     */
    public CarouselResponse(final List<CarouselCardResponse> items) {
        this.items = items;
    }

    public List<CarouselCardResponse> getItems() {
        return items;
    }

    public boolean isValid() {
        return items != null && !items.isEmpty();
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || !getClass().equals(o.getClass())) {
            return false;
        }

        final CarouselResponse that = (CarouselResponse) o;

        return getItems() != null ? getItems().equals(that.getItems()) : that.getItems() == null;
    }

    @Override
    public int hashCode() {
        return getItems() != null ? getItems().hashCode() : 0;
    }
}
