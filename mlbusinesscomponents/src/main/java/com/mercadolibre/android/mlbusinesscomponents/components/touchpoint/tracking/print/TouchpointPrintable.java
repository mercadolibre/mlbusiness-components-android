package com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.tracking.print;

import android.support.annotation.Nullable;

public interface TouchpointPrintable {

    /**
     * Forward tracking
     *
     * @return A {@link TouchpointTracking}
     */
    @Nullable
    TouchpointTracking getTracking();

    /**
     * Set tracking
     *
     * @param tracking A {@link TouchpointTracking}
     */
    void setTracking(@Nullable final TouchpointTracking tracking);
}
