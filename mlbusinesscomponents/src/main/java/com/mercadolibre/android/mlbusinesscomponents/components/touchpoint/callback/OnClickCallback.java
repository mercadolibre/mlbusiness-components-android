package com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.callback;

import androidx.annotation.Nullable;
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
     * @param touchpointTracking The tracking tap data.
     */
    default void sendTapTracking(@Nullable final TouchpointTracking touchpointTracking) {
        // no op..
    }
}
