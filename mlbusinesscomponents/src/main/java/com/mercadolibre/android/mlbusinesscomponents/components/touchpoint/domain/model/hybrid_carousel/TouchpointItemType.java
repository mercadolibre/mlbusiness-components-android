package com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.hybrid_carousel;

import android.support.annotation.Nullable;
import android.view.ViewGroup;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.hybrid_carousel.model.HybridCarouselCardContainerModel;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.hybrid_carousel.model.TouchpointItem;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.hybrid_carousel.model.default_card.HybridCarouselDefaultCard;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.hybrid_carousel.model.view_more.HybridCarouselViewMoreCard;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.tracking.print.TouchpointTracking;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.view.hybrid_carousel.ItemViewHolder;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.view.hybrid_carousel.ItemViewHolderProvider;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.view.hybrid_carousel.default_card.HybridCarouselDefaultViewHolderProvider;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.view.hybrid_carousel.view_more.HybridCarouselViewMoreViewHolderProvider;

public enum TouchpointItemType {

    DEFAULT(HybridCarouselDefaultCard.class, new HybridCarouselDefaultViewHolderProvider()),
    VIEW_MORE(HybridCarouselViewMoreCard.class, new HybridCarouselViewMoreViewHolderProvider());

    private final Class<? extends TouchpointItem> model;
    private final ItemViewHolderProvider viewHolderProvider;
    private final Gson gson;

    /**
     * Constructor
     *
     * @param model the model to map to.
     * @param viewHolderProvider the viewHolder provider
     */
    /* default */ TouchpointItemType(final Class<? extends TouchpointItem> model, final ItemViewHolderProvider viewHolderProvider) {
        this.model = model;
        this.viewHolderProvider = viewHolderProvider;
        gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();

    }

    /**
     * Gets the model from the content
     *
     * @param type type of secion
     * @param link link where to push
     * @param tracking data to track
     * @param content data to parse.
     * @return the complete model to draw
     */
    @Nullable
    public HybridCarouselCardContainerModel getModelFromContent(final String type, final String link, final TouchpointTracking tracking, final JsonElement content) {
        return new HybridCarouselCardContainerModel(type, link, tracking,gson.fromJson(content.toString(), model));
    }

    /**
     * Returns the viewholder to populate
     *
     * @param parent parent viewgroup
     * @return the viewholder to populate
     */
    public ItemViewHolder getViewHolder(final ViewGroup parent) {
        return viewHolderProvider.getViewHolderFromType(parent);
    }

    /**
     * Returns the specific itemtype for a given type
     * @param type the type to check
     * @return the specific item type
     */
    public static TouchpointItemType getItemFromPosition(final int type) {
        return TouchpointItemType.values()[type];
    }
}