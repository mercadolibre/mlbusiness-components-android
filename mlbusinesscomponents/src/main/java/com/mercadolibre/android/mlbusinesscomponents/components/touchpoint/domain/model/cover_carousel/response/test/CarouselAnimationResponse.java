package com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.cover_carousel.response.test;

import androidx.annotation.Keep;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.cover_carousel.response.CarouselAnimationInterfaceModel;

@Keep
public class CarouselAnimationResponse implements CarouselAnimationInterfaceModel {

    private final boolean alphaAnimation;
    private final boolean scaleAnimation;
    private final boolean pressAnimation;

    public CarouselAnimationResponse(final boolean alphaAnimation, final boolean scaleAnimation, final boolean pressAnimation) {
        this.alphaAnimation = alphaAnimation;
        this.scaleAnimation = scaleAnimation;
        this.pressAnimation = pressAnimation;
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
}
