package com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.view.carousel.card;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.mercadolibre.android.mlbusinesscomponents.R;
import com.mercadolibre.android.mlbusinesscomponents.components.discount.CircleTransform;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.callback.OnClickCallback;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.carousel.CarouselCardTouchpoint;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.tracking.MLBusinessTouchpointTracker;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.tracking.TouchpointTrackeable;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.tracking.print.TouchpointTracking;
import com.mercadolibre.android.picassodiskcache.PicassoDiskLoader;
import java.util.Map;

import static com.mercadolibre.android.mlbusinesscomponents.components.utils.TrackingUtils.trackTap;

public class CarouselCardView extends CardView implements TouchpointTrackeable {

    private static final String REGULAR = "regular";
    private static final String SEMIBOLD = "semibold";

    private final ImageView logo;
    private final LinearLayout levelContainer;
    private final ImageView levelIcon;
    private final TextView levelDescription;
    private final TextView mainTitle;
    private final TextView topTitle;
    private final TextView rightTitle;
    private final TextView topLabel;
    private final TextView mainLabel;
    private final LinearLayout button;
    private final FrameLayout logoContainer;
    private final View logoOverlay;
    @Nullable private TouchpointTracking tracking;
    @Nullable private Map<String, Object> extraData;
    @Nullable private OnClickCallback onClickCallback;
    @Nullable private MLBusinessTouchpointTracker tracker;
    private boolean isMPInstalled = true;

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
        logoContainer = findViewById(R.id.touchpoint_carousel_card_logo_container);
        logoOverlay = findViewById(R.id.touchpoint_carousel_card_logo_overlay);
        presenter = new CarouselCardPresenter();
    }

    /**
     * Binds the model
     *
     * @param card the model
     */
    public void bind(final CarouselCardTouchpoint card, final double size) {
        presenter.onBind(card, this);
        ViewGroup.LayoutParams layoutParams = getLayoutParams();
        layoutParams.height = (int) (size * getResources().getDisplayMetrics().density);
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
        this.mainTitle.setVisibility(VISIBLE);
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
        this.mainLabel.setVisibility(VISIBLE);
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
        button.setClickable(isMPInstalled);
        if (isMPInstalled) {
            button.setOnClickListener(v -> onClickEvent(link));
        }
    }

    private void onClickEvent(final String link) {
        if (onClickCallback != null) {
            trackTap(tracker, tracking);
            onClickCallback.onClickWithTracking(link, tracking);
        }
    }

    private void decorateLogo(final boolean havePill) {
        final int marginStartEnd = getResources().getDimensionPixelSize(R.dimen.ui_1_75m);
        final LinearLayout.LayoutParams logoParams =
            (LinearLayout.LayoutParams) logoContainer.getLayoutParams();
        logoParams.leftMargin = marginStartEnd;
        logoParams.rightMargin = marginStartEnd;
        if (havePill) {
            logoParams.topMargin = getResources().getDimensionPixelSize(R.dimen.ui_1_5m);
            logoParams.bottomMargin = 0;
        } else {
            logoParams.topMargin = getResources().getDimensionPixelSize(R.dimen.ui_2m);
            logoParams.bottomMargin = getResources().getDimensionPixelSize(R.dimen.ui_1_5m);
        }
        logoContainer.setLayoutParams(logoParams);
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
            levelContainer.setVisibility(GONE);
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

    public void changeBackgroundColor(final String color) {
        try {
            setCardBackgroundColor(Color.parseColor(color));
        } catch (final IllegalArgumentException e) {
            //no-op
        }
    }

    public void setCanOpenMercadoPago(final boolean isMPInstalled) {
        this.isMPInstalled = isMPInstalled;
    }

    /**
     * Set all labels to a default color.
     */
    public void setDefaultColor() {
        topTitle.setTextColor(getResources().getColor(R.color.ui_components_black_color));
        mainTitle.setTextColor(getResources().getColor(R.color.ui_components_black_color));
        rightTitle.setTextColor(getResources().getColor(R.color.ui_components_black_color));
        mainLabel.setTextColor(getResources().getColor(R.color.ui_components_black_color));
        topLabel.setTextColor(getResources().getColor(R.color.ui_components_black_color));
    }

    /**
     * Returns the background color to the default color.
     */
    public void changeBackgroundColorToDefault() {
        try {
            setCardBackgroundColor(getResources().getColor(R.color.ui_meli_white));
        } catch (final IllegalArgumentException e) {
            //no-op
        }
    }

    /**
     * Changes the title color
     * @param color to change the title.
     */
    public void changeTitleColor(final String color) {
        try {
            topLabel.setTextColor(Color.parseColor(color));
        } catch (final IllegalArgumentException e) {
            topLabel.setTextColor(getResources().getColor(R.color.ui_meli_black));
        }
    }

    /**
     * Changes the title size.
     * @param size to change the title size to.
     */
    public void changeTitleSize(final int size) {
        try {
            topLabel.setTextSize(size);
        } catch (final IllegalArgumentException e) {
            topLabel.setTextSize(getResources().getDimension(R.dimen.ui_fontsize_xsmall));
        }
    }

    /**
     * Returns the title size to a default value.
     */
    public void changeTitleSizeToDefault() {
        try {
            topLabel.setTextSize(getResources().getDimension(R.dimen.ui_fontsize_xsmall));
        } catch (final IllegalArgumentException e) {
            //no op..
        }
    }

    /**
     * Changes the title font style.
     * @param weight font style to change to.
     */
    public void changeTitleFontStyle(final String weight) {
        try{
            if (Build.VERSION.SDK_INT < 23) {
                topLabel.setTextAppearance(getContext(), getStyle(weight));
            } else {
                topLabel.setTextAppearance(getStyle(weight));
            }
        } catch (final IllegalArgumentException e) {
            //no op..
        }
    }

    private int getStyle(final String weight) {
        switch(weight) {
        case REGULAR:
            return R.style.touchpoint_carousel_card_main_label;
        case SEMIBOLD:
        default:
            return R.style.touchpoint_carousel_card_top_label;
        }
    }

    /**
     * Returns the title font style to a default value
     */
    public void changeFontStyleToDefault() {
        try{
            if (Build.VERSION.SDK_INT < 23) {
                topLabel.setTextAppearance(getContext(), R.style.touchpoint_carousel_card_top_label);
            } else {
                topLabel.setTextAppearance(R.style.touchpoint_carousel_card_top_label);
            }
        } catch (final IllegalArgumentException e) {
            //no op..
        }
    }

    /**
     * Changes the subtitle font style.
     * @param weight to change the font style to.
     */
    public void changeSubtitleFontStyle(final String weight) {
        try{
            if (Build.VERSION.SDK_INT < 23) {
                mainLabel.setTextAppearance(getContext(), getStyle(weight));
            } else {
                mainLabel.setTextAppearance(getStyle(weight));
            }
        } catch (final IllegalArgumentException e) {
            //no op..
        }
    }

    /**
     * Returns the subtitle font style to a default value.
     */
    public void changeSubtitleFontStyleToDefault() {
        try{
            if (Build.VERSION.SDK_INT < 23) {
                mainLabel.setTextAppearance(getContext(), R.style.touchpoint_carousel_card_main_label);
            } else {
                mainLabel.setTextAppearance(R.style.touchpoint_carousel_card_main_label);
            }
        } catch (final IllegalArgumentException e) {
            //no op..
        }
    }

    /**
     * Changes the subtitle text color.
     * @param color to change the subtitle to.
     */
    public void changeSubtitleColor(final String color) {
        try {
            mainLabel.setTextColor(Color.parseColor(color));
        } catch (final IllegalArgumentException e) {
            mainLabel.setTextColor(getResources().getColor(R.color.ui_meli_black));
        }
    }

    /**
     * Changes the subtitle text size.
     * @param size to change the subtitle size to.
     */
    public void changeSubtitleSize(final int size) {
        try {
            mainLabel.setTextSize(size);
        } catch (final IllegalArgumentException e) {
            mainLabel.setTextSize(getResources().getDimension(R.dimen.ui_fontsize_xxsmall));
        }
    }

    /**
     * Returns the subtitle size to a default value.
     */
    public void changeSubtitleSizeToDefault() {
        try {
            mainLabel.setTextSize(getResources().getDimension(R.dimen.ui_fontsize_xxsmall));
        } catch (final IllegalArgumentException e) {
            //no op..
        }
    }

    /**
     * Enables the logo overlay.
     */
    public void enableLogoOverlay() {
        logoOverlay.setVisibility(VISIBLE);
    }

    /**
     * Disables the logo overlay.
     */
    public void disableLogoOverlay() {
        logoOverlay.setVisibility(GONE);
    }
}
