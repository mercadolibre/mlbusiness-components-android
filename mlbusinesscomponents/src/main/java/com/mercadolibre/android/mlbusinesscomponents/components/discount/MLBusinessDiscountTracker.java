package com.mercadolibre.android.mlbusinesscomponents.components.discount;

import android.support.annotation.Nullable;
import java.util.Map;

public interface MLBusinessDiscountTracker {

    /**
     * Track event
     *
     * @param eventData the tracking information
     */
    void track(@Nullable final String action, @Nullable final Map<String, Object> eventData);
}