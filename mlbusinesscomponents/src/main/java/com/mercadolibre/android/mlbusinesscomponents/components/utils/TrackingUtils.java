package com.mercadolibre.android.mlbusinesscomponents.components.utils;

import androidx.annotation.Nullable;
import com.mercadolibre.android.mlbusinesscomponents.common.MLBusinessSingleItem;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.tracking.MLBusinessTouchpointTracker;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.tracking.TouchpointTrackeable;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.tracking.print.TouchpointPrintProvider;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.tracking.print.TouchpointTracking;
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

    public static void trackTap(@Nullable final MLBusinessTouchpointTracker tracker,
        @Nullable final TouchpointTracking tracking) {
        if (tracker != null && isValidTracking(tracking)) {
            tracker.track(TAP, tracking.getEventData());
        }
    }

    public static void trackShow(@Nullable final MLBusinessTouchpointTracker tracker,
        final List<TouchpointTrackeable> trackeables) {
        if (tracker != null && !trackeables.isEmpty() && !retrieveDataToTrack(trackeables).isEmpty()) {
            tracker.track(SHOW, retrieveDataToTrack(trackeables));
        }
    }

    public static void trackPrint(@Nullable final MLBusinessTouchpointTracker tracker,
        final TouchpointPrintProvider printProvider) {
        if (tracker != null) {
            final Map<String, Object> data = printProvider.getData();
            if (!data.isEmpty()) {
                tracker.track(PRINT, data);
            }
            printProvider.cleanData();
        }
    }

    private static Map<String, Object> retrieveDataToTrack(final List<TouchpointTrackeable> items) {
        final List<Map<String, Object>> eventData = new ArrayList<>();
        for (final TouchpointTrackeable trackeable : items) {
            if (isValidTracking(trackeable.getTracking())) {
                eventData.add(trackeable.getTracking().getEventData());
            }
        }
        final Map<String, Object> data = new HashMap<>();
        if (!eventData.isEmpty()) {
            data.put(KEY, eventData);
        }
        return data;
    }

    private static boolean isValidTracking(@Nullable final TouchpointTracking tracking) {
        return tracking != null && tracking.getEventData() != null && !tracking.getEventData().isEmpty();
    }
}
