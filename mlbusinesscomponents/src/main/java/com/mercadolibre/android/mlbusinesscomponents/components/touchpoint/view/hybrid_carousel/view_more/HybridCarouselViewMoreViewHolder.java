package com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.view.hybrid_carousel.view_more;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
import com.mercadolibre.android.mlbusinesscomponents.R;
import com.mercadolibre.android.mlbusinesscomponents.common.TouchpointImageLoader;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.callback.OnClickCallback;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.hybrid_carousel.model.HybridCarouselCardContainerModel;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.view.hybrid_carousel.ItemViewHolder;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.view.hybrid_carousel.view_more.card.HybridCarouselViewMoreCardView;

public class HybridCarouselViewMoreViewHolder extends ItemViewHolder {

    private final HybridCarouselViewMoreCardView view;
    private HybridCarouselCardContainerModel model;

    /**
     * Constructor
     *
     * @param container parent view
     */
    public HybridCarouselViewMoreViewHolder(@NonNull final View container) {
        super(container);
        view = container.findViewById(R.id.touchpoint_hybrid_carousel_view_more_card);
    }

    @Override
    public void bindView(final HybridCarouselCardContainerModel model, final TouchpointImageLoader imageLoader, @Nullable
    final OnClickCallback onClickCallback, final int size) {
        if (!model.equals(this.model)) {
            this.model = model;
            this.model.getContent().setAdapterPosition(getAdapterPosition());
            view.bind(this.model, size);
            view.setImageLoader(imageLoader);
            view.setOnClickCallback(onClickCallback);
        }
    }
}
