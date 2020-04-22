package com.mercadolibre.android.mlbusinesscomponentsapp;

import android.content.Context;
import com.google.gson.Gson;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.response.TouchpointResponse;

public final class TouchpointFactory {

    private TouchpointFactory() {
        //no-op
    }

    public static TouchpointResponse createGridResponse(final Gson gson, final Context context) {
        return gson.fromJson(FileUtils.loadFromRaw(context, R.raw.touchpoint_grid_content), TouchpointResponse.class);
    }
}
