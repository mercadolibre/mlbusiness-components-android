package com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.cover_carousel.response.test;

import androidx.annotation.Keep;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.cover_carousel.model.test.CoverCard;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.cover_carousel.model.cover_card.CoverCardInterfaceModel;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.cover_carousel.response.CarouselAnimationInterfaceModel;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.cover_carousel.response.CoverCarouselInterfaceModel;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Keep
public class CoverCarouselResponse implements CoverCarouselInterfaceModel, Serializable {

    private static final long serialVersionUID = 5514383671266112753L;

    private final String link;
    private final String label;
    private final String title;
    private final CarouselAnimationResponse carouselAnimation;
    private final List<CoverCard> items;

    public CoverCarouselResponse(final String link, final String label, final String title,
        final CarouselAnimationResponse carouselAnimation,
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
    public CarouselAnimationInterfaceModel getCarouselAnimation() {
        return carouselAnimation;
    }

    @Override
    public List<CoverCardInterfaceModel> getItems() {
        return new ArrayList<>(items);
    }
}
