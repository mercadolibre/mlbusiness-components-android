package com.mercadolibre.android.mlbusinesscomponents.components.discount;

import android.support.annotation.Nullable;

public interface OnClickDiscountBox {

    /**
     * On click discount item callback
     *
     * @param index The position which one is draw
     * @param deepLink The desiree link to lunch
     * @param trackId The item identifier to be tracked
     */
    void onClickDiscountItem(final int index, @Nullable final String deepLink, @Nullable final String trackId);
}
