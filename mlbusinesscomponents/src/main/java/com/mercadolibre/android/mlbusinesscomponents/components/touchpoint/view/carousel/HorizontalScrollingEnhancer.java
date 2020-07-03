package com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.view.carousel;

import android.support.v7.widget.RecyclerView;

public interface HorizontalScrollingEnhancer {

    default void enhanceHorizontalScroll(final RecyclerView recyclerView) {
        //no op..
    }
}
