package com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.view.hybrid_carousel;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import com.mercadolibre.android.mlbusinesscomponents.common.TouchpointImageLoader;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.callback.OnClickCallback;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.hybrid_carousel.TouchpointItemType;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.hybrid_carousel.model.HybridCarouselCardContainerModel;
import com.mercadolibre.android.mlbusinesscomponents.components.utils.DiffUtils;
import java.util.ArrayList;
import java.util.List;

public class HybridCarouselAdater extends RecyclerView.Adapter<ItemViewHolder> {

    private final List<HybridCarouselCardContainerModel> elements;
    private TouchpointImageLoader imageLoader;
    private OnClickCallback onClickCallback;
    private double size;

    /**
     * Constructor
     */
    public HybridCarouselAdater() {
        elements = new ArrayList<>();
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull final ViewGroup viewGroup, final int type) {
        return TouchpointItemType.getItemFromPosition(type).getViewHolder(viewGroup);
    }

    @Override
    public void onBindViewHolder(@NonNull final ItemViewHolder viewHolder, final int index) {
        viewHolder.bindView(elements.get(index), imageLoader, onClickCallback, size);
    }

    @Override
    public int getItemViewType(final int position) {
        return elements.get(position).getContent().getItemType();
    }

    @Override
    public int getItemCount() {
        return elements.size();
    }

    @Override
    public long getItemId(final int position) {
        return elements.get(position).hashCode();
    }

    /**
     * Set the initial discount list.
     *
     * @param initialCards the discounts list.
     */
    public void setItems(@NonNull final List<HybridCarouselCardContainerModel> initialCards) {
        final DiffUtils diffCallback = new DiffUtils<>(elements, initialCards);
        final DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffCallback);
        elements.clear();
        elements.addAll(initialCards);
        diffResult.dispatchUpdatesTo(this);
    }

    /**
     * Sets the image loader delegate.
     *
     * @param imageLoader the delegate.
     */
    public void setImageLoader(final TouchpointImageLoader imageLoader) {
        this.imageLoader = imageLoader;
    }

    /**
     * Sets the click callback.
     *
     * @param onClickCallback the click callback.
     */
    public void setOnClickCallback(@Nullable final OnClickCallback onClickCallback) {
        this.onClickCallback = onClickCallback;
    }

    /**
     * Sets the card height.
     *
     * @param fixedCardHeight the card height.
     */
    public void setCardHeight(final double fixedCardHeight) {
        this.size = fixedCardHeight;
    }
}