package com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.tracking.print;

import android.graphics.Rect;
import androidx.annotation.Nullable;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

public final class MLBusinessTouchpointListener {

    private static final int ACTION_UP = MotionEvent.ACTION_UP;

    private final TouchpointViewFinder finder;
    @Nullable private TouchpointChildPrintable printable;

    public MLBusinessTouchpointListener() {
        finder = new TouchpointViewFinder(new Rect());
    }

    /**
     * Reset history of tracked prints
     */
    public void resetTrackedPrints() {
        printable = finder.getPrintable();
        if (printable != null) {
            printable.cleanHistory();
        }
    }

    /**
     * Do print if view is visible
     *
     * @param container A {@link ViewGroup}
     */
    public void print(final ViewGroup container) {
        printable = finder.getPrintableIfVisible(container);
        if (printable != null) {
            printable.print();
        }
    }

    /**
     * Listen the event {@value ACTION_UP} of the container
     *
     * @param container A {@link ViewGroup}
     */
    public void setOnTouchListener(final ViewGroup container) {
        print(container);
        container.setOnTouchListener(this::isActionUp);
    }

    private boolean isActionUp(final View viewGroup, final MotionEvent event) {
        if (event.getAction() == ACTION_UP) {
            print((ViewGroup) viewGroup);
        }
        return false;
    }
}
