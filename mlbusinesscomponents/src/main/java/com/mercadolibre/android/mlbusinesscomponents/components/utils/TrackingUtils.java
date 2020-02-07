package com.mercadolibre.android.mlbusinesscomponents.components.utils;

import com.mercadolibre.android.mlbusinesscomponents.common.MLBusinessSingleItem;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class TrackingUtils {

    private static final String KEY = "items";
    public static final String SHOW = "show";
    public static final String TAP = "tap";

    private TrackingUtils() {
        //no-op
    }

    public static Map<String, Object> retrieveDataToTrack(final List<MLBusinessSingleItem> items) {
        final List<Map<String, Object>> eventData = new ArrayList<>();
        for (final MLBusinessSingleItem item : items) {
            if (item.getEventData() != null && !item.getEventData().isEmpty()) {
                eventData.add(item.getEventData());
            }
        }
        return new HashMap<>(Collections.singletonMap(KEY, eventData));
    }
}
