package com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.view.carousel;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.mercadolibre.android.mlbusinesscomponents.R;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.callback.OnClickCallback;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.carousel.CarouselCardResponse;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.tracking.MLBusinessTouchpointTracker;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.view.carousel.card.CarouselCardView;
import com.mercadolibre.android.mlbusinesscomponents.components.utils.DiffUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CarouselAdapter extends RecyclerView.Adapter<CarouselAdapter.CarouselViewHolder> {

    private final List<CarouselCardResponse> cards;
    @Nullable private OnClickCallback onClickCallback;
    @Nullable private MLBusinessTouchpointTracker tracker;
    @Nullable private Map<String, Object> extraData;

    /**
     * Constructor
     */
    /* default */ CarouselAdapter() {
        cards = new ArrayList<>();
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
        holder.bindView(cards.get(index));
    }

    @Override
    public int getItemCount() {
        return cards.size();
    }

    /**
     * Set cards
     *
     * @param cards A list of of {@link CarouselCardResponse}
     */
    public void setItems(@NonNull final List<CarouselCardResponse> cards) {
        final DiffUtils diffCallback = new DiffUtils<>(this.cards, cards);
        final DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffCallback);
        this.cards.clear();
        this.cards.addAll(cards);
        diffResult.dispatchUpdatesTo(this);
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

    /* default */ static class CarouselViewHolder extends RecyclerView.ViewHolder {

        private final CarouselCardView view;

        /* default */ CarouselViewHolder(@NonNull final View itemView) {
            super(itemView);
            view = itemView.findViewById(R.id.touchpoint_carousel_card_view);
        }

        /* default */ void bindView(final CarouselCardResponse card) {
            view.bind(card);
        }

        /* default */ void setOnClickCallback(@Nullable final OnClickCallback onClickCallback) {
            view.setOnClickCallback(onClickCallback);
        }

        /* default */ void setTracker(@Nullable final MLBusinessTouchpointTracker tracker) {
            view.setTracker(tracker);
        }

        /* default */ void setExtraData(@Nullable final Map<String, Object> extraData) {
            view.setExtraData(extraData);
        }
    }
}
