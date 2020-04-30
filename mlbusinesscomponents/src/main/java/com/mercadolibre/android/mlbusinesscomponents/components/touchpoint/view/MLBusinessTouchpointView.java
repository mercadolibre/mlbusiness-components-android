package com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.TouchpointRegistry;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.callback.OnClickCallback;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.TouchpointMapper;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.response.MLBusinessTouchpointData;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.response.MLBusinessTouchpointResponse;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.tracking.MLBusinessTouchpointTracker;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.tracking.print.TouchpointPrintProvider;
import java.util.HashMap;
import java.util.HashSet;

public class MLBusinessTouchpointView extends FrameLayout {

    @Nullable private OnClickCallback callback;
    private TouchpointRegistry type;
    private AbstractTouchpointChildView child;
    @Nullable private MLBusinessTouchpointTracker tracker;
    private final TouchpointPrintProvider printProvider;

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
        printProvider = new TouchpointPrintProvider(new HashSet<>(), new HashMap<>());
    }

    /**
     * Init view
     *
     * @param data A {@link MLBusinessTouchpointData}
     */
    public void init(final MLBusinessTouchpointData data) {
        final MLBusinessTouchpointResponse response = data.getResponse();
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
     * @param data A {@link MLBusinessTouchpointData}
     */
    public void update(final MLBusinessTouchpointData data) {
        init(data);
    }

    private void updateContent(final MLBusinessTouchpointResponse response,
        final TouchpointRegistry touchpointRegistry) {
        if (touchpointRegistry == type) {
            child.bind(TouchpointMapper.mapToContent(response));
        } else {
            removeAllViews();
            type = touchpointRegistry;
            child = touchpointRegistry.createViewFromResponse(getContext(), response, callback, tracker);
            addView(child);
        }
    }

    public void setOnClickCallback(@Nullable final OnClickCallback callback) {
        this.callback = callback;
    }

    public void setTracker(@Nullable final MLBusinessTouchpointTracker tracker) {
        this.tracker = tracker;
    }

    /**
     * Do print
     */
    public void print() {
        child.setPrintProvider(printProvider);
        child.print();
    }

    /**
     * Clean tracks history
     */
    public void cleanHistory() {
        printProvider.cleanHistory();
    }
}
