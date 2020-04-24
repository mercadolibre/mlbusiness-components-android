package com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.callback.OnClickCallback;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.TouchpointRegistry;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.TouchpointMapper;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.response.TouchpointResponse;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.tracking.TouchpointTracker;

public class TouchpointView extends FrameLayout {

    @Nullable private TouchpointTracker tracker;
    @Nullable private OnClickCallback callback;
    private TouchpointRegistry type;
    private AbstractTouchpointChildView child;

    /**
     * Constructor
     *
     * @param context the context
     */
    public TouchpointView(@NonNull final Context context) {
        this(context, null);
    }

    /**
     * Constructor
     *
     * @param context the context
     * @param attrs the attribute set
     */
    public TouchpointView(@NonNull final Context context, @Nullable final AttributeSet attrs) {
        this(context, attrs, 0);
    }

    /**
     * Constructor
     *
     * @param context the context
     * @param attrs the attribute set
     * @param defStyleAttr the attribute style
     */
    public TouchpointView(@NonNull final Context context, @Nullable final AttributeSet attrs, final int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * Init view
     *
     * @param response A {@link TouchpointResponse}
     */
    public void init(final TouchpointResponse response) {
        final TouchpointRegistry touchpointRegistry = TouchpointMapper.getTouchpointById(response.type);
        if (touchpointRegistry != null) {
            updateContent(response, touchpointRegistry);
        }
    }

    /**
     * Update view
     *
     * @param response A {@link TouchpointResponse}
     */
    public void update(final TouchpointResponse response) {
        init(response);
    }

    private void updateContent(final TouchpointResponse response, final TouchpointRegistry touchpointRegistry) {
        if (touchpointRegistry == type) {
            child.bind(TouchpointMapper.mapToContent(response));
        } else {
            removeAllViews();
            type = touchpointRegistry;
            child = touchpointRegistry.createViewFromResponse(getContext(), response, callback, tracker);
            addView(child);
        }
    }

    public void setTracker(@Nullable final TouchpointTracker tracker) {
        this.tracker = tracker;
    }

    public void setOnClickCallback(@Nullable final OnClickCallback callback) {
        this.callback = callback;
    }
}
