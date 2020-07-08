package com.mercadolibre.android.mlbusinesscomponents.common;

import android.view.View;

public interface TouchpointImageLoader {

    void getImage(final String image, View view, final ImageCallback callback);
}
