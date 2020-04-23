package com.mercadolibre.android.mlbusinesscomponents.components.touchpoint;

import android.content.Context;
import android.support.annotation.Nullable;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.callback.OnClickCallback;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.TouchpointMapper;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.TouchpointContent;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.grid.Grid;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.response.TouchpointResponse;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.view.AbstractTouchpointChildView;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.view.TouchpointViewFactory;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.view.grid.GridView;

public enum TouchpointRegistry {

    GRID(Grid.class, GridView::new),
    ;

    private final Class<? extends TouchpointContent> model;
    private final TouchpointViewFactory factory;
    private final Gson gson;

    TouchpointRegistry(final Class<? extends TouchpointContent> model, final TouchpointViewFactory factory) {
        this.model = model;
        this.factory = factory;
        gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();
    }

    /**
     * Retrieves the corresponding model for the given type
     *
     * @param json the JSON object
     * @return the model
     */
    public TouchpointContent getModelFromType(final JsonObject json) {
        return gson.fromJson(json.toString(), model);
    }

    /**
     * Creates a view from the given type
     *
     * @param context the context
     * @param response A {@link TouchpointResponse}
     * @param onClickCallback A {@link OnClickCallback}
     * @param tracker A {@link TouchpointTracker}
     * @return the view
     */
    public AbstractTouchpointChildView createViewFromResponse(final Context context, final TouchpointResponse response,
        @Nullable final OnClickCallback onClickCallback, @Nullable final TouchpointTracker tracker) {
        final AbstractTouchpointChildView view = factory.create(context);
        view.setTracker(tracker);
        view.setOnClickCallback(onClickCallback);
        view.bind(TouchpointMapper.mapToContent(response));
        return view;
    }
}
