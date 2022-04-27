package com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.view.flex_cover_carousel.flex_cover_card;

import static com.mercadolibre.android.mlbusinesscomponents.common.Constants.NON_SIZE;

import android.animation.AnimatorInflater;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.mercadolibre.android.mlbusinesscomponents.R;
import com.mercadolibre.android.mlbusinesscomponents.components.row.model.TouchpointRowItemInterface;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.callback.OnClickCallback;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.cover_carousel.model.cover_card.CoverCardInterfaceModel;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.tracking.TouchpointTrackeable;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.tracking.print.TouchpointTracking;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.view.cover_carousel.CardTransformer;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.view.cover_carousel.cover_card.CoverCardInterfaceView;

public class FlexCoverCardView extends CardView implements TouchpointTrackeable, CoverCardInterfaceView, CardTransformer {

    private static final float CORNER_RADIUS_VALUE = 6f;

    private final FlexCoverCardPresenter presenter;
    private final SimpleDraweeView cardCoverImage;
    private final View skeletonView;
    private final LinearLayout cardCoverContent;

    @Nullable private TouchpointTracking tracking;

    @Nullable private OnClickCallback onClickCallback;

    public FlexCoverCardView(@NonNull final Context context) {
        this(context, null);
    }

    public FlexCoverCardView(@NonNull final Context context, @Nullable final AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FlexCoverCardView(@NonNull final Context context, @Nullable final AttributeSet attrs, final int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inflate(getContext(), R.layout.touchpoint_flex_cover_carousel_card_view, this);
        cardCoverImage = findViewById(R.id.touchpoint_flex_cover_carousel_card_image);
        skeletonView = findViewById(R.id.touchpoint_cover_carousel_card_image_skeleton);
        cardCoverContent = findViewById(R.id.touchpoint_flex_cover_carousel_card_content);
        presenter = new FlexCoverCardPresenter();

        setCornerRadius();
        onShowSkeleton();
    }

    private void setCornerRadius() {
        setRadius(TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP, CORNER_RADIUS_VALUE, getResources().getDisplayMetrics()
        ));
    }

    private void onShowSkeleton() {
        showSkeleton();
    }

    /**
     * binds the view.
     *
     * @param model the data to bind
     */
    @Override
    public void bind(final CoverCardInterfaceModel model) {
        bind(model, NON_SIZE);
    }

    /**
     * binds the view.
     *
     * @param model the data to bind
     * @param size the card's size
     */
    @Override
    public void bind(final CoverCardInterfaceModel model, final int size) {
       if (size != NON_SIZE) {
            setNewHeight(size);
        }
        presenter.bindView(model, this);
    }

    private void setNewHeight(final int size) {
        ViewGroup.LayoutParams layoutParams = getLayoutParams();
        layoutParams.height = size;
    }

    /**
     * Binds data from descriptions into an hybrid_row
     *
     * @param description the data to bind.
     */
    @Override
    public void setRow(final TouchpointRowItemInterface description) {

    }

    /**
     * Cover image for the card.
     *
     * @param cover url for the cover image.
     */
    @Override
    public void setCoverImage(final String cover) {
        cardCoverImage.setImageURI(cover);
        cardCoverImage.refreshDrawableState();
    }

    /**
     * Sets the click listener
     *
     * @param link the link
     */
    @Override
    public void setOnClick(final String link) {
        setClickable(true);
        setOnClickListener(v -> onClickEvent(link));
    }

    private void onClickEvent(final String link) {
        if (tracking != null) {
            onClickCallback.sendTapTracking(tracking);
        }
        onClickCallback.onClick(link);
    }

    /**
     * dissmiss the click action
     */
    @Override
    public void dismissClickable() {
        setClickable(false);
    }

    /**
     * sets the onclick callback
     *
     * @param onClickCallback
     */
    @Override
    public void setOnClickCallback(@Nullable final OnClickCallback onClickCallback) {
        this.onClickCallback = onClickCallback;
    }

    @Nullable
    @Override
    public TouchpointTracking getTracking() {
        return tracking;
    }

    @Override
    public int getCoverCardHeight() {
        return cardCoverImage.getLayoutParams().height + cardCoverContent.getLayoutParams().height;
    }

    @Override
    public void showSkeleton() {
        skeletonView.setVisibility(VISIBLE);
    }

    @Override
    public void hideSkeleton() {
        skeletonView.setVisibility(GONE);
    }

    @Override
    public boolean getSkeletonState() {
        return skeletonView.getVisibility() == VISIBLE;
    }

    @Override
    public void setPressAnimation() {
        presenter.setPressAnimationWithLink(this);
    }

    @Override
    public void setOnClickListenerWithAnimationAndLink(final String link, final TouchpointTracking tracking) {
        setClickable(true);
        setOnClickListener(v -> {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                v.setStateListAnimator(AnimatorInflater.loadStateListAnimator(
                    getContext(), R.drawable.cover_card_click_animation
                ));
            }
            setTracking(tracking);
            onClickEvent(link);
        });
    }

    @Override
    public void setTracking(@Nullable final TouchpointTracking tracking) {
        this.tracking = tracking;
    }

    @Override
    public FlexCoverCardView getView() {
        return this;
    }

    @Override
    public void setTopImageToClosedtStatus() {
        cardCoverImage.setAlpha(0.4f);
    }

    @Override
    public void setTopImageToDefaultStatus() {
        cardCoverImage.setAlpha(1f);
    }
}