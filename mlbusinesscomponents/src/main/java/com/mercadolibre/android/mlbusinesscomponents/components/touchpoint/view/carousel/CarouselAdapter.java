package com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.view.carousel;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.mercadolibre.android.mlbusinesscomponents.R;
import com.mercadolibre.android.mlbusinesscomponents.common.TouchpointImageLoader;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.callback.OnClickCallback;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.carousel.CarouselCard;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.carousel.CarouselCardV2;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.carousel.TouchpointV2ItemType;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.tracking.MLBusinessTouchpointTracker;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.view.carousel.card.CarouselCardView;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.view.carousel.card.carouselCardInterface;
import com.mercadolibre.android.mlbusinesscomponents.components.utils.DiffUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CarouselAdapter extends RecyclerView.Adapter<CarouselAdapter.CarouselViewHolder> {

    private final List<CarouselCard> cards;
    @Nullable private OnClickCallback onClickCallback;
    @Nullable private MLBusinessTouchpointTracker tracker;
    @Nullable private Map<String, Object> extraData;
    private int cardHeight;
    private boolean isMPInstalled = true;
    private TouchpointImageLoader imageLoader;

    /**
     * Constructor
     */
    /* default */ CarouselAdapter() {
        cards = new ArrayList<>();
        cardHeight = 500;
    }

    @NonNull
    @Override
    public CarouselViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, final int index) {
        final View view = LayoutInflater.from(parent.getContext())
            .inflate(R.layout.touchpoint_carousel_card_view_container, parent, false);
        return new CarouselViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final CarouselViewHolder holder, final int index) {
        holder.setOnClickCallback(onClickCallback);
        holder.setTracker(tracker);
        holder.setExtraData(extraData);
        holder.setCanOpenMercadoPago(isMPInstalled);
        holder.setImageLoader(imageLoader);
        holder.bindView(cards.get(index), cardHeight);
    }

    @Override
    public int getItemCount() {
        return cards.size();
    }

    /**
     * Set cards
     *
     * @param cards A list of of {@link CarouselCard}
     */
    public void setItems(@NonNull final List<CarouselCard> cards) {
        final DiffUtils diffCallback = new DiffUtils<>(this.cards, cards);
        final DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffCallback);
        this.cards.clear();
        this.cards.addAll(cards);
        diffResult.dispatchUpdatesTo(this);
    }

    public void setImageLoader(final TouchpointImageLoader imageLoader) {
        this.imageLoader = imageLoader;
    }

    /* default */ void setOnClickCallback(@Nullable final OnClickCallback onClickCallback) {
        this.onClickCallback = onClickCallback;
    }

    /* default */ void setTracker(@Nullable final MLBusinessTouchpointTracker tracker) {
        this.tracker = tracker;
    }

    /* default */ void setExtraData(@Nullable final Map<String, Object> extraData) {
        this.extraData = extraData;
    }

    /* default */ void setCanOpenMercadoPago(final boolean isMPInstalled) {
        this.isMPInstalled = isMPInstalled;
    }

    public void setCardHeight(final int fixedCardHeight) {
        cardHeight = fixedCardHeight;
    }

    /* default */ static class CarouselViewHolder extends RecyclerView.ViewHolder {

        private final CarouselCardView defaultView;
        private final CarouselCardV2 fullView;
        private carouselCardInterface currentView;

        /* default */ CarouselViewHolder(@NonNull final View itemView) {
            super(itemView);
            defaultView = itemView.findViewById(R.id.touchpoint_carousel_card_view);
            fullView = itemView.findViewById(R.id.touchpoint_carousel_card_view_v2);
        }

        /* default */ void bindView(final CarouselCard card, final int size) {
            if (TouchpointV2ItemType.FULL.name().equals(card.getType())) {
                fullView.bind(null);
                currentView = fullView;
                return;
            }
            currentView = defaultView;
            defaultView.bind(card, size);
        }

        /* default */ void setOnClickCallback(@Nullable final OnClickCallback onClickCallback) {
            if (onClickCallback == null) return;
            currentView.setOnClickCallback(onClickCallback);
        }

        /* default */ void setTracker(@Nullable final MLBusinessTouchpointTracker tracker) {
            if (tracker == null) return;
            currentView.setTracker(tracker);
        }

        /* default */ void setExtraData(@Nullable final Map<String, Object> extraData) {
            if (extraData == null) return;
            currentView.setExtraData(extraData);
        }

        /* default */ void setCanOpenMercadoPago(final boolean isMPInstalled) {
            currentView.setCanOpenMercadoPago(isMPInstalled);
        }

        /* default */ void setImageLoader(final TouchpointImageLoader touchpointImageLoader) {
            currentView.setImageLoader(touchpointImageLoader);
        }
    }
}
