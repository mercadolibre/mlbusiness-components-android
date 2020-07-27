package com.mercadolibre.android.mlbusinesscomponents.components.row;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.mercadolibre.android.mlbusinesscomponents.R;
import com.mercadolibre.android.mlbusinesscomponents.common.TouchpointAssetLoader;
import com.mercadolibre.android.mlbusinesscomponents.components.pill.model.PillResponseInterface;
import com.mercadolibre.android.mlbusinesscomponents.components.pill.view.RightBottomInfoView;
import com.mercadolibre.android.mlbusinesscomponents.components.row.model.DescriptionItemsInterface;
import com.mercadolibre.android.mlbusinesscomponents.components.row.model.TouchpointRowItemInterface;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.callback.OnClickCallback;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.view.carousel.card.AssetLoader;
import java.util.List;

public class TouchpointRowView extends LinearLayout implements OnClickCallback {

    private static final int TRANSLATION_PIXELS = 6;

    private final SimpleDraweeView leftImage;
    private final SimpleDraweeView leftImageAccessory;
    private final TextView mainTitle;
    private final TextView mainSubtitle;
    private final TextView rightTopLabel;
    private final TextView rightPrimaryLabel;
    private final TextView rightSecondaryLabel;
    private final TextView rightMiddleLabel;
    private final RightBottomInfoView rightBottomInfoContainer;
    private final LinearLayout mainDescriptionContainer;
    private final TouchpointRowPresenter presenter;
    private OnClickCallback onClickCallback;

    /**
     * Constructor
     *
     * @param context The execution context.
     **/
    public TouchpointRowView(final Context context) {
        this(context, null);
    }

    /**
     * Constructor
     *
     * @param context The execution context.
     * @param attrs The style attributes.
     **/
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
        rightBottomInfoContainer.bindViews();
        presenter = new TouchpointRowPresenter();
    }

    /**
     * Binds the model
     *
     * @param cardResponse the model
     **/
    public void bind(final TouchpointRowItemInterface cardResponse) {
        presenter.onBind(cardResponse, this);
    }

    /**
     * Show skeleton
     **/
    public void showRow() {
        setVisibility(VISIBLE);
    }

    /**
     * Hide skeleton
     **/
    public void hideRow() {
        setVisibility(GONE);
    }

    /**
     * Show brand logo
     *
     * @param logo the image source
     **/
    public void showImage(final String logo) {
        AssetLoader.getImage(logo, leftImage, (shouldLoadImage -> {
            leftImage.setVisibility(VISIBLE);

            if (shouldLoadImage) {
                TouchpointAssetLoader.create().withContainer(leftImage).withSource(logo).load();
            }
        }));
    }

    /**
     * Hide the brand logo
     **/
    public void hideImage() {
        leftImage.setVisibility(GONE);
    }

    /**
     * Show brand name
     *
     * @param title The brand name
     **/
    public void showTitle(final String title) {
        mainTitle.setText(title);
        mainTitle.setVisibility(VISIBLE);
    }

    /**
     * Hide brand name
     **/
    public void hideTitle() {
        mainTitle.setVisibility(GONE);
    }

    /**
     * Show subtitle
     *
     * @param subtitle The subtitle
     **/
    public void showSubtitle(final String subtitle) {
        mainSubtitle.setText(subtitle);
        mainSubtitle.setVisibility(VISIBLE);
    }

    /**
     * hide subtitle
     **/
    public void hideSubtitle() {
        mainSubtitle.setVisibility(GONE);
    }

    /**
     * Show topLabel
     *
     * @param topLabel The untilText
     **/
    public void showTopLabel(final String topLabel) {
        rightTopLabel.setText(topLabel);
        rightTopLabel.setVisibility(VISIBLE);
    }

    /**
     * Hide topLabel
     **/
    public void hideTopLabel() {
        rightTopLabel.setVisibility(GONE);
    }

    /**
     * Show discount mainLabel
     *
     * @param mainLabel The discount mainLabel
     **/
    public void showMainLabel(final String mainLabel) {
        rightPrimaryLabel.setText(mainLabel);
        rightPrimaryLabel.setVisibility(VISIBLE);
    }

    /**
     * Hide discount rightLabel
     **/
    public void hideRightLabel() {
        rightSecondaryLabel.setVisibility(GONE);
    }

    /**
     * Show discount rightLabel
     *
     * @param rightLabel The discount rightLabel
     **/
    public void showRightLabel(final String rightLabel) {
        rightSecondaryLabel.setText(rightLabel);
        rightSecondaryLabel.setVisibility(VISIBLE);
    }

    /**
     * Hide discount mainLabel
     */
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
     * Show store distance
     *
     * @param mainDescription the labels.
     */
    public void showDescriptionLabels(final List<DescriptionItemsInterface> mainDescription) {
        if (mainDescription != null && !mainDescription.isEmpty()) {
            if (mainDescriptionContainer.getChildCount() > 0) {
                mainDescriptionContainer.removeAllViews();
            }
            mainDescriptionContainer.setVisibility(VISIBLE);
            addDescriptionLabels(mainDescription);
        }
    }

    private void addDescriptionLabels(final List<DescriptionItemsInterface> mainDescription) {
        for (DescriptionItemsInterface item : mainDescription) {
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
     */
    public void hideDescriptionLabels() {
        if (mainDescriptionContainer.getChildCount() > 0) {
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
        if (onClickCallback != null) {
            onClickCallback.onClick(link);
        }
    }

    public void setOnClickCallback(final OnClickCallback onClickCallback) {
        this.onClickCallback = onClickCallback;
    }

    public void hideImageAccessory() {
        leftImageAccessory.setVisibility(GONE);
    }

    public void showImageAccessory(final String leftImageAccessory) {
        AssetLoader.getImage(leftImageAccessory, this.leftImageAccessory, (shouldLoadImage -> {
            this.leftImageAccessory.setVisibility(VISIBLE);

            if (shouldLoadImage) {
                TouchpointAssetLoader.create().withContainer(this.leftImageAccessory).withSource(leftImageAccessory).load();
            }
        }));
    }

    public void setRightLabelsToBlockedStatus() {
        rightMiddleLabel.setAlpha(0.4f);
        rightPrimaryLabel.setAlpha(0.4f);
        rightSecondaryLabel.setAlpha(0.4f);
    }

    public void setRightLabelsToDefaultStatus() {
        rightMiddleLabel.setAlpha(1f);
        rightPrimaryLabel.setAlpha(1f);
        rightSecondaryLabel.setAlpha(1f);
    }

    public void hideRightBottomInfo() {
        rightBottomInfoContainer.hideRightBottomInfoView();
    }

    public void showRightBottomInfo(final PillResponseInterface pill) {
        rightBottomInfoContainer.bind(pill);
        rightBottomInfoContainer.setTranslationX(TRANSLATION_PIXELS * getResources().getDisplayMetrics().density);
        rightBottomInfoContainer.setTranslationY(-TRANSLATION_PIXELS * getResources().getDisplayMetrics().density);;
    }
}
