package com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.view.hybrid_carousel.default_card;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import com.mercadolibre.android.mlbusinesscomponents.R;
import com.mercadolibre.android.mlbusinesscomponents.common.TouchpointImageLoader;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.callback.OnClickCallback;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.hybrid_carousel.model.HybridCarouselCardContainerModel;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.view.hybrid_carousel.ItemViewHolder;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.view.hybrid_carousel.default_card.card.HybridCarouselDefaultCardView;

public class HybridCarouselDefaultViewHolder extends ItemViewHolder {

    private final HybridCarouselDefaultCardView view;
    private HybridCarouselCardContainerModel model;

    /**
     * Constructor
     *
     * @param container parent view
     */
    public HybridCarouselDefaultViewHolder(@NonNull final View container) {
        super(container);
        view =  container.findViewById(R.id.touchpoint_hybrid_carousel_default_card);
    }

    @Override
    public void bindView(final HybridCarouselCardContainerModel model, final TouchpointImageLoader imageLoader, @Nullable
    final OnClickCallback onClickCallback, final double size) {
        if (!model.equals(this.model)) {
            this.model = model;
            this.model.getContent().setAdapterPosition(getAdapterPosition());
            view.setImageLoader(imageLoader);
            view.setOnClickCallback(onClickCallback);
            view.bind(this.model, size);
        }
    }
}
