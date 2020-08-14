package com.mercadolibre.android.mlbusinesscomponents.components.utils;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import java.util.List;

public class DiffUtils<T> extends DiffUtil.Callback {

    private final List<T> oldItems;
    private final List<T> newItems;

    /**
     * Constructor
     *
     * @param oldItems The attached items
     * @param newItems The new items to attach
     */
    public DiffUtils(@NonNull final List<T> oldItems, @NonNull final List<T> newItems) {
        this.oldItems = oldItems;
        this.newItems = newItems;
    }

    @Override
    public int getOldListSize() {
        return oldItems.size();
    }

    @Override
    public int getNewListSize() {
        return newItems.size();
    }

    @Override
    public boolean areItemsTheSame(final int oldItemPosition, final int newItemPosition) {
        final T oldItem = oldItems.get(oldItemPosition);
        final T newItem = newItems.get(newItemPosition);
        return oldItem.equals(newItem);
    }

    @Override
    public boolean areContentsTheSame(final int oldItemPosition, final int newItemPosition) {
        return areItemsTheSame(oldItemPosition, newItemPosition);
    }
}
