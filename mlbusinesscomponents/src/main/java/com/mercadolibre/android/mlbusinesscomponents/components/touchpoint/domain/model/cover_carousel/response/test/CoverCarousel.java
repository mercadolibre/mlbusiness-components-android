package com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.cover_carousel.response.test;

import androidx.annotation.Keep;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.TouchpointContent;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.cover_carousel.model.test.CoverCard;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.cover_carousel.model.cover_card.CoverCardInterface;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.cover_carousel.response.CarouselAnimationInterface;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.cover_carousel.response.CoverCarouselInterface;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Keep
public class CoverCarousel implements CoverCarouselInterface, TouchpointContent, Serializable {

    private final String link;
    private final String label;
    private final String title;
    private final CarouselAnimation carouselAnimation;
    private final List<CoverCard> items;

    public CoverCarousel(final String link, final String label, final String title,
        final CarouselAnimation carouselAnimation,
        final List<CoverCard> items) {
        this.link = link;
        this.label = label;
        this.title = title;
        this.carouselAnimation = carouselAnimation;
        this.items = items;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getLabel() {
        return label;
    }

    @Override
    public String getLink() {
        return link;
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
