package com.mercadolibre.android.mlbusinesscomponentsapp.touchpoint;

import android.content.Context;
import com.google.gson.Gson;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.response.TouchpointResponse;
import com.mercadolibre.android.mlbusinesscomponentsapp.R;

public enum TouchpointSamples {

    GRID(R.raw.touchpoint_grid_content),
    GRID2(R.raw.touchpoint_grid_content2),
    ;

    private final int rawId;
    private final Gson gson;

    TouchpointSamples(final int rawId) {
        this.rawId = rawId;
        gson = new Gson();
    }

    public TouchpointResponse retrieveResponse(final Context context) {
        return gson.fromJson(FileUtils.loadFromRaw(context, rawId), TouchpointResponse.class);
    }
}
