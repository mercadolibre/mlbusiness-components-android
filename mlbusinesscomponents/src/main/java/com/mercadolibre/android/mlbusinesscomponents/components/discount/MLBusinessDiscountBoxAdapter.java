package com.mercadolibre.android.mlbusinesscomponents.components.discount;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
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
import com.mercadolibre.android.mlbusinesscomponents.common.MLBusinessSingleItem;
import com.mercadolibre.android.mlbusinesscomponents.components.utils.StringUtils;
import com.mercadolibre.android.picassodiskcache.PicassoDiskLoader;
import java.lang.ref.WeakReference;
import java.util.List;

import static android.text.TextUtils.isEmpty;

class MLBusinessDiscountBoxAdapter
    extends RecyclerView.Adapter<MLBusinessDiscountBoxAdapter.DiscountBoxViewHolder> {

    private final List<MLBusinessSingleItem> items;
    /* default */ final WeakReference<MLBusinessDiscountBoxView.OnClickDiscountBox> onClickDiscountBox;

    MLBusinessDiscountBoxAdapter(
            final List<com.mercadolibre.android.mlbusinesscomponents.common.MLBusinessSingleItem> items,
            @Nullable final WeakReference<MLBusinessDiscountBoxView.OnClickDiscountBox> onClickDiscountBox) {
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
    public void onBindViewHolder(@NonNull final DiscountBoxViewHolder holder, final int position) {
        holder.bind(items.get(position), position);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class DiscountBoxViewHolder extends RecyclerView.ViewHolder {
        final ImageView iconImage;
        final TextView titleLabel;
        final TextView subtitleLabel;

        DiscountBoxViewHolder(@NonNull final View itemView) {
            super(itemView);

            iconImage = itemView.findViewById(R.id.iconImage);
            titleLabel = itemView.findViewById(R.id.titleLabel);
            subtitleLabel = itemView.findViewById(R.id.subtitleLabel);
        }

        void bind(final MLBusinessSingleItem item, final int position) {
            setTitle(item.getTitleLabel());
            setSubtitle(item.getSubtitleLabel());
            setOnClickListener(item.getDeepLinkItem(), item.getTrackId(), position);
            loadIconImage(item.getImageUrl());
        }

        private void setTitle(final String title) {
            if (StringUtils.isValidString(title)) {
                titleLabel.setText(title);
                titleLabel.setVisibility(View.VISIBLE);
            }
        }

        private void setSubtitle(final String subtitle) {
            if (StringUtils.isValidString(subtitle)) {
                subtitleLabel.setText(subtitle);
                subtitleLabel.setVisibility(View.VISIBLE);
            }
        }

        private void setOnClickListener(@Nullable final String deeplink, @Nullable final String trackId,
            final int position) {
            if (onClickDiscountBox != null) {
                setRippleEffect();
                setOnClickItem(position, deeplink, trackId);
            }
        }

        void setRippleEffect(@Nullable String itemDeepLink) {
            if (!isEmpty(itemDeepLink)) {
                final Context context = itemView.getContext();
                if (context != null) {
                    final int resId =  android.R.attr.selectableItemBackground;
                    final TypedValue outValue = new TypedValue();
                    context.getTheme().resolveAttribute(resId, outValue, true);
                    itemView.setBackgroundResource(outValue.resourceId);
                }
            }
        }

        void setOnClickItem(final int indexItem, @Nullable final String deepLink, @Nullable final String trackId) {
            itemView.setOnClickListener(v -> {
                final MLBusinessDiscountBoxView.OnClickDiscountBox listener = onClickDiscountBox.get();
                if (listener != null) {
                    listener.onClickDiscountItem(indexItem, deepLink, trackId);
                }
            });
        }

        void loadIconImage(@NonNull final String url) {
            final Context context = itemView.getContext();
            if (context != null) {
                PicassoDiskLoader.get(context)
                    .load(Uri.parse(url))
                    .transform(new CircleTransform())
                    .placeholder(R.drawable.skeleton)
                    .into(iconImage);
            }
        }
    }
}