package com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.response;

import android.support.annotation.Nullable;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.tracking.MLBusinessTouchpointTracker;

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
     * @return A {@link MLBusinessTouchpointTracker}
     */
    @Nullable
    MLBusinessTouchpointTracker getTouchpointTracker();
}
