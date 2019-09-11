package com.mercadolibre.android.mlbusinesscomponents.components.discount;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.mercadolibre.android.mlbusinesscomponents.R;
import com.mercadolibre.android.mlbusinesscomponents.commons.SingleItem;
import com.squareup.picasso.Picasso;
import java.util.List;

class MLBusinessDiscountBoxAdapter
    extends RecyclerView.Adapter<MLBusinessDiscountBoxAdapter.DiscountBoxViewHolder> {

    final private List<SingleItem> items;
    final private MLBusinessDiscountBoxView.OnClickDiscountBox onClickDiscountBox;

    MLBusinessDiscountBoxAdapter(
        final List<SingleItem> items,
        @Nullable final MLBusinessDiscountBoxView.OnClickDiscountBox onClickDiscountBox) {
        this.items = items;
        this.onClickDiscountBox = onClickDiscountBox;
    }

    @NonNull
    @Override
    public DiscountBoxViewHolder onCreateViewHolder(@NonNull final ViewGroup viewGroup, final int i) {
        return new DiscountBoxViewHolder(LayoutInflater.from(viewGroup.getContext())
            .inflate(R.layout.ml_view_discount_box, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final DiscountBoxViewHolder holder, final int i) {
        final SingleItem item = items.get(i);

        holder.titleLabel.setText(item.getTitleLabel());
        holder.subtitleLabel.setText(item.getSubtitleLabel());
        holder.loadIconImage(item.getImageUrl());
        if (onClickDiscountBox != null) {
            holder.setRippleEffect();
            holder.setOnClickItem(item.getDeepLinkItem(), item.getTrackId());
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class DiscountBoxViewHolder extends RecyclerView.ViewHolder {

        final ImageView iconImage;
        final TextView titleLabel;
        final TextView subtitleLabel;
        private final Context context;

        DiscountBoxViewHolder(@NonNull final View itemView) {
            super(itemView);

            context = itemView.getContext();
            iconImage = itemView.findViewById(R.id.iconImage);
            titleLabel = itemView.findViewById(R.id.titleLabel);
            subtitleLabel = itemView.findViewById(R.id.subtitleLabel);
        }

        void setRippleEffect() {
            if (context != null) {
                TypedValue outValue = new TypedValue();
                context.getTheme().resolveAttribute(android.R.attr.selectableItemBackground, outValue, true);
                itemView.setBackgroundResource(outValue.resourceId);
            }
        }

        void setOnClickItem(@Nullable final String deepLink, @Nullable final String trackId) {
            itemView.setOnClickListener(v -> onClickDiscountBox.onClickDiscountItem(deepLink, trackId));
        }

        void loadIconImage(@NonNull String url) {
            if (context != null) {
                Picasso
                    .with(context)
                    .load(Uri.parse(url))
                    .placeholder(R.drawable.skeleton)
                    .into(iconImage);
            }
        }
    }
}
