package com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain;

import android.support.annotation.Nullable;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.TouchpointRegistry;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.TouchpointContent;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.response.TouchpointResponse;
import java.util.Locale;
import org.jetbrains.annotations.NotNull;

public final class TouchpointMapper {

    private TouchpointMapper() {
        //no-op
    }

    /**
     * Converts from {@link TouchpointResponse} DTO to {@link TouchpointContent} model
     *
     * @param response The service response
     * @return A {@link TouchpointContent}
     */
    @Nullable
    public static TouchpointContent mapToContent(final Gson gson, final TouchpointResponse response) {
        return isValidResponse(response) ? createTouchpoint(gson, response) : null;
    }

    @NotNull
    private static TouchpointContent createTouchpoint(final Gson gson, final TouchpointResponse response) {
        final TouchpointRegistry type = TouchpointRegistry.valueOf(response.type.toUpperCase(Locale.getDefault()));
        return type.getModelFromType(gson, response.content.getAsJsonObject());
    }

    private static boolean isValidResponse(final TouchpointResponse response) {
        return isAValidType(response.type) && isAValidContent(response.content);
    }

    private static boolean isAValidType(final String typeResponse) {
        if (typeResponse == null) {
            return false;
        }
        try {
            TouchpointRegistry.valueOf(typeResponse.toUpperCase(Locale.getDefault()));
        } catch (final IllegalArgumentException e) {
            return false;
        }

        return true;
    }

    private static boolean isAValidContent(@Nullable final JsonElement contentResponse) {
        return contentResponse != null && !contentResponse.isJsonNull() && contentResponse.isJsonObject();
    }
}