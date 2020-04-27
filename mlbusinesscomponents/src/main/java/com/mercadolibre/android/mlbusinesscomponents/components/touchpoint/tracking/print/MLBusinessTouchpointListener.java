package com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.tracking.print;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.view.MotionEvent;
import android.view.ViewGroup;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.tracking.MLBusinessTouchpointTracker;

public final class MLBusinessTouchpointListener {

    private final TouchpointPrinter printer;

    @SuppressLint("ClickableViewAccessibility")
    private MLBusinessTouchpointListener(@NonNull final MLBusinessTouchpointTracker tracker, final ViewGroup viewGroup) {
        printer = new TouchpointPrinter(tracker);
        viewGroup.setOnTouchListener((v, event) -> isActionUp(viewGroup, event));
    }

    /**
     * Start listen {@link ViewGroup} touch events
     *
     * @param tracker A {@link MLBusinessTouchpointTracker}
     * @param viewGroup the {@link ViewGroup}
     * @return A {@link MLBusinessTouchpointListener}
     */
    public static MLBusinessTouchpointListener listen(@NonNull final MLBusinessTouchpointTracker tracker,
        final ViewGroup viewGroup) {
        return new MLBusinessTouchpointListener(tracker, viewGroup);
    }

    /**
     * Reset history of tracked prints
     */
    public void resetTrackedPrints() {
        printer.cleanHistory();
    }

    private boolean isActionUp(final ViewGroup viewGroup, final MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP) {
            printer.addPrints(viewGroup);
            printer.sendPrints();
        }
        return false;
    }
}
