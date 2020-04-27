package com.mercadolibre.android.mlbusinesscomponentsapp.touchpoint;

import android.content.Context;
import android.support.annotation.Nullable;
import com.google.gson.Gson;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.response.MLBusinessTouchpointData;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.response.MLBusinessTouchpointResponse;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.tracking.TouchpointTracker;
import com.mercadolibre.android.mlbusinesscomponentsapp.R;

public enum TouchpointSamples {

    FULL_GRID(R.raw.touchpoint_grid_full_content),
    PARTIAL_GRID(R.raw.touchpoint_grid_partial_content),
    ;

    final int rawId;
    final Gson gson;

    TouchpointSamples(final int rawId) {
        this.rawId = rawId;
        gson = new Gson();
    }

    public MLBusinessTouchpointData retrieveResponse(final Context context, @Nullable final TouchpointTracker tracker) {
        return new MLBusinessTouchpointData() {
            @Override
            public MLBusinessTouchpointResponse getResponse() {
                return gson.fromJson(FileUtils.loadFromRaw(context, rawId), MLBusinessTouchpointResponse.class);
            }

            @Override
            public TouchpointTracker getTouchpointTracker() {
                return tracker;
            }
        };
    }
}
