package com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.view.carousel.card;

import android.view.View;
import com.mercadolibre.android.mlbusinesscomponents.common.DefaultImageLoader;
import com.mercadolibre.android.mlbusinesscomponents.common.ImageCallback;
import com.mercadolibre.android.mlbusinesscomponents.common.TouchpointImageLoader;

public final class AssetLoader {

    private static TouchpointImageLoader strategy;

    private AssetLoader() {
        //do nothing.
    }

    public static void setStrategy(final TouchpointImageLoader strategy) {
        AssetLoader.strategy = strategy;
    }


    public static void getImage(final String image, final View view, final ImageCallback loadDefault ) {
        if (strategy == null) {
            setStrategy(new DefaultImageLoader());
        }
        strategy.getImage(image, view ,loadDefault);
    }
}