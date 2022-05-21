package com.mercadolibre.android.mlbusinesscomponentsapp.touchpoint;

import android.content.Context;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.response.MLBusinessTouchpointResponse;
import com.mercadolibre.android.mlbusinesscomponentsapp.R;

public enum TouchpointSamples {

   /* FULL_GRID(R.raw.touchpoint_grid_full_content),
    PARTIAL_GRID(R.raw.touchpoint_grid_partial_content),
    CAROUSEL(R.raw.touchpoint_carousel),
    CAROUSEL_TOUCHPOINT_V2(R.raw.touchpoint_carousel_touchpoint_v2),
    HYBRID_CAROUSEL(R.raw.touchpoint_hybrid_carousel),
    COVER_CAROUSEL(R.raw.touchpoint_cover_carousel),*/
    FLEX_COVER_CAROUSEL(R.raw.touchpoint_flex_cover_carousel);

    final int rawId;
    final Gson gson;

    TouchpointSamples(final int rawId) {
        this.rawId = rawId;
        gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();
    }

    public MLBusinessTouchpointResponse retrieveResponse(final Context context) {
        return gson.fromJson(FileUtils.loadFromRaw(context, rawId), MLBusinessTouchpointResponse.class);
    }
}
