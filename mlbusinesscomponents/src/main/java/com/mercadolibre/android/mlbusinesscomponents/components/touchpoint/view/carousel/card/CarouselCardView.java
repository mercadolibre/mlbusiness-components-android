package com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.view.carousel.card;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.mercadolibre.android.mlbusinesscomponents.R;
import com.mercadolibre.android.mlbusinesscomponents.components.discount.CircleTransform;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.callback.OnClickCallback;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.carousel.CarouselCard;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.tracking.MLBusinessTouchpointTracker;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.tracking.TouchpointTrackeable;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.tracking.print.TouchpointTracking;
import com.mercadolibre.android.picassodiskcache.PicassoDiskLoader;
import java.util.Map;

import static com.mercadolibre.android.mlbusinesscomponents.components.utils.TrackingUtils.TAP;
import static com.mercadolibre.android.mlbusinesscomponents.components.utils.TrackingUtils.mergeData;
import static com.mercadolibre.android.mlbusinesscomponents.components.utils.TrackingUtils.toItem;

public class CarouselCardView extends CardView implements TouchpointTrackeable {

    private final ImageView logo;
    private final LinearLayout levelContainer;
    private final ImageView levelIcon;
    private final TextView levelDescription;
    private final TextView mainTitle;
    private final TextView topTitle;
    private final TextView rightTitle;
    private final TextView topLabel;
    private final TextView mainLabel;
    private final ConstraintLayout button;
    @Nullable private TouchpointTracking tracking;
    @Nullable private Map<String, Object> extraData;
    @Nullable private OnClickCallback onClickCallback;
    @Nullable private MLBusinessTouchpointTracker tracker;

    private final CarouselCardPresenter presenter;

    /**
     * Constructor
     *
     * @param context The execution context.
     */
    public CarouselCardView(@NonNull final Context context) {
        this(context, null);
    }

    /**
     * Constructor
     *
     * @param context The execution context.
     * @param attrs The style attributes.
     */
    public CarouselCardView(@NonNull final Context context, @Nullable final AttributeSet attrs) {
        this(context, attrs, 0);
    }

    /**
     * Constructor
     *
     * @param context The execution context.
     * @param attrs The style attributes.
     * @param defStyleAttr Default style attributes.
     */
    public CarouselCardView(@NonNull final Context context, @Nullable final AttributeSet attrs,
        final int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inflate(getContext(), R.layout.touchpoint_carousel_card_view, this);
        logo = findViewById(R.id.touchpoint_carousel_card_logo);
        levelContainer = findViewById(R.id.touchpoint_carousel_card_level_container);
        levelIcon = findViewById(R.id.touchpoint_carousel_card_level_icon);
        levelDescription = findViewById(R.id.touchpoint_carousel_card_level);
        topTitle = findViewById(R.id.touchpoint_carousel_card_top_title);
        mainTitle = findViewById(R.id.touchpoint_carousel_card_main_title);
        rightTitle = findViewById(R.id.touchpoint_carousel_card_right_title);
        topLabel = findViewById(R.id.touchpoint_carousel_card_top_label);
        mainLabel = findViewById(R.id.touchpoint_carousel_card_main_label);
        button = findViewById(R.id.touchpoint_carousel_card_button);
        presenter = new CarouselCardPresenter();
    }

    /**
     * Binds the model
     *
     * @param card the model
     */
    public void bind(final CarouselCard card) {
        presenter.onBind(card, this);
    }

    /* default */ void showImage(final String logo) {
        PicassoDiskLoader.get(getContext())
            .load(Uri.parse(logo))
            .transform(new CircleTransform())
            .placeholder(R.drawable.skeleton)
            .into(this.logo);
    }

    /* default */ void hideImage() {
        logo.setVisibility(GONE);
    }

    /* default */ void showTopTitle(final String topTitle) {
        this.topTitle.setText(topTitle);
        this.topTitle.setVisibility(VISIBLE);
    }

    /* default */ void hideTopTitle() {
        topTitle.setVisibility(GONE);
    }

    /* default */ void showMainTitle(final String mainTitle) {
        this.mainTitle.setText(mainTitle);
    }

    /* default */ void showEndTitle(final String rightTitle) {
        this.rightTitle.setText(rightTitle);
        this.rightTitle.setVisibility(VISIBLE);
    }

    /* default */ void hideEndTitle() {
        rightTitle.setVisibility(GONE);
    }

    /* default */ void showTopLabel(final String topLabel) {
        this.topLabel.setText(topLabel);
    }

    /* default */ void showMainLabel(final String mainLabel) {
        this.mainLabel.setText(mainLabel);
    }

    /* default */ void showLevelText(final String text, final String textColor,
        final String backgroundColor) {
        try {
            levelDescription.setText(text);
            levelDescription.setTextColor(Color.parseColor(textColor));
            setLevelBackground(backgroundColor);
        } catch (final IllegalArgumentException e) {
            hideLevel();
        }
    }

    /* default */ void showLevelIcon(final String iconName) {
        levelIcon.setVisibility(VISIBLE);
        PicassoDiskLoader.get(getContext())
            .load(Uri.parse(iconName))
            .into(levelIcon);
    }

    /* default */ void hideLevelIcon() {
        levelIcon.setVisibility(GONE);
    }

    /* default */ void hideLevel() {
        levelContainer.setVisibility(GONE);
        decorateLogo(Boolean.FALSE);
    }

    /* default */ void setTextColor(final String textColor) {
        final int color = Color.parseColor(textColor);
        topTitle.setTextColor(color);
        mainTitle.setTextColor(color);
        rightTitle.setTextColor(color);
        mainLabel.setTextColor(color);
        topLabel.setTextColor(color);
    }

    /* default */ void onClick(final String link) {
        button.setOnClickListener(v -> onClickEvent(link));
    }

    private void onClickEvent(final String link) {
        if (onClickCallback != null) {
            if (tracker != null && tracking != null) {
                tracker.track(TAP, mergeData(toItem(tracking.getEventData()), extraData));
            }
            onClickCallback.onClick(link);
        }
    }

    private void decorateLogo(final boolean havePill) {
        final int marginStartEnd = getResources().getDimensionPixelSize(R.dimen.ui_1_75m);
        final ConstraintLayout.LayoutParams logoParams =
            (ConstraintLayout.LayoutParams) logo.getLayoutParams();
        if (havePill) {
            logoParams.topMargin = getResources().getDimensionPixelSize(R.dimen.ui_1_5m);
            logoParams.leftMargin = marginStartEnd;
            logoParams.rightMargin = marginStartEnd;
            logoParams.bottomMargin = 0;
            logo.setLayoutParams(logoParams);
        } else {
            logoParams.topMargin = getResources().getDimensionPixelSize(R.dimen.ui_2m);
            logoParams.leftMargin = marginStartEnd;
            logoParams.rightMargin = marginStartEnd;
            logoParams.bottomMargin = getResources().getDimensionPixelSize(R.dimen.ui_1_5m);
        }
        logo.setLayoutParams(logoParams);
    }

    private void setLevelBackground(final String backgroundColor) {
        try {
            levelContainer.setVisibility(VISIBLE);
            final int color = Color.parseColor(backgroundColor);
            final GradientDrawable shape = (GradientDrawable) getResources()
                .getDrawable(R.drawable.touchpoint_carousel_level_background);
            shape.setColor(color);
            levelContainer.setBackground(shape);
            decorateLogo(Boolean.TRUE);
        } catch (final IllegalArgumentException ie) {
            levelContainer.setVisibility(INVISIBLE);
        }
    }

    @Nullable
    @Override
    public TouchpointTracking getTracking() {
        return tracking;
    }

    public void setTracking(@Nullable final TouchpointTracking tracking) {
        this.tracking = tracking;
    }

    /**
     * Tints the pill asset
     *
     * @param textColor color to tint
     */
    public void tintPillAsset(final String textColor) {
        levelIcon.setColorFilter(Color.parseColor(textColor));
    }

    /**
     * Hides the top label view
     */
    public void hideTopLabel() {
        topLabel.setVisibility(GONE);
    }

    /**
     * Hides the main label view
     */
    public void hideMainLabel() {
        mainLabel.setVisibility(GONE);
    }

    /**
     * Hides the main title view
     */
    public void hideMainTitle() {
        mainTitle.setVisibility(GONE);
    }

    public void setOnClickCallback(@Nullable final OnClickCallback onClickCallback) {
        this.onClickCallback = onClickCallback;
    }

    public void setTracker(@Nullable final MLBusinessTouchpointTracker tracker) {
        this.tracker = tracker;
    }

    public void setExtraData(@Nullable final Map<String, Object> extraData) {
        this.extraData = extraData;
    }
}
