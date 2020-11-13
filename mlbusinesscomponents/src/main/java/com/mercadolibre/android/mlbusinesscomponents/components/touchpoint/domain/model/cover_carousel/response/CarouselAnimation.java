package com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.cover_carousel.response;

public class CarouselAnimation implements CarouselAnimationInterface {

    private final boolean alphaAnimation;
    private final boolean scaleAnimation;
    private final boolean pressAnimation;

    public CarouselAnimation(final boolean alphaAnimation, final boolean scaleAnimation, final boolean pressAnimation) {
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
