package com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.tracking.print;

import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.tracking.MLBusinessTouchpointTracker;
import java.util.Map;

import static com.mercadolibre.android.mlbusinesscomponents.components.utils.TrackingUtils.PRINT;

/* default */ class TouchpointPrinter {

    private final MLBusinessTouchpointTracker tracker;
    private final TouchointPrintProvider trackeableProvider;
    private final Rect rect;

    /* default */ TouchpointPrinter(@NonNull final MLBusinessTouchpointTracker tracker) {
        this.tracker = tracker;
        trackeableProvider = TouchointPrintProvider.create();
        rect = new Rect();
    }

    /* default */ void sendPrints() {
        final Map<String, Object> data = trackeableProvider.getData();
        if (!data.isEmpty()) {
            tracker.track(PRINT, data);
            trackeableProvider.cleanData();
        }
    }

    /* default */ void addPrints(final ViewGroup viewGroup) {
        viewGroup.getHitRect(rect);
        findAndAddTracking(viewGroup);
    }

    /* default */ void cleanHistory() {
        trackeableProvider.cleanHistory();
    }

    private void findAndAddTracking(final ViewGroup viewGroup) {
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            final View child = viewGroup.getChildAt(i);
            if (child instanceof ViewGroup && !(child instanceof TouchpointPrintable)) {
                findAndAddTracking((ViewGroup) child);
            }
            if (shouldTrackPrint(child)) {
                trackeableProvider.accumulateData(((TouchpointPrintable) child).getTracking());
            }
        }
    }

    private boolean shouldTrackPrint(final View child) {
        return child instanceof TouchpointPrintable && child.getLocalVisibleRect(rect);
    }
}
