package com.mercadolibre.android.mlbusinesscomponents.components.touchpoint;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.TouchpointMapper;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.response.TouchpointResponse;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.view.TouchpointView;

public final class TouchpointCreator {

    private final Context context;
    private final TouchpointResponse response;
    @Nullable private TouchpointTracker tracker;
    @Nullable private OnClickCallback callback;

    private TouchpointCreator(@NonNull final Context context, @NonNull final TouchpointResponse response) {
        this.context = context;
        this.response = response;
    }

    /**
     * Create a {@link TouchpointCreator}
     *
     * @param context The activity caller
     * @param response A {@link TouchpointResponse}
     * @return The creator
     */
    public static TouchpointCreator create(@NonNull final Context context, @NonNull final TouchpointResponse response) {
        return new TouchpointCreator(context, response);
    }

    public TouchpointCreator withTracker(@Nullable final TouchpointTracker tracker) {
        this.tracker = tracker;
        return this;
    }

    public TouchpointCreator withOnClick(@Nullable final OnClickCallback callback) {
        this.callback = callback;
        return this;
    }

    @Nullable
    public TouchpointView get() {
        final TouchpointRegistry touchpointRegistry = TouchpointMapper.getTouchpointById(response.type);
        if (touchpointRegistry != null) {
            return touchpointRegistry.createViewFromResponse(context, response, callback, tracker);
        }
        return null;
    }
}
