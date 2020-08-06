package com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.view.hybrid_carousel.view_more;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.mercadolibre.android.mlbusinesscomponents.R;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.view.hybrid_carousel.ItemViewHolder;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.view.hybrid_carousel.ItemViewHolderProvider;

public class HybridCarouselViewMoreViewHolderProvider implements ItemViewHolderProvider {

    @Override
    public ItemViewHolder getViewHolderFromType(final ViewGroup parent) {
        final View view = LayoutInflater.from(parent.getContext())
            .inflate(R.layout.touchpoint_hybrid_carousel_view_more_card_container, parent, false);
        return new HybridCarouselViewMoreViewHolder(view);
    }
}
