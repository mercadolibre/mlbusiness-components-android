package com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.tracking.print;

import android.annotation.SuppressLint;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.TouchpointTracker;
import java.util.Map;

import static com.mercadolibre.android.mlbusinesscomponents.components.utils.TrackingUtils.PRINT;

public class TouchpointPrintListener implements View.OnTouchListener {

    private final TouchpointTracker tracker;
    private final TouchointPrintProvider trackeableProvider;
    private final Rect rect;

    /**
     * Constructor
     *
     * @param tracker A {@link TouchpointTracker}
     */
    public TouchpointPrintListener(@NonNull final TouchpointTracker tracker) {
        this.tracker = tracker;
        trackeableProvider = TouchointPrintProvider.create();
        rect = new Rect();
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouch(final View v, final MotionEvent event) {
        if (MotionEvent.ACTION_UP == event.getAction()) {
            addPrints((ViewGroup) v);
            sendPrints();
        }
        return false;
    }

    private void sendPrints() {
        final Map<String, Object> data = trackeableProvider.getData();
        if (!data.isEmpty()) {
            tracker.track(PRINT, data);
            trackeableProvider.updateHistory();
            trackeableProvider.cleanData();
        }
    }

    private void addPrints(final ViewGroup viewGroup) {
        viewGroup.getHitRect(rect);
        findAndAddTracking(viewGroup);
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
