package com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.view.hybrid_carousel;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import com.mercadolibre.android.mlbusinesscomponents.common.TouchpointImageLoader;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.callback.OnClickCallback;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.hybrid_carousel.model.HybridCarouselCardContainerModel;

public abstract class ItemViewHolder<M extends HybridCarouselCardContainerModel> extends RecyclerView.ViewHolder {

    /**
     * Constructor
     * @param itemView parent view.
     */
    public ItemViewHolder(@NonNull final View itemView) {
        super(itemView);
    }

    /**
     * Bind view
     *
     * @param item The model
     */
    public abstract void bindView(final M item, final TouchpointImageLoader imageLoader, @Nullable final OnClickCallback onClickCallback, final double size);
}
