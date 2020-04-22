package com.mercadolibre.android.mlbusinesscomponentsapp;

import android.content.Context;
import com.google.gson.Gson;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.response.TouchpointResponse;

public enum TouchpointSamples {

    GRID(R.raw.touchpoint_grid_content),
    ;

    private final int rawId;

    TouchpointSamples(final int rawId) {
        this.rawId = rawId;
    }

    public TouchpointResponse get(final Gson gson, final Context context) {
        return gson.fromJson(FileUtils.loadFromRaw(context, rawId), TouchpointResponse.class);
    }
}
