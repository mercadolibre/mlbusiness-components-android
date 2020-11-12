package com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.view.cover_carousel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import com.mercadolibre.android.mlbusinesscomponents.R;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.callback.OnClickCallback;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.cover_carousel.model.cover_card.CoverCardInterface;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.view.cover_carousel.cover_card.CoverCardView;
import java.util.ArrayList;
import java.util.List;

public class CarouselCoverAdapter extends RecyclerView.Adapter<CarouselCoverAdapter.CoverCardViewHolder> {

    private final List<CoverCardInterface> elements;
    private OnClickCallback onClickCallback;

    /**
     * Constructor
     */
    public CarouselCoverAdapter() {
        elements = new ArrayList<>();
    }

    @NonNull
    @Override
    public CoverCardViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, final int viewType) {
        final View view = LayoutInflater.from(parent.getContext())
            .inflate(R.layout.touchpoint_cover_card_container, parent, false);
        return new CoverCardViewHolder(view, parent.getContext());
    }

    @Override
    public void onBindViewHolder(@NonNull final CoverCardViewHolder holder, final int position) {
        holder.bindWithData(elements.get(position));
    }

    @Override
    public int getItemCount() {
        return elements.size();
    }

    /**
     * Sets the click callback.
     *
     * @param onClickCallback the click callback.
     */
    public void setOnClickCallback(@Nullable final OnClickCallback onClickCallback) {
        this.onClickCallback = onClickCallback;
    }

    public void bindItems(final List<CoverCardInterface> items) {
        elements.clear();
        elements.addAll(items);
        notifyDataSetChanged();
    }

    public class CoverCardViewHolder extends RecyclerView.ViewHolder {

        private final CoverCardView cardView;

        public CoverCardViewHolder(@NonNull final View itemView, final Context context) {
            super(itemView);
            cardView = (CoverCardView) itemView;
        }

        public void bindWithData(final CoverCardInterface coverCard) {
            cardView.bind(coverCard);
        }
    }
}