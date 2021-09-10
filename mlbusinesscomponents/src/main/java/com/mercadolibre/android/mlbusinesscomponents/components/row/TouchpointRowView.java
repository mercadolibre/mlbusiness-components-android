package com.mercadolibre.android.mlbusinesscomponents.components.row;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Rect;
import androidx.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;
import android.widget.ViewSwitcher;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import com.facebook.drawee.view.SimpleDraweeView;
import com.mercadolibre.android.mlbusinesscomponents.R;
import com.mercadolibre.android.mlbusinesscomponents.common.TouchpointAssetLoader;
import com.mercadolibre.android.mlbusinesscomponents.common.TouchpointImageLoader;
import com.mercadolibre.android.mlbusinesscomponents.components.pickup.PickUpView;
import com.mercadolibre.android.mlbusinesscomponents.components.pill.model.PillResponseInterface;
import com.mercadolibre.android.mlbusinesscomponents.components.pill.view.RightBottomInfoView;
import com.mercadolibre.android.mlbusinesscomponents.components.pickup.model.DescriptionItemsInterface;
import com.mercadolibre.android.mlbusinesscomponents.components.row.model.MainTitleTopInterface;
import com.mercadolibre.android.mlbusinesscomponents.components.row.model.TouchpointRowItemInterface;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.callback.OnClickCallback;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.view.carousel.card.AssetLoader;
import java.util.List;

public class TouchpointRowView extends ViewSwitcher implements OnClickCallback {

    private static final int TRANSLATION_PIXELS_X = 6;
    private static final int TRANSLATION_PIXELS_Y = 5;
    private static final int SKELETON_INDEX = 1;
    private static final int VIEW_INDEX = 0;

    private static final float DISABLE_VIEW = 0.4f;
    private static final float ENABLE_VIEW = 1f;

    private final SimpleDraweeView leftImage;
    private final SimpleDraweeView leftImageAccessory;
    private final TextView mainTitleTop;
    private final TextView mainTitle;
    private final TextView mainSubtitle;
    private final View rightContainer;
    private final TextView rightTopLabel;
    private final TextView rightPrimaryLabel;
    private final TextView rightSecondaryLabel;
    private final TextView rightMiddleLabel;
    private final RightBottomInfoView rightBottomInfoContainer;
    private final PickUpView mainDescriptionContainer;
    private final PickUpView mainCharacteristicsContainer;
    private final PickUpView cardStatusContainer;
    private final TouchpointRowPresenter presenter;
    private final View rippleView;
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
        if (leftImage.getHierarchy() != null
                && leftImage.getHierarchy().getRoundingParams() != null ) {
            leftImage.getHierarchy().getRoundingParams().setPaintFilterBitmap(true);
        }
        leftImageAccessory = findViewById(R.id.left_image_accessory);
        mainTitleTop = findViewById(R.id.main_title_top);
        mainTitle = findViewById(R.id.main_title);
        mainSubtitle = findViewById(R.id.main_subtitle);
        rightContainer = findViewById(R.id.discounts_payers_list_row_label_container);
        rightTopLabel = findViewById(R.id.right_top_label);
        rightPrimaryLabel = findViewById(R.id.right_primary_label);
        rightSecondaryLabel = findViewById(R.id.right_secondary_label);
        rightMiddleLabel = findViewById(R.id.right_middle_label);
        mainDescriptionContainer = findViewById(R.id.main_description_container);
        mainCharacteristicsContainer = findViewById(R.id.main_characteristics_container);
        cardStatusContainer = findViewById(R.id.card_status_container);
        rightBottomInfoContainer = findViewById(R.id.right_bottom_info_container);
        rippleView = findViewById(R.id.discounts_payers_list_row_click);
        rightBottomInfoContainer.bindViews();
        presenter = new TouchpointRowPresenter();
        post(this::analyzeComponentsPositions);
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
     */
    public void showSkeleton() {
        setDisplayedChild(SKELETON_INDEX);
    }

    /**
     * Hide skeleton
     */
    public void hideSkeleton() {
        setDisplayedChild(VIEW_INDEX);
    }

    /**
     * Show brand logo
     *
     * @param logo the image source
     **/
    public void showImage(final String logo) {
        AssetLoader.getImage(logo, leftImage, (shouldLoadImage -> {
            if (shouldLoadImage) {
                TouchpointAssetLoader.create().withContainer(leftImage).withSource(logo).load();
            }
        }));

        leftImage.setVisibility(VISIBLE);
    }

    /**
     * Hide the brand logo
     **/
    public void hideImage() {
        leftImage.setVisibility(GONE);
    }

    /**
     * Show main title top
     * @param title
     */
    public void showMainTitleTop(final MainTitleTopInterface title) {
        mainTitleTop.setText(title.getText());
        mainTitleTop.setTextColor(Color.parseColor(title.getColor()));
        mainTitleTop.setVisibility(VISIBLE);
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
     * Set title and subtitle to closed status
     **/
    public void setTitleToClosedStatus() {
        mainTitle.setTextColor(Color.parseColor("#737373"));
        mainSubtitle.setAlpha(DISABLE_VIEW);
    }

    /**
     * Set default title and subtitle closed status
     **/
    public void setDefaultTitleClosedStatus() {
        mainTitle.setAlpha(ENABLE_VIEW);
        mainSubtitle.setAlpha(ENABLE_VIEW);
    }

    /**
     * Hide main title
     */
    public void hideMainTitleTop() {
       mainTitleTop.setVisibility(GONE);
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
        if (mainDescriptionContainer != null) {
            mainDescriptionContainer.bindViews(mainDescription);
        }
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
            if (shouldLoadImage) {
                TouchpointAssetLoader.create().withContainer(this.leftImageAccessory).withSource(leftImageAccessory).load();
            }
        }));

        this.leftImageAccessory.setVisibility(VISIBLE);
    }

    public void setRightLabelsToBlockedStatus() {
        rightMiddleLabel.setAlpha(DISABLE_VIEW);
        rightPrimaryLabel.setAlpha(DISABLE_VIEW);
        rightSecondaryLabel.setAlpha(DISABLE_VIEW);
    }

    public void setRightLabelsToDefaultStatus() {
        rightContainer.setAlpha(ENABLE_VIEW);
        rightMiddleLabel.setAlpha(ENABLE_VIEW);
        rightPrimaryLabel.setAlpha(ENABLE_VIEW);
        rightSecondaryLabel.setAlpha(ENABLE_VIEW);
    }

    public void hideRightBottomInfo() {
        rightBottomInfoContainer.hideRightBottomInfoView();
    }

    public void showRightBottomInfo(final PillResponseInterface pill) {
        rightBottomInfoContainer.bind(pill);
        rightBottomInfoContainer.setTranslationX(TRANSLATION_PIXELS_X * getResources().getDisplayMetrics().density);
        rightBottomInfoContainer.setTranslationY(-TRANSLATION_PIXELS_Y * getResources().getDisplayMetrics().density);
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
     * Show store distance
     *
     * @param mainDescription the labels.
     */
    public void showCharacteristicsLabels(final List<DescriptionItemsInterface> mainDescription) {
        if (mainCharacteristicsContainer != null) {
            mainCharacteristicsContainer.bindViews(mainDescription);
        }
    }

    /**
     * Hide store characteristics
     */
    public void hideCharacteristicsLabels() {
        if (mainCharacteristicsContainer.getChildCount() > 0) {
            mainCharacteristicsContainer.removeAllViews();
        }
        mainCharacteristicsContainer.setVisibility(GONE);
    }

    /**
     * Hide Card Status
     */
    public void hideStatusDescription() {
        if (cardStatusContainer.getChildCount() > 0) {
            cardStatusContainer.removeAllViews();
        }
        setMainCharacteristicMarginBottom(getResources().getDimensionPixelSize(R.dimen.ui_2m));
        cardStatusContainer.setVisibility(GONE);
    }

    /**
     * Show card status
     *
     * @param cardStatus the status.
     */
    public void showStatusDescription(final List<DescriptionItemsInterface> cardStatus) {
        if (cardStatusContainer != null) {
            cardStatusContainer.bindViews(cardStatus);
            setMainCharacteristicMarginBottom(0);
        }
    }

    public void removeRippleEffect() {
        rippleView.setVisibility(GONE);
    }

    /**
     * Sets left image to default status
     */
    public void setLeftImageToDefaultStatus() {
        leftImage.setAlpha(ENABLE_VIEW);
        leftImageAccessory.setAlpha(ENABLE_VIEW);
    }

    public void setLeftImageToClosedStatus() {
        leftImage.setAlpha(DISABLE_VIEW);
        leftImageAccessory.setAlpha(DISABLE_VIEW);
    }

    public void setRightContainerToClosedStatus() {
        rightContainer.setAlpha(DISABLE_VIEW);
    }

    private void setMainCharacteristicMarginBottom(final int dimensionPixelSize) {
        ConstraintLayout.LayoutParams layoutParams =
            (ConstraintLayout.LayoutParams) mainCharacteristicsContainer.getLayoutParams();
        if (layoutParams != null) {
            layoutParams.setMargins(0, 0, 0, dimensionPixelSize);
        }
    }

    private Rect getRectFromView(final View view, final int[] position) {
        return new Rect(
            position[0], position[1],
            position[0] + view.getMeasuredWidth(),
            position[1] + view.getMeasuredHeight()
        );
    }

    private boolean areViewsOverlapping(final Rect firstViewRect, final Rect secondViewRect, final Rect thirdViewRect,
        final Rect intersectedView) {
        return firstViewRect.intersect(intersectedView) || secondViewRect.intersect(intersectedView) ||
            thirdViewRect.intersect(intersectedView);
    }

    private void analyzeComponentsPositions() {
        final int[] mainDescriptionContainerPositions = new int[2];
        final int[] mainCharacteristicsContainerPositions = new int[2];
        final int[] cardStatusContainerPosition = new int[2];
        final int[] rightContainerPosition = new int[2];

        mainDescriptionContainer.getLocationOnScreen(mainDescriptionContainerPositions);
        mainCharacteristicsContainer.getLocationOnScreen(mainCharacteristicsContainerPositions);
        cardStatusContainer.getLocationOnScreen(cardStatusContainerPosition);
        rightContainer.getLocationOnScreen(rightContainerPosition);

        final Rect mainDescriptionRect =
            getRectFromView(mainDescriptionContainer, mainDescriptionContainerPositions);
        final Rect mainCharacteristicsRect =
            getRectFromView(mainCharacteristicsContainer, mainCharacteristicsContainerPositions);
        final Rect cardStatusRect = getRectFromView(cardStatusContainer, cardStatusContainerPosition);
        final Rect rightContainerRect = getRectFromView(rightContainer, rightContainerPosition);

        final boolean viewsAreOverlapping =
            areViewsOverlapping(mainDescriptionRect, mainCharacteristicsRect, cardStatusRect, rightContainerRect);

        if (viewsAreOverlapping) {
            setNewConstraintsForOverlappingViews();
        }
    }

    private void setNewConstraintsForOverlappingViews() {
        final ConstraintLayout constraintContainer = findViewById(R.id.touchpoint_row_view_constraint_container);
        final ConstraintSet constraintSet = new ConstraintSet();

        constraintSet.clone(constraintContainer);
        constraintSet.connect(R.id.main_description_container, ConstraintSet.TOP,
            R.id.discounts_payers_list_row_label_container, ConstraintSet.BOTTOM);
        constraintSet.connect(R.id.main_description_container, ConstraintSet.END,
            ConstraintSet.PARENT_ID, ConstraintSet.END);
        constraintSet.connect(R.id.discounts_payers_list_row_label_container, ConstraintSet.TOP, R.id.main_title,
            ConstraintSet.TOP);
        constraintSet.connect(R.id.discounts_payers_list_row_label_container, ConstraintSet.BOTTOM,
            R.id.main_description_container, ConstraintSet.TOP);
        constraintSet.clear(R.id.discounts_payers_list_row_label_container, ConstraintSet.BOTTOM);
        constraintSet.applyTo(constraintContainer);

        requestLayout();
    }
}
