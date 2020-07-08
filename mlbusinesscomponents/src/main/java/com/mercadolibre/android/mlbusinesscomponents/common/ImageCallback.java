package com.mercadolibre.android.mlbusinesscomponents.common;

public interface ImageCallback {
    /**
     * callback to set the image.
     *
     * @param loadDefault whether to set the icon with the default option or not
     */
    void call(final boolean loadDefault);
}
