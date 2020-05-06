package com.mercadolibre.android.mlbusinesscomponents.components.discount;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.mercadolibre.android.mlbusinesscomponents.R;
import com.mercadolibre.android.mlbusinesscomponents.common.MLBusinessSingleItem;
import com.mercadolibre.android.mlbusinesscomponents.components.utils.StringUtils;
import com.mercadolibre.android.picassodiskcache.PicassoDiskLoader;

import static com.mercadolibre.android.mlbusinesscomponents.components.utils.TrackingUtils.TAP;

public class MLBusinessDiscountBoxItemView extends LinearLayout {

    private final LinearLayout itemClick;
    private final ImageView icon;
    private final TextView title;
    private final TextView subtitle;

    /**
     * Constructor
     *
     * @param context the context
     */
    public MLBusinessDiscountBoxItemView(final Context context) {
        this(context, null);
    }

    /**
     * Constructor
     *
     * @param context the context
     * @param attrs the attribute set
     */
    public MLBusinessDiscountBoxItemView(final Context context, @Nullable final AttributeSet attrs) {
        this(context, attrs, 0);
    }

    /**
     * Constructor
     *
     * @param context the context
     * @param attrs the attribute set
     * @param defStyleAttr the attribute style
     */
    public MLBusinessDiscountBoxItemView(final Context context, @Nullable final AttributeSet attrs,
        final int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inflate(context, R.layout.ml_view_business_discount_box_item, this);
        init();
        itemClick = findViewById(R.id.discount_item);
        icon = findViewById(R.id.discount_item_icon);
        title = findViewById(R.id.discount_item_title);
        subtitle = findViewById(R.id.discount_item_subtitle);
    }

    private void init() {
        setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1));
        setGravity(Gravity.CENTER);
    }

    /**
     * Bind model
     *
     * @param item A {@link MLBusinessSingleItem}
     */
    public void bind(final MLBusinessSingleItem item,
        @Nullable final MLBusinessDiscountBoxView.OnClickDiscountBox onClick, final int index,
        @Nullable final MLBusinessDiscountTracker discountTracker) {
        showImage(item.getImageUrl());
        showTitle(item.getTitleLabel());
        showSubtitle(item.getSubtitleLabel());
        setListener(item, onClick, index, discountTracker);
    }

    private void showImage(@NonNull final String url) {
        PicassoDiskLoader.get(getContext())
            .load(Uri.parse(url))
            .transform(new CircleTransform())
            .placeholder(R.drawable.skeleton)
            .into(icon);
    }

    private void showTitle(final String title) {
        showTextIfCould(title, this.title);
    }

    private void showSubtitle(final String subtitle) {
        showTextIfCould(subtitle, this.subtitle);
    }

    private void setListener(final MLBusinessSingleItem item,
        @Nullable final MLBusinessDiscountBoxView.OnClickDiscountBox listener,
        final int index, @Nullable final MLBusinessDiscountTracker discountTracker) {
        if (listener != null) {
            itemClick.setOnClickListener(v -> onClickItem(listener, index, item, discountTracker));
        }
    }

    private void onClickItem(@NonNull final MLBusinessDiscountBoxView.OnClickDiscountBox listener, final int index,
        final MLBusinessSingleItem item, @Nullable final MLBusinessDiscountTracker discountTracker) {
        listener.onClickDiscountItem(index, item.getDeepLinkItem(), item.getTrackId());
        trackTapEvent(item, discountTracker);
    }

    private void trackTapEvent(final MLBusinessSingleItem item,
        @Nullable final MLBusinessDiscountTracker discountTracker) {
        if (discountTracker != null) {
            discountTracker.track(TAP, item.getEventData());
        }
    }

    private void showTextIfCould(final String text, final TextView textView) {
        if (StringUtils.isValidString(text)) {
            textView.setText(text);
            return;
        }
        textView.setVisibility(GONE);
    }
}
