package com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.tracking.print;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;

/* default */ public final class TouchointPrintProvider {

    private final Map<String, Object> history;
    private final Map<String, Object> accumulated;

    /**
     * Constructor
     *
     * @param history A history of data who was tracked
     * @param accumulated The data to track when scroll stop
     */
    private TouchointPrintProvider(final Map<String, Object> history, final Map<String, Object> accumulated) {
        this.history = history;
        this.accumulated = accumulated;
    }

    /* default */
    static TouchointPrintProvider create() {
        return new TouchointPrintProvider(new HashMap<>(), new HashMap<>());
    }

    /* default */ void cleanHistory() {
        history.clear(); //TODO llamar cuando se termina la session
    }

    /**
     * Update history
     */
    /* default */ void updateHistory() {
        history.putAll(accumulated);
    }

    /**
     * True if history of tracks contains the data
     *
     * @param value The value of track
     * @return True if contains this id, false otherwise
     */
    public boolean historyContains(final Object value) {
        return history.containsValue(value);
    }

    /* default */ void accumulateData(@Nullable final Map<String, Object> data) {
        //TODO
    }

    @NonNull
    /* default */ Map<String, Object> getData() {
        return accumulated;
    }

    /* default */ void cleanData() {
        accumulated.clear();
    }
}