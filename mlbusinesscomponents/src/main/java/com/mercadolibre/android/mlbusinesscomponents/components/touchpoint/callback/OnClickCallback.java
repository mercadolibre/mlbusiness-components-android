package com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.callback;

import android.support.annotation.Nullable;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.tracking.print.TouchpointTracking;

public interface OnClickCallback {

    /**
     * On click callback
     *
     * @param deepLink The desiree link to lunch
     */
    void onClick(@Nullable final String deepLink);

    /**
     * On click callback with tracking
     *
     * @param deepLink The desiree link to lunch
     */
    default void onClickWithTracking(@Nullable final String deepLink, @Nullable final TouchpointTracking touchpointTracking) {
        // no op..
    }
}
