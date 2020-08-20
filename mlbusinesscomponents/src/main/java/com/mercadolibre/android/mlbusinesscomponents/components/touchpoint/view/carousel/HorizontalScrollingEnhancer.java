package com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.view.carousel;

import androidx.recyclerview.widget.RecyclerView;

public interface HorizontalScrollingEnhancer {

    default void enhanceHorizontalScroll(final RecyclerView recyclerView) {
        //no op..
    }
}
