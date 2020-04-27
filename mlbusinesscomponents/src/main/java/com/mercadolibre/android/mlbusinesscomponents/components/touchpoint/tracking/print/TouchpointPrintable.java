package com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.tracking.print;

import android.support.annotation.Nullable;

public interface TouchpointPrintable {

    /**
     * Forward model tracking
     *
     * @return An event data
     */
    @Nullable
    TouchpointTracking getTracking();
}
