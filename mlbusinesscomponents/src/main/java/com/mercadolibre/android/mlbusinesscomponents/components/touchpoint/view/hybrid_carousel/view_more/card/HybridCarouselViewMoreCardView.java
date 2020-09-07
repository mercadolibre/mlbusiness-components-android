package com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.view.hybrid_carousel.view_more.card;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.mercadolibre.android.mlbusinesscomponents.R;
import com.mercadolibre.android.mlbusinesscomponents.common.TouchpointAssetLoader;
import com.mercadolibre.android.mlbusinesscomponents.common.TouchpointImageLoader;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.callback.OnClickCallback;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.hybrid_carousel.model.HybridCarouselCardContainerModel;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.hybrid_carousel.model.view_more.ViewMoreMainTitleFormat;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.tracking.TouchpointTrackeable;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.tracking.print.TouchpointTracking;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.view.carousel.card.AssetLoader;

public class HybridCarouselViewMoreCardView extends CardView implements TouchpointTrackeable {

    private final TextView middleTitle;
    private final SimpleDraweeView topImage;
    private final HybridViewMoreCardPresenter presenter;
    @Nullable private OnClickCallback onClickCallback;
    @Nullable private TouchpointTracking tracking;

    public HybridCarouselViewMoreCardView(@NonNull final Context context) {
        this(context, null);
    }

    public HybridCarouselViewMoreCardView(@NonNull final Context context, @Nullable final AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HybridCarouselViewMoreCardView(@NonNull final Context context, @Nullable final AttributeSet attrs, final int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inflate(getContext(), R.layout.touchpoint_hybrid_carousel_view_more_card_view, this);
        middleTitle = findViewById(R.id.touchpoint_hybrid_carousel_view_more_card_middle_title);
        topImage = findViewById(R.id.touchpoint_hybrid_carousel_view_more_card_top_image);

        presenter = new HybridViewMoreCardPresenter(this);
    }

    /**
     * Binds the view.
     *
     * @param model the data to bind
     * @param size the card size
     */
    public void bind(final HybridCarouselCardContainerModel model, final double size) {
        ViewGroup.LayoutParams layoutParams = getLayoutParams();
        this.tracking = model.getTracking();
        layoutParams.height = (int) (size * getResources().getDisplayMetrics().density);
        presenter.bindView(model);
    }

    /**
     * hides the card view
     */
    public void hideView() {
        setVisibility(GONE);
    }

    /**
     * shows the card view
     */
    public void showView() {
        setVisibility(VISIBLE);
    }

    /**
     * Shows the middle title
     *
     * @param label the text
     * @param format the format
     */
    public void setMiddleTitle(final String label, final ViewMoreMainTitleFormat format) {
        if (label == null) {
            return;
        }
        middleTitle.setVisibility(VISIBLE);
        if (!label.isEmpty()) {
            middleTitle.setText(label);
        }
        if (format.getTextColor() != null && !format.getTextColor().isEmpty()) {
            try {
                middleTitle.setTextColor(Color.parseColor(format.getTextColor()));
            } catch (Exception e) {
                // no op..
            }
        }
        if (format.getBackgroundColor() != null && !format.getBackgroundColor().isEmpty()) {
            try {
                middleTitle.setBackgroundColor(Color.parseColor(format.getBackgroundColor()));
            } catch (Exception e) {
                // no op..
            }
        }
    }

    /**
     * hides the title
     */
    public void hideTitle() {
        middleTitle.setVisibility(GONE);
    }

    /**
     * shows the image
     *
     * @param mainImage the image link
     */
    public void setImage(final String mainImage) {
        topImage.setVisibility(VISIBLE);
        AssetLoader.getImage(mainImage, topImage, (shouldLoadImage -> {
            if (shouldLoadImage) {
                TouchpointAssetLoader.create().withContainer(topImage).withSource(mainImage).load();
            }
        }));
    }

    /**
     * hides the image
     */
    public void hideImage() {
        topImage.setVisibility(GONE);
    }

    /**
     * Sets the click listener
     *
     * @param link link to push
     * @param tracking data to track
     */
    public void setOnClick(final String link, @Nullable final TouchpointTracking tracking) {
        setClickable(true);
        setOnClickListener(v -> onClickEvent(link, tracking));
    }

    private void onClickEvent(final String link, @Nullable final TouchpointTracking tracking) {
        if (onClickCallback != null) {
            onClickCallback.onClick(link);
            onClickCallback.sendTapTracking(tracking);
        }
    }

    /**
     * Dismiss the clickable
     */
    public void dismissClickable() {
        setClickable(false);
    }

    /**
     * Sets the click callback
     *
     * @param onClickCallback
     */
    public void setOnClickCallback(@Nullable final OnClickCallback onClickCallback) {
        this.onClickCallback = onClickCallback;
    }

    /**
     * Sets the image loader
     *
     * @param imageLoader the image loader.
     */
    public void setImageLoader(final TouchpointImageLoader imageLoader) {
        AssetLoader.setStrategy(imageLoader);
    }

    @Nullable
    @Override
    public TouchpointTracking getTracking() {
        return tracking;
    }
}
