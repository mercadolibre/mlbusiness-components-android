package com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.tracking.print;

import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.view.MLBusinessTouchpointView;

/* default */ class TouchpointViewFinder {

    private final Rect rect;

    /* default */ TouchpointViewFinder(final Rect rect) {
        this.rect = rect;
    }

    /**
     * Find touchpoint view
     *
     * @param viewGroup a {@link ViewGroup}
     * @return Retrieve the touchpoint view or null if could not find it
     */
    @Nullable
    /* default */ MLBusinessTouchpointView find(final ViewGroup viewGroup) {
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            final View child = viewGroup.getChildAt(i);
            if (child instanceof ViewGroup && !(child instanceof MLBusinessTouchpointView)) {
                return find((ViewGroup) child);
            }
            if (child instanceof MLBusinessTouchpointView && child.getLocalVisibleRect(rect)) {
                return (MLBusinessTouchpointView) child;
            }
        }
        return null;
    }
}
