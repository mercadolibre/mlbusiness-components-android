package com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.carousel;

import android.support.annotation.Keep;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.TouchpointContent;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.view.carousel.HeightCalculatorDelegate;
import java.io.Serializable;
import java.util.List;

@Keep
public class Carousel implements TouchpointContent, Serializable, HeightCalculatorDelegate {

    private static final long serialVersionUID = 7998836654820333977L;

    private final List<CarouselCardTouchpoint> items;

    /**
     * Constructor
     *
     * @param items A list of {@link CarouselCardTouchpoint} cards
     */
    public Carousel(final List<CarouselCardTouchpoint> items) {
        this.items = items;
    }

    public List<CarouselCardTouchpoint> getItems() {
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

        final Carousel that = (Carousel) o;

        return getItems() != null ? getItems().equals(that.getItems()) : that.getItems() == null;
    }

    @Override
    public int hashCode() {
        return getItems() != null ? getItems().hashCode() : 0;
    }

    @Override
    public double getFixedCardHeight() {
        double maxHeight = 0;
        for (final CarouselCardTouchpoint carouselCard: items) {
            if(maxHeight < carouselCard.getHeight()) {
                maxHeight = carouselCard.getHeight();
            }
        }
        return maxHeight;
    }
}
