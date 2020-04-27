package com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.tracking.print;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* default */ final class TouchointPrintProvider {

    private static final String TOUCHPOINT_KEY = "items";

    private final Set<String> history;
    private final Map<String, Object> accumulated;

    /**
     * Constructor
     *
     * @param history A history of data who was tracked
     * @param accumulated The data to track when scroll stop
     */
    private TouchointPrintProvider(final Set<String> history, final Map<String, Object> accumulated) {
        this.history = history;
        this.accumulated = accumulated;
    }

    /* default */ static TouchointPrintProvider create() {
        return new TouchointPrintProvider(new HashSet<>(), new HashMap<>());
    }

    /**
     * Clean history of tracks
     */
    public void cleanHistory() {
        history.clear();
    }

    /* default */ void accumulateData(@Nullable final TouchpointTracking tracking) {
        if (tracking != null && !historyContains(tracking.getTrackingId())) {
            List<Object> eventData = (List<Object>) accumulated.get(TOUCHPOINT_KEY);
            if (eventData == null) {
                eventData = new ArrayList<>();
            }
            eventData.add(tracking.getEventData());
            accumulated.put(TOUCHPOINT_KEY, eventData);
            updateHistory(tracking.getTrackingId());
        }
    }

    @NonNull
    /* default */ Map<String, Object> getData() {
        return accumulated;
    }

    /* default */ void cleanData() {
        accumulated.clear();
    }

    private boolean historyContains(final String key) {
        return history.contains(key);
    }

    private void updateHistory(final String key) {
        history.add(key);
    }
}