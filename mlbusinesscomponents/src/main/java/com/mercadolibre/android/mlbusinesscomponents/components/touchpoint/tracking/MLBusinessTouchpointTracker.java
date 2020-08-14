package com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.tracking;

import androidx.annotation.Nullable;
import java.util.Map;

public interface MLBusinessTouchpointTracker {

    /**
     * Track event
     *
     * @param eventData the tracking information
     */
    void track(@Nullable final String action, @Nullable final Map<String, Object> eventData);

    /**
     * Set context identifier
     *
     * @param id The context identifier
     */
    void setId(final String id);
}