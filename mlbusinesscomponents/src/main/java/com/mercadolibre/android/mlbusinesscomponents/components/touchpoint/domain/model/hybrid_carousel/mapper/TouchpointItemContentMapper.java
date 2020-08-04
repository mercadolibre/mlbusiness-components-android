package com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.hybrid_carousel.mapper;

import android.support.annotation.Nullable;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.hybrid_carousel.TouchpointItemType;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.hybrid_carousel.model.HybridCarouselCardContainerModel;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.hybrid_carousel.model.TouchpointItem;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.tracking.print.TouchpointTracking;
import java.util.Locale;

public class TouchpointItemContentMapper {

    private final Gson gson;
    private TouchpointItemType type;

    /**
     * Constructor
     */
    public TouchpointItemContentMapper() {
        this.gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();
    }

    /**
     * Converts from json content to {@link TouchpointItem} model
     *
     * @param type The item type
     * @param content The json content
     * @param link The link to push
     * @param tracking the tracking data
     * @return The necessary model to section
     */
    @Nullable
    public HybridCarouselCardContainerModel map(final String type, final String link, final TouchpointTracking tracking, final JsonElement content) {
        return isValidItemType(type) && isAValidContent(content) ?
            this.type.getModelFromContent(type, link, tracking, content.getAsJsonObject()) : null;
    }

    private boolean isValidItemType(@Nullable final String typeResponse) {
        if (typeResponse == null) {
            return false;
        }
        try {
            type = TouchpointItemType.valueOf(typeResponse.toUpperCase(Locale.getDefault()));
        } catch (final IllegalArgumentException e) {
            return false;
        }
        return true;
    }

    private boolean isAValidContent(@Nullable final JsonElement content) {
        return content != null && !content.isJsonNull() && content.isJsonObject();
    }
}
