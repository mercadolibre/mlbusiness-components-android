package com.mercadolibre.android.mlbusinesscomponents.common;

import android.view.View;

public class DefaultImageLoader implements TouchpointImageLoader {

    @Override
    public void getImage(final String image, final View view, final ImageCallback loadDefault) {
        loadDefault.call(true);
    }
}


