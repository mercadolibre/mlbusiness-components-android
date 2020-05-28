package com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.TouchpointRegistry;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.callback.OnClickCallback;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.TouchpointMapper;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.response.MLBusinessTouchpointResponse;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.tracking.MLBusinessTouchpointTracker;

public class MLBusinessTouchpointView extends FrameLayout {

    @Nullable private OnClickCallback callback;
    private TouchpointRegistry type;
    @Nullable AbstractTouchpointChildView child;
    @Nullable private MLBusinessTouchpointTracker tracker;
    private boolean isMPInstalled = true;

    /**
     * Constructor
     *
     * @param context the context
     */
    public MLBusinessTouchpointView(@NonNull final Context context) {
        this(context, null);
    }

    /**
     * Constructor
     *
     * @param context the context
     * @param attrs the attribute set
     */
    public MLBusinessTouchpointView(@NonNull final Context context, @Nullable final AttributeSet attrs) {
        this(context, attrs, 0);
    }

    /**
     * Constructor
     *
     * @param context the context
     * @param attrs the attribute set
     * @param defStyleAttr the attribute style
     */
    public MLBusinessTouchpointView(@NonNull final Context context, @Nullable final AttributeSet attrs,
        final int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * Init view
     *
     * @param response A {@link MLBusinessTouchpointResponse}
     */
    public void init(final MLBusinessTouchpointResponse response) {
        if (response != null) {
            final TouchpointRegistry touchpointRegistry = TouchpointMapper.getTouchpointById(response.type);
            if (touchpointRegistry != null) {
                updateContent(response, touchpointRegistry);
            }
        }
    }

    /**
     * Update view
     *
     * @param response A {@link MLBusinessTouchpointResponse}
     */
    public void update(final MLBusinessTouchpointResponse response) {
        init(response);
    }

    private void updateContent(final MLBusinessTouchpointResponse response,
        final TouchpointRegistry touchpointRegistry) {
        if (touchpointRegistry == type) {
            child.setCanOpenMercadoPago(isMPInstalled);
            child.bind(TouchpointMapper.mapToContent(response));
        } else {
            removeAllViews();
            type = touchpointRegistry;
            child = touchpointRegistry.createViewFromResponse(getContext(), response, callback, tracker, isMPInstalled);
            addView(child);
        }
    }

    public void setOnClickCallback(@Nullable final OnClickCallback callback) {
        this.callback = callback;
    }

    public void setTracker(@Nullable final MLBusinessTouchpointTracker tracker) {
        this.tracker = tracker;
    }

    public void setCanOpenMercadoPago(final boolean isMPInstalled) {
        this.isMPInstalled = isMPInstalled;
    }

    public int getStaticHeight() {
        if (child == null) {
            return 0;
        }
        return child.getStaticHeight();
    }
}
