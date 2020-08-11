package com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.view.hybrid_carousel.default_card.card;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.mercadolibre.android.mlbusinesscomponents.R;
import com.mercadolibre.android.mlbusinesscomponents.common.TouchpointAssetLoader;
import com.mercadolibre.android.mlbusinesscomponents.common.TouchpointImageLoader;
import com.mercadolibre.android.mlbusinesscomponents.components.pill.model.PillResponseInterface;
import com.mercadolibre.android.mlbusinesscomponents.components.pill.view.RightBottomInfoView;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.callback.OnClickCallback;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.hybrid_carousel.model.HybridCarouselCardContainerModel;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.tracking.TouchpointTrackeable;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.tracking.print.TouchpointTracking;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.view.carousel.card.AssetLoader;

public class HybridCarouselDefaultCardView extends CardView implements TouchpointTrackeable {

    private HybridCarouselDefaultCardPresenter presenter;
    private final FrameLayout topImageContainer;
    private final SimpleDraweeView topImage;
    private final SimpleDraweeView topImageAccessory;
    private final TextView middleTitle;
    private final TextView middleSubtitle;
    private final TextView bottomTopLabel;
    private final TextView bottomPrimaryLabel;
    private final TextView bottomSecondaryLabel;
    private final RightBottomInfoView rightBottomInfoContainer;
    @Nullable private TouchpointTracking tracking;

    @Nullable private OnClickCallback onClickCallback;

    public HybridCarouselDefaultCardView(@NonNull final Context context) {
        this(context, null);
    }

    public HybridCarouselDefaultCardView(@NonNull final Context context, @Nullable final AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HybridCarouselDefaultCardView(@NonNull final Context context, @Nullable final AttributeSet attrs, final int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inflate(getContext(), R.layout.touchpoint_hybrid_carousel_default_card_view, this);
        topImageContainer = findViewById(R.id.touchpoint_hybrid_carousel_default_card_image_container);
        topImage = findViewById(R.id.touchpoint_hybrid_carousel_default_card_top_image);
        topImageAccessory = findViewById(R.id.touchpoint_hybrid_carousel_default_card_top_image_accessory);
        middleTitle = findViewById(R.id.touchpoint_hybrid_carousel_default_card_middle_title);
        middleSubtitle = findViewById(R.id.touchpoint_hybrid_carousel_default_card_middle_subtitle);
        bottomTopLabel = findViewById(R.id.touchpoint_hybrid_carousel_default_card_bottom_top_label);
        bottomPrimaryLabel = findViewById(R.id.touchpoint_hybrid_carousel_default_card_bottom_primary_label);
        bottomSecondaryLabel = findViewById(R.id.touchpoint_hybrid_carousel_default_card_bottom_secondary_label);
        rightBottomInfoContainer = findViewById(R.id.touchpoint_hybrid_carousel_default_card_bottom_info_container);
        rightBottomInfoContainer.bindViews();
        presenter = new HybridCarouselDefaultCardPresenter(this);
    }

    /**
     * binds the view.
     * @param model the data to bind
     * @param size the card's size
     */
    public void bind(final HybridCarouselCardContainerModel model, final double size) {
        this.tracking = model.getTracking();
        ViewGroup.LayoutParams layoutParams = getLayoutParams();
        layoutParams.height = (int) (size * getResources().getDisplayMetrics().density);
        presenter.bindView(model);
    }

    /**
     * hides the view
     */
    public void hideView() {
        setVisibility(GONE);
    }

    /**
     * shows the view
     */
    public void showView() {
        setVisibility(VISIBLE);
    }

    /**
     * hides the top image
     */
    public void hideTopImage() {
        topImageContainer.setVisibility(GONE);
    }

    /**
     * shows the top image
     * @param topImage the image link
     */
    public void setImage(final String topImage) {
        this.topImage.setVisibility(VISIBLE);
        AssetLoader.getImage(topImage, this.topImage, (shouldLoadImage -> {
            if (shouldLoadImage) {
                TouchpointAssetLoader.create().withContainer(this.topImage).withSource(topImage).load();
            }
        }));
    }

    /**
     * Sets the click listener
     *
     * @param link the link
     * @param tracking the tracking data
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
     * dissmiss the click action
     */
    public void dismissClickable() {
        setClickable(false);
    }

    /**
     * sets the onclick callback
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

    /**
     * hides the image accessory
     */
    public void hideImageAccessory() {
        topImageAccessory.setVisibility(GONE);
    }

    /**
     * shows the image accessory
     * @param topImageAccessory the image link
     */
    public void setImageAccessory(final String topImageAccessory) {
        this.topImageAccessory.setVisibility(VISIBLE);
        AssetLoader.getImage(topImageAccessory, this.topImageAccessory, (shouldLoadImage -> {
            if (shouldLoadImage) {
                TouchpointAssetLoader.create().withContainer(this.topImageAccessory).withSource(topImageAccessory).load();
            }
        }));
    }

    /**
     * hides the middle title
     */
    public void hideMiddleTitle() {
        middleTitle.setVisibility(GONE);
    }

    /**
     * shows the middle title
     *
     * @param middleTitle the title
     */
    public void setMiddleTitle(final String middleTitle) {
        setText(this.middleTitle, middleTitle);
    }

    /**
     * hides the middle title
     */
    public void hideMiddleSubtitle() {
        this.middleSubtitle.setVisibility(GONE);
    }

    /**
     *
     * @param middleSubtitle
     */
    public void setMiddleSubtitle(final String middleSubtitle) {
        setText(this.middleSubtitle, middleSubtitle);
    }

    /**
     * hides the top label
     */
    public void hideBottomTopLabel() {
        bottomTopLabel.setVisibility(GONE);
    }

    /**
     * shows the bottom top label
     *
     * @param bottomTopLabel the text
     */
    public void setBottomTopLabel(final String bottomTopLabel) {
        setText(this.bottomTopLabel, bottomTopLabel);
    }

    /**
     * hides the bottom primary label
     */
    public void hideBottomPrimaryLabel() {
        bottomPrimaryLabel.setVisibility(GONE);
    }

    /**
     * Shows the bottom primary label
     * @param bottomPrimaryLabel the text
     */
    public void setBottomPrimaryLabel(final String bottomPrimaryLabel) {
        setText(this.bottomPrimaryLabel, bottomPrimaryLabel);
    }

    /**
     * hides the bottom secondary label
     */
    public void hideBottomSecondaryLabel() {
        bottomSecondaryLabel.setVisibility(GONE);
    }

    /**
     * shows the bottom secondary label
     * @param bottomSecondaryLabel the text
     */
    public void setBottomSecondaryLabel(final String bottomSecondaryLabel) {
        setText(this.bottomSecondaryLabel, bottomSecondaryLabel);
    }

    /**
     * hides the right bottom info
     */
    public void hideRightBottomInfo() {
        rightBottomInfoContainer.hideRightBottomInfoView();
    }

    /**
     * shows the right bottom info
     * @param pill the pill info
     */
    public void showRightBottomInfo(final PillResponseInterface pill) {
        rightBottomInfoContainer.bind(pill);
        rightBottomInfoContainer.setTranslationY(-6 * getResources().getDisplayMetrics().density);
    }

    private void setText(final TextView view, final String text) {
        view.setVisibility(VISIBLE);
        view.setText(text);
    }

    @Nullable
    @Override
    public TouchpointTracking getTracking() {
        return tracking;
    }

    public void setBottomLabelsToBlockedStatus() {
        bottomPrimaryLabel.setAlpha(0.4f);
        bottomSecondaryLabel.setAlpha(0.4f);
    }

    public void setBottomLabelsToDefaultStatus() {
        bottomPrimaryLabel.setAlpha(1f);
        bottomSecondaryLabel.setAlpha(1f);
    }
}
