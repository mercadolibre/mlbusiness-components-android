package com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.hybrid_carousel.model;

public interface TouchpointItem {

    int NO_POSITION = -2;

    /**
     * Get the type of the item.
     *
     * @return the type.
     */
    int getItemType();

    /**
     * Get the item position on adapter collection
     *
     * @return The index
     */
    default int getAdapterPosition() {
        return NO_POSITION;
    }

    /**
     * Set the item position on adapter collection
     *
     * @param position The item position
     */
    default void setAdapterPosition(final int position) {
        //no-op
    }

    /**
     * Returns the card height.
     *
     * @return the card height
     */
    double getHeight();
}
