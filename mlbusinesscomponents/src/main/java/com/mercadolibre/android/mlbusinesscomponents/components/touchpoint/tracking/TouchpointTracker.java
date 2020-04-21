package com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.tracking;

import android.support.annotation.Nullable;
import java.util.Map;

public interface TouchpointTracker {

    /**
     * Track event
     *
     * @param eventData the tracking information
     */
    void track(@Nullable final String action, @Nullable final Map<String, Object> eventData);
}