package com.mercadolibre.android.mlbusinesscomponents.components.discount;

import java.util.List;
import java.util.Map;

public interface MLBusinessDiscountTracker {

    /**
     * Track event
     *
     * @param eventData the tracking information
     */
    void track(final String action, final List<Map<String, Object>> eventData);
}