package com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.response;

import android.support.annotation.Nullable;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.tracking.TouchpointTracker;

public interface MLBusinessTouchpointData {

    /**
     * Get touchpoint response
     *
     * @return A {@link MLBusinessTouchpointResponse}
     */
    MLBusinessTouchpointResponse getResponse();

    /**
     * Get tracker
     *
     * @return A {@link TouchpointTracker}
     */
    @Nullable
    TouchpointTracker getTouchpointTracker();
}
