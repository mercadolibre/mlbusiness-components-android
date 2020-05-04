package com.mercadolibre.android.mlbusinesscomponents.components.utils;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.mercadolibre.android.mlbusinesscomponents.common.MLBusinessSingleItem;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.tracking.TouchpointTrackeable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class TrackingUtils {

    private static final String KEY = "items";
    public static final String SHOW = "show";
    public static final String TAP = "tap";
    public static final String PRINT = "print";

    private TrackingUtils() {
        //no-op
    }

    public static Map<String, Object> retrieveBusinessSingleItemDataToTrack(final List<MLBusinessSingleItem> items) {
        final List<Map<String, Object>> eventData = new ArrayList<>();
        for (final MLBusinessSingleItem item : items) {
            if (item.getEventData() != null && !item.getEventData().isEmpty()) {
                eventData.add(item.getEventData());
            }
        }
        return new HashMap<>(Collections.singletonMap(KEY, eventData));
    }

    public static Map<String, Object> retrieveDataToTrack(final List<TouchpointTrackeable> items,
        @Nullable final Map<String, Object> tracking) {
        final List<Map<String, Object>> eventData = new ArrayList<>();
        for (final TouchpointTrackeable trackeable : items) {
            if (haveData(trackeable)) {
                eventData.add(trackeable.getTracking().getEventData());
            }
        }
        final Map<String, Object> data = new HashMap<>();
        data.put(KEY, eventData);
        return mergeData(data, tracking);
    }

    private static boolean haveData(final TouchpointTrackeable trackeable) {
        return trackeable.getTracking() != null
            && trackeable.getTracking().getEventData() != null
            && !trackeable.getTracking().getEventData().isEmpty();
    }

    public static Map<String, Object> mergeData(@NonNull final Map<String, Object> data,
        @Nullable final Map<String, Object> tracking) {
        if (tracking != null) {
            data.putAll(tracking);
        }
        return data;
    }

    public static Map<String, Object> toItem(@NonNull final Map<String, Object> eventData) {
        final Map<String, Object> data = new HashMap<>();
        data.put(KEY, Collections.singletonList(eventData));
        return data;
    }
}
