package com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.tracking;

import android.support.annotation.Nullable;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.tracking.print.TouchpointTracking;

public interface TouchpointTrackeable {

    /**
     * Forward tracking
     *
     * @return A {@link TouchpointTracking}
     */
    @Nullable
    TouchpointTracking getTracking();

}
