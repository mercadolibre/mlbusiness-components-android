package com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.tracking.print;

import android.support.annotation.Nullable;
import java.util.Map;

public interface TouchpointPrintable {

    /**
     * Forward model tracking
     *
     * @return An event data
     */
    @Nullable
    Map<String, Object> getTracking();
}
