package com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.view.hybrid_carousel;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import com.mercadolibre.android.mlbusinesscomponents.common.TouchpointImageLoader;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.callback.OnClickCallback;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.hybrid_carousel.model.HybridCarouselCardContainerModel;

public abstract class ItemViewHolder<M extends HybridCarouselCardContainerModel> extends RecyclerView.ViewHolder {

    /**
     * Constructor
     *
     * @param itemView parent view.
     */
    public ItemViewHolder(@NonNull final View itemView) {
        super(itemView);
    }

    /**
     * Bind view
     *
     * @param item The model
     * @param onClickCallback the onclick callback
     * @param size the size in pixels
     */
    public abstract void bindView(final M item, final TouchpointImageLoader imageLoader, @Nullable final OnClickCallback onClickCallback,
        final int size);
}
