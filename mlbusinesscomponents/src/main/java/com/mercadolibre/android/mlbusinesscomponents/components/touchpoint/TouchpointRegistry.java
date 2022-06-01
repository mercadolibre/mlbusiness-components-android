package com.mercadolibre.android.mlbusinesscomponents.components.touchpoint;

import android.content.Context;

import androidx.annotation.Nullable;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.callback.OnClickCallback;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.TouchpointMapper;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.TouchpointContent;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.carousel.Carousel;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.cover_carousel.response.test.CoverCarouselResponse;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.flex_cover_carousel.FlexCoverCarouselResponse;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.grid.Grid;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.hybrid_carousel.response.HybridCarousel;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.response.MLBusinessTouchpointResponse;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.tracking.MLBusinessTouchpointTracker;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.view.AbstractTouchpointChildView;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.view.TouchpointViewFactory;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.view.carousel.CarouselView;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.view.cover_carousel.CoverCarouselView;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.view.flex_cover_carousel.FlexCoverCarouselView;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.view.grid.GridView;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.view.hybrid_carousel.HybridCarouselView;
import com.mercadolibre.android.mlbusinesscomponents.components.utils.StringUtils;

public enum TouchpointRegistry {

    GRID(Grid.class, GridView::new),
    CAROUSEL(Carousel.class, CarouselView::new),
    HYBRID_CAROUSEL(HybridCarousel.class, HybridCarouselView::new),
    COVER_CAROUSEL(CoverCarouselResponse.class, CoverCarouselView::new),
    FLEX_COVER_CAROUSEL (FlexCoverCarouselResponse.class, FlexCoverCarouselView::new);

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
     * @param response A {@link MLBusinessTouchpointResponse}
     * @param onClickCallback A {@link OnClickCallback}
     * @param tracker A {@link MLBusinessTouchpointTracker}
     * @param isMPInstalled true if MP is installed, false otherwise
     * @return the view
     */
    public AbstractTouchpointChildView createViewFromResponse(final Context context,
        final MLBusinessTouchpointResponse response, @Nullable final OnClickCallback onClickCallback,
        @Nullable final MLBusinessTouchpointTracker tracker, final boolean isMPInstalled) {
        final AbstractTouchpointChildView view = factory.create(context);
        setTracker(response.id, tracker, view);
        view.setCanOpenMercadoPago(isMPInstalled);
        view.setOnClickCallback(onClickCallback);
        view.setExtraData(response.tracking);
        view.setAdditionalInsets(response.additionalEdgeInsets);
        view.bind(TouchpointMapper.mapToContent(response));
        return view;
    }

    private void setTracker(final String id, @Nullable final MLBusinessTouchpointTracker tracker,
        final AbstractTouchpointChildView view) {
        if (StringUtils.isValidString(id) && tracker != null) {
            tracker.setId(id);
            view.setTracker(tracker);
        }
    }
}
