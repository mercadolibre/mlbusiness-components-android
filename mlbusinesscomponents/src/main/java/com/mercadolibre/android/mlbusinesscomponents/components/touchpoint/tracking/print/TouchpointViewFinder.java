package com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.tracking.print;

import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;

/* default */ class TouchpointViewFinder {

    private final Rect rect;
    @Nullable private TouchpointChildPrintable printable;

    /* default */ TouchpointViewFinder(final Rect rect) {
        this.rect = rect;
    }

    /**
     * Find a {@link TouchpointChildPrintable}
     *
     * @param viewGroup a {@link ViewGroup}
     * @return Retrieve printable child or null if could not find it
     */
    @Nullable
    /* default */ TouchpointChildPrintable getPrintable(final ViewGroup viewGroup) {
        find(viewGroup);
        return printable;
    }

    private void find(final ViewGroup viewGroup) {
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            final View child = viewGroup.getChildAt(i);
            if (child instanceof TouchpointChildPrintable && child.getLocalVisibleRect(rect)) {
                printable = (TouchpointChildPrintable) child;
            }
            if (child instanceof ViewGroup) {
                find((ViewGroup) child);
            }
        }
    }
}
