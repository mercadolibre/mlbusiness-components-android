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
    private final boolean alphaAnimation;
    private final boolean scaleAnimation;
    private final boolean pressAnimation;
    private final List<CoverCard> items;

    public CoverCarousel(final Header header,
        final boolean alphaAnimation, final boolean scaleAnimation,
        final boolean pressAnimation, final List<CoverCard> items) {
        this.header = header;
        this.alphaAnimation = alphaAnimation;
        this.scaleAnimation = scaleAnimation;
        this.pressAnimation = pressAnimation;
        this.items = items;
    }

    @Override
    public HeaderInterface getHeader() {
        return header;
    }

    @Override
    public boolean getAlphaAnimation() {
        return alphaAnimation;
    }

    @Override
    public boolean getScaleAnimation() {
        return scaleAnimation;
    }

    @Override
    public boolean getPressAnimation() {
        return pressAnimation;
    }

    @Override
    public List<CoverCardInterface> getItems() {
        return new ArrayList<>(items);
    }
}
