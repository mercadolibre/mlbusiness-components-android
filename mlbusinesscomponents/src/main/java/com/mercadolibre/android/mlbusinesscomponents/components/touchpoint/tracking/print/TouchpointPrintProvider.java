package com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.tracking.print;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public final class TouchpointPrintProvider {

    private static final String TOUCHPOINT_KEY = "items";

    private final Set<String> history;
    private final Map<String, Object> accumulated;

    /**
     * Constructor
     *
     * @param history A history of data who was tracked
     * @param accumulated The data to track when scroll stop
     */
    public TouchpointPrintProvider(final Set<String> history, final Map<String, Object> accumulated) {
        this.history = history;
        this.accumulated = accumulated;
    }

    public void cleanHistory() {
        history.clear();
    }

    public void accumulateData(@Nullable final TouchpointTracking tracking) {
        if (tracking != null && !historyContains(tracking.getTrackingId())) {
            List<Map<String, Object>> eventData = (List<Map<String, Object>>) accumulated.get(TOUCHPOINT_KEY);
            if (eventData == null) {
                eventData = new ArrayList<>();
            }
            eventData.add(tracking.getEventData());
            accumulated.put(TOUCHPOINT_KEY, eventData);
            updateHistory(tracking.getTrackingId());
        }
    }

    @NonNull
    public Map<String, Object> getData() {
        return accumulated;
    }

    public void cleanData() {
        accumulated.clear();
    }

    private boolean historyContains(final String key) {
        return history.contains(key);
    }

    private void updateHistory(final String key) {
        history.add(key);
    }
}