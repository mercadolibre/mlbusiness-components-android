package com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.tracking.print;

import android.support.annotation.Nullable;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.tracking.TouchpointTrackeable;

public interface TouchpointPrintable extends TouchpointTrackeable {

    /**
     * Set tracking
     *
     * @param tracking A {@link TouchpointTracking}
     */
    void setTracking(@Nullable final TouchpointTracking tracking);
}
