package com.mercadolibre.android.mlbusinesscomponents.components.pill.view;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.mercadolibre.android.mlbusinesscomponents.R;
import com.mercadolibre.android.mlbusinesscomponents.common.TouchpointAssetLoader;
import com.mercadolibre.android.mlbusinesscomponents.components.pill.model.PillResponseInterface;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.view.carousel.card.AssetLoader;

public class RightBottomInfoView extends LinearLayout {

    private TextView rightBottomInfoText;
    private SimpleDraweeView rightBottomInfoIcon;

    public RightBottomInfoView(final Context context) {
        this(context, null);
    }

    public RightBottomInfoView(final Context context, @Nullable final AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RightBottomInfoView(final Context context, @Nullable final AttributeSet attrs, final int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * Binds the pill
     *
     * @param pill the data to create the view
     */
    public void bind(PillResponseInterface pill) {

        if (TextUtils.isEmpty(pill.getIcon())) {
            hideLevelIcon();
        } else {
            showRightBottomInfoIcon(pill.getIcon());
            if (isValidPillFormat(pill)) {
                tintRightBottomInfoIcon(pill.getFormat().getTextColor());
            }
        }
        showRightBottomInfoText(pill.getLabel(), pill.getFormat().getTextColor(),
            pill.getFormat().getBackgroundColor());
    }

    public void bindViews() {
        rightBottomInfoText = findViewById(R.id.right_bottom_info_text);
        rightBottomInfoIcon = findViewById(R.id.right_bottom_info_image);
    }

    /**
     * Hide level
     */
    public void hideRightBottomInfoView() {
        setVisibility(GONE);
    }

    private void showRightBottomInfoText(final String text, final String textColor,
        final String backgroundColor) {
        try {
            rightBottomInfoText.setText(text);
            rightBottomInfoText.setTextColor(Color.parseColor(textColor));
            setLevelBackground(backgroundColor);
        } catch (final IllegalArgumentException e) {
            hideRightBottomInfoView();
        }
    }

    private void showRightBottomInfoIcon(final String iconName) {
        rightBottomInfoIcon.setVisibility(VISIBLE);
        AssetLoader.getImage(iconName, rightBottomInfoIcon, (shouldLoadImage -> {
            rightBottomInfoIcon.setVisibility(VISIBLE);

            if (shouldLoadImage) {
                TouchpointAssetLoader.create().withContainer(rightBottomInfoIcon).withSource(iconName).load();
            }
        }));
    }

    private void hideLevelIcon() {
        rightBottomInfoIcon.setVisibility(GONE);
    }

    private void tintRightBottomInfoIcon(final String textColor) {
        try {
            rightBottomInfoIcon.setColorFilter(Color.parseColor(textColor));
        } catch (Exception e) {
            // no op..
        }
    }

    private void setLevelBackground(final String backgroundColor) {
        try {
            setVisibility(VISIBLE);
            final int color = Color.parseColor(backgroundColor);
            final GradientDrawable shape = (GradientDrawable) getResources()
                .getDrawable(R.drawable.level_background);
            shape.setColor(color);
            setBackground(shape);
        } catch (final IllegalArgumentException ie) {
            setVisibility(GONE);
        }
    }

    private boolean isValidPillFormat(@org.jetbrains.annotations.Nullable final PillResponseInterface pill) {
        return pill != null && pill.getFormat() != null &&
            !TextUtils.isEmpty(pill.getFormat().getTextColor());
    }
}
