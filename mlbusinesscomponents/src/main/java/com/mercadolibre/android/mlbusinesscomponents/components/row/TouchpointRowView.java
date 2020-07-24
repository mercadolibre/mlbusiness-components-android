package com.mercadolibre.android.mlbusinesscomponents.components.row;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ViewSwitcher;
import com.facebook.drawee.view.SimpleDraweeView;
import com.mercadolibre.android.mlbusinesscomponents.R;
import java.util.List;

public class TouchpointRowView extends ViewSwitcher {
    private static final String DEFAULT_STORE_LOGO = "default_store";
    private static final int SKELETON_INDEX = 1;
    private static final int VIEW_INDEX = 0;

    private final SimpleDraweeView leftImage;
    private final SimpleDraweeView leftImageAccessory;
    private final TextView mainTitle;
    private final TextView mainSubtitle;
    private final TextView rightTopLabel;
    private final TextView rightPrimaryLabel;
    private final TextView rightSecondaryLabel;
    private final TextView rightMiddleLabel;
    private final TextView rightBottomInfoText;
    private final SimpleDraweeView rightBottomInfoIcon;
    private final LinearLayout rightBottomInfoContainer;
    private final LinearLayout mainDescriptionContainer;
    private final TouchpointRowPresenter presenter;

    /**
     * Constructor
     *
     * @param context The execution context. **/
    public TouchpointRowView(final Context context) {
        this(context, null);
    }

    /**
     * Constructor
     *
     * @param context The execution context.
     * @param attrs The style attributes. **/
    public TouchpointRowView(final Context context, @Nullable final AttributeSet attrs) {
        super(context, attrs);
        inflate(context, R.layout.touchpoint_row_view, this);
        leftImage = findViewById(R.id.left_image);
        leftImageAccessory = findViewById(R.id.left_image_accessory);
        mainTitle = findViewById(R.id.main_title);
        mainSubtitle = findViewById(R.id.main_subtitle);
        rightTopLabel = findViewById(R.id.right_top_label);
        rightPrimaryLabel = findViewById(R.id.right_primary_label);
        rightSecondaryLabel = findViewById(R.id.right_secondary_label);
        rightMiddleLabel = findViewById(R.id.right_middle_label);
        mainDescriptionContainer = findViewById(R.id.main_description_container);
        rightBottomInfoContainer = findViewById(R.id.right_bottom_info_container);
        rightBottomInfoText = findViewById(R.id.right_bottom_info_text);
        rightBottomInfoIcon = findViewById(R.id.right_bottom_info_image);

        presenter = new TouchpointRowPresenter();
    }

    /**
     * Binds the model
     *
     * @param cardResponse the model **/
    public void bind(final TouchpointRowItem cardResponse) {
        presenter.onBind(cardResponse, this);
    }

    /**
     * Show skeleton **/
    public void showSkeleton() {
        setDisplayedChild(SKELETON_INDEX);
    }

    /**
     * Hide skeleton **/
    public void hideSkeleton() {
        setDisplayedChild(VIEW_INDEX);
    }

    /**
     * Show brand logo
     *
     * @param logo the image source **/
    public void showImage(final String logo) {
        /*ImageLoader.create()
            .withContainer(leftImage)
            .withSource(logo)
            .withDefaultLocalSource(DEFAULT_STORE_LOGO)
            .withPrefix(ImageLoader.DISCOUNTS_PAYERS_PREFIX)
            .load();*/
    }

    /**
     * Hide the brand logo **/
    public void hideImage() {
        leftImage.setVisibility(GONE);
    }

    /**
     * Show brand name
     *
     * @param title The brand name **/
    public void showTitle(final String title) {
        mainTitle.setText(title);
        mainTitle.setVisibility(VISIBLE);
    }

    /**
     * Hide brand name **/
    public void hideTitle() {
        mainTitle.setVisibility(GONE);
    }

    /**
     * Show subtitle
     *
     * @param subtitle The subtitle **/
    public void showSubtitle(final String subtitle) {
        mainSubtitle.setText(subtitle);
        mainSubtitle.setVisibility(VISIBLE);
    }

    /**
     * hide subtitle **/
    public void hideSubtitle() {
        mainSubtitle.setVisibility(GONE);
    }

    /**
     * Show topLabel
     *
     * @param topLabel The untilText **/
    public void showTopLabel(final String topLabel) {
        rightTopLabel.setText(topLabel);
        rightTopLabel.setVisibility(VISIBLE);
    }

    /**
     * Hide topLabel **/
    public void hideTopLabel() {
        rightTopLabel.setVisibility(GONE);
    }

    /**
     * Show discount mainLabel
     *
     * @param mainLabel The discount mainLabel **/
    public void showMainLabel(final String mainLabel) {
        rightPrimaryLabel.setText(mainLabel);
        rightPrimaryLabel.setVisibility(VISIBLE);
    }

    /**
     * Hide discount rightLabel **/
    public void hideRightLabel() {
        rightSecondaryLabel.setVisibility(GONE);
    }

    /**
     * Show discount rightLabel
     *
     * @param rightLabel The discount rightLabel **/
    public void showRightLabel(final String rightLabel) {
        rightSecondaryLabel.setText(rightLabel);
        rightSecondaryLabel.setVisibility(VISIBLE);
    }

    /**
     * Hide discount mainLabel */
    public void hideMainLabel() {
        rightPrimaryLabel.setVisibility(GONE);
    }

    /**
     * Show discount bottom label
     *
     * @param bottomLabel The discount bottom label
    */
    public void showBottomLabel(final String bottomLabel) {
        rightMiddleLabel.setText(bottomLabel);
        rightMiddleLabel.setVisibility(VISIBLE);
    }

    /**
     * Hide discount bottom label
    */
    public void hideBottomLabel() {
        rightMiddleLabel.setVisibility(GONE);
    }

    /**
     * Show level
     *
     * @param text The level text
     * @param textColor The text color
     * @param backgroundColor The background color
    */
    public void showLevelText(final String text, final String textColor,
        final String backgroundColor) {
        try {
            rightBottomInfoText.setText(text);
            rightBottomInfoText.setTextColor(Color.parseColor(textColor));
            setLevelBackground(backgroundColor);
        } catch (final IllegalArgumentException e) {
            hideLevel();
        }
    }

    /**
     * Show level
     *
     * @param iconName The name of resource
    */
    public void showLevelIcon(final String iconName) {
        rightBottomInfoIcon.setVisibility(VISIBLE);
        /*ImageLoader.create()
            .withContainer(rightBottomInfoIcon)
            .withSource(iconName)
            .withPrefix(ImageLoader.DISCOUNTS_PAYERS_PREFIX)
            .load();*/
    }

    /**
     * Hide level icon
    */
    public void hideLevelIcon() {
        rightBottomInfoIcon.setVisibility(GONE);
    }

    /**
     * Hide level
    */
    public void hideLevel() {
        rightBottomInfoContainer.setVisibility(INVISIBLE);
        /*decorateLogo(Boolean.FALSE);*/
    }

    /**
     * Show store distance
     *
     * @param mainDescription the labels.
    */
    public void showDescriptionLabels(final List<DescriptionItems> mainDescription) {
        if(mainDescription != null && !mainDescription.isEmpty()) {
            if(mainDescriptionContainer.getChildCount() > 0) {
                mainDescriptionContainer.removeAllViews();
            }
            mainDescriptionContainer.setVisibility(VISIBLE);
            addDescriptionLabels(mainDescription);
        }

    }

    private void addDescriptionLabels(final List<DescriptionItems> mainDescription) {
        for (DescriptionItems item: mainDescription) {
            switch (item.getType()) {
            case "image":
                addImageDescription(item.getContent(), item.getColor());
                break;
            case "text":
                addTextDescription(item.getContent(), item.getColor());
                break;
            default:
                // no op..
            }
        }
    }

    private void addTextDescription(final String content, final String color) {
        MainDescriptionLabelsText mainDescriptionLabelsText = new MainDescriptionLabelsText(getContext());
        mainDescriptionLabelsText.setText(content, color);
        mainDescriptionContainer.addView(mainDescriptionLabelsText);
    }

    private void addImageDescription(final String content, final String color) {
        MainDescriptionLabesImage mainDescriptionLabesImage = new MainDescriptionLabesImage(getContext());
        mainDescriptionLabesImage.setImage(content, color);
        mainDescriptionContainer.addView(mainDescriptionLabesImage);
    }

    /**
     * Hide store description
     *
    */
    public void hideDescriptionLabels() {
        if(mainDescriptionContainer.getChildCount() > 0) {
            mainDescriptionContainer.removeAllViews();
        }
        mainDescriptionContainer.setVisibility(GONE);
    }


    /**
     * Set on click listener
     *
     * @param link The deep link to launch
    */
    public void onClick(final String link) {
        setOnClickListener(view -> onClickEvent(link, view));
    }

    private void onClickEvent(final String link, final View view) {
        /*if (tapListener != null) {
            tapListener.tap(tracking);
        }
        IntentUtils.launchDeepLink(view.getContext(), link);*/
    }

    private void setLevelBackground(final String backgroundColor) {
        /*try {
            rightBottomInfoContainer.setVisibility(VISIBLE);
            decorateLogo(Boolean.TRUE);
            final int color = Color.parseColor(backgroundColor);
            final GradientDrawable shape = (GradientDrawable) getResources()
                .getDrawable(R.drawable.discounts_payers_level_background);
            shape.setColor(color);
            rightBottomInfoContainer.setBackground(shape);
            rightBottomInfoContainer.bringToFront();
        } catch (final IllegalArgumentException ie) {
            rightBottomInfoContainer.setVisibility(GONE);
        }*/
    }

}
