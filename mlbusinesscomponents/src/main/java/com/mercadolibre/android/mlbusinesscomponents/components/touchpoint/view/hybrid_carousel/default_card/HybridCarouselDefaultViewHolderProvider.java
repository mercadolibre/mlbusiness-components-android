package com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.view.hybrid_carousel.default_card;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.mercadolibre.android.mlbusinesscomponents.R;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.view.hybrid_carousel.ItemViewHolder;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.view.hybrid_carousel.ItemViewHolderProvider;

public class HybridCarouselDefaultViewHolderProvider implements ItemViewHolderProvider {

    @Override
    public ItemViewHolder getViewHolderFromType(final ViewGroup parent) {
        final View view = LayoutInflater.from(parent.getContext())
            .inflate(R.layout.touchpoint__hybrid_carousel_default_card_view, parent, false);
        return new HybridCarouselDefaultViewHolder(view);
    }
}
