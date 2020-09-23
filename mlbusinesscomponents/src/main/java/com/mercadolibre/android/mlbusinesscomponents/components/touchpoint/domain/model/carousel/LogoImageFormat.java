package com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.carousel;

import androidx.annotation.Keep;

@Keep
public class LogoImageFormat {
    private final boolean overlay;

    public LogoImageFormat(final boolean overlay) {
        this.overlay = overlay;
    }

    public boolean isOverlay() {
        return overlay;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof LogoImageFormat)) {
            return false;
        }

        final LogoImageFormat that = (LogoImageFormat) o;

        return overlay == that.overlay;
    }

    @Override
    public int hashCode() {
        return (overlay ? 1 : 0);
    }
}
