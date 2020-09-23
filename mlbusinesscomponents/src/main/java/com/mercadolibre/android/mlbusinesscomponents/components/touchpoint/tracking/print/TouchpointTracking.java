package com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.tracking.print;

import androidx.annotation.Keep;
import java.io.Serializable;
import java.util.Map;

@Keep
public class TouchpointTracking implements Serializable {

    private static final long serialVersionUID = 5550209236409614639L;

    private final Map<String, Object> eventData;
    private final String trackingId;

    public TouchpointTracking(final Map<String, Object> eventData, final String trackingId) {
        this.eventData = eventData;
        this.trackingId = trackingId;
    }

    public Map<String, Object> getEventData() {
        return eventData;
    }

    public String getTrackingId() {
        return trackingId;
    }
}
