package com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.cover_carousel.response;

import androidx.annotation.Keep;
import com.mercadolibre.android.mlbusinesscomponents.components.common.header.Header;
import com.mercadolibre.android.mlbusinesscomponents.components.common.header.HeaderInterface;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.TouchpointContent;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.cover_carousel.model.cover_card.CoverCard;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.cover_carousel.model.cover_card.CoverCardInterface;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Keep
public class CoverCarousel implements CoverCarouselInterface, TouchpointContent, Serializable {

    private final Header header;
    private final CarouselAnimation carouselAnimation;
    private final List<CoverCard> items;

    public CoverCarousel(final Header header,
        final boolean alphaAnimation, final boolean scaleAnimation,
        final boolean pressAnimation,
        final CarouselAnimation carouselAnimation,
        final List<CoverCard> items) {
        this.header = header;
        this.carouselAnimation = carouselAnimation;
        this.items = items;
    }

    @Override
    public HeaderInterface getHeader() {
        return header;
    }

    @Override
    public CarouselAnimationInterface getCarouselAnimation() {
        return carouselAnimation;
    }

    @Override
    public List<CoverCardInterface> getItems() {
        return new ArrayList<>(items);
    }
}
