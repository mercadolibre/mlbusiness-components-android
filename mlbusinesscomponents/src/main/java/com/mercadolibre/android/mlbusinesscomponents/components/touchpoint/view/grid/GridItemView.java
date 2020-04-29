package com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.view.grid;

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
import com.mercadolibre.android.mlbusinesscomponents.components.discount.CircleTransform;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.callback.OnClickCallback;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.grid.GridItem;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.tracking.MLBusinessTouchpointTracker;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.tracking.print.TouchpointPrintable;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.tracking.print.TouchpointTracking;
import com.mercadolibre.android.mlbusinesscomponents.components.utils.StringUtils;
import com.mercadolibre.android.picassodiskcache.PicassoDiskLoader;
import org.jetbrains.annotations.NotNull;

import static com.mercadolibre.android.mlbusinesscomponents.components.utils.TrackingUtils.TAP;

public class GridItemView extends LinearLayout implements TouchpointPrintable {

    private final LinearLayout itemClick;
    private final ImageView icon;
    private final TextView title;
    private final TextView subtitle;
    @Nullable private TouchpointTracking tracking;

    /**
     * Constructor
     *
     * @param context the context
     */
    public GridItemView(final Context context) {
        this(context, null);
    }

    /**
     * Constructor
     *
     * @param context the context
     * @param attrs the attribute set
     */
    public GridItemView(final Context context, @Nullable final AttributeSet attrs) {
        this(context, attrs, 0);
    }

    /**
     * Constructor
     *
     * @param context the context
     * @param attrs the attribute set
     * @param defStyleAttr the attribute style
     */
    public GridItemView(final Context context, @Nullable final AttributeSet attrs,
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
     * @param item A {@link GridItem}
     * @param callback A {@link OnClickCallback}
     * @param tracker A {@link MLBusinessTouchpointTracker}
     */
    public void bind(final GridItem item, @Nullable final OnClickCallback callback,
        @Nullable final MLBusinessTouchpointTracker tracker) {
        showImage(item.getImage());
        showTitle(item.getTitle());
        showSubtitle(item.getSubtitle());
        setOnClick(item, callback, tracker);
        setTracking(item.getTracking());
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

    private void setOnClick(final GridItem item, @Nullable final OnClickCallback callback,
        @Nullable final MLBusinessTouchpointTracker tracker) {
        if (callback != null) {
            itemClick.setOnClickListener(v -> onClick(item, callback, tracker));
        }
    }

    private void onClick(final GridItem item, @NotNull final OnClickCallback callback,
        @Nullable final MLBusinessTouchpointTracker tracker) {
        if (tracker != null && item.getTracking() != null) {
            tracker.track(TAP, item.getTracking().getEventData());
        }
        callback.onClick(item.getLink());
    }

    private void showTextIfCould(final String text, final TextView textView) {
        if (StringUtils.isValidString(text)) {
            textView.setText(text);
            return;
        }
        textView.setVisibility(GONE);
    }

    @Override
    public void setTracking(@Nullable final TouchpointTracking tracking) {
        this.tracking = tracking;
    }

    @Nullable
    @Override
    public TouchpointTracking getTracking() {
        return tracking;
    }
}
