package com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.view.hybrid_carousel;

import android.view.ViewGroup;

public interface ItemViewHolderProvider {

    /**
     * Get view holder from type
     *
     * @param parent The parent
     * @return A {@link ItemViewHolder}
     */
    ItemViewHolder getViewHolderFromType(final ViewGroup parent);
}
