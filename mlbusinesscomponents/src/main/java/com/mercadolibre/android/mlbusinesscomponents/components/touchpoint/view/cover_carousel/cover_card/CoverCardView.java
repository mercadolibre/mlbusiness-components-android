package com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.view.cover_carousel.cover_card;

import android.content.Context;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.mercadolibre.android.mlbusinesscomponents.R;
import com.mercadolibre.android.mlbusinesscomponents.components.row.TouchpointRowView;
import com.mercadolibre.android.mlbusinesscomponents.components.row.model.TouchpointRowItemInterface;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.callback.OnClickCallback;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.cover_carousel.model.cover_card.CoverCardInterface;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.tracking.TouchpointTrackeable;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.tracking.print.TouchpointTracking;

import static com.mercadolibre.android.mlbusinesscomponents.common.Constants.NON_SIZE;

public class CoverCardView extends CardView implements TouchpointTrackeable, OnClickCallback {

    private static final float CORNER_RADIUS_VALUE = 6f;

    private final CoverCardPresenter presenter;
    private final LinearLayout cardCointaier;
    private final SimpleDraweeView cardCoverImage;
    private final TouchpointRowView cardCoverRow;
    private final View skeletonView;

    @Nullable private TouchpointTracking tracking;

    @Nullable private OnClickCallback onClickCallback;

    public CoverCardView(@NonNull final Context context) {
        this(context, null);
    }

    public CoverCardView(@NonNull final Context context, @Nullable final AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CoverCardView(@NonNull final Context context, @Nullable final AttributeSet attrs, final int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inflate(getContext(), R.layout.touchpoint_cover_carousel_card_view, this);
        cardCointaier = findViewById(R.id.touchpoint_cover_carousel_card_container);
        cardCoverImage = findViewById(R.id.touchpoint_cover_carousel_card_image);
        cardCoverRow = findViewById(R.id.touchpoint_cover_carousel_card_row);
        skeletonView = findViewById(R.id.touchpoint_cover_carousel_image_skeleton);
        presenter = new CoverCardPresenter(this);

        setCornerRadius();
    }

    private void setCornerRadius() {
        setRadius(TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP, CORNER_RADIUS_VALUE, getResources().getDisplayMetrics()
        ));
    }

    /**
     * binds the view.
     *
     * @param model the data to bind
     */
    public void bind(final CoverCardInterface model) {
        bind(model, NON_SIZE);
    }

    /**
     * binds the view.
     *
     * @param model the data to bind
     * @param size the card's size
     */
    public void bind(final CoverCardInterface model, final int size) {
        if (size != NON_SIZE) {
            setNewHeight(size);
        }
        presenter.bindView(model);
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
    public void setRow(final TouchpointRowItemInterface description) {
        cardCoverRow.bind(description);
        cardCoverRow.setOnClickCallback(this);
        cardCoverRow.removeRippleEffect();
    }

    /**
     * Cover image for the card.
     *
     * @param cover url for the cover image.
     */
    public void setCoverImage(final String cover) {
        cardCoverImage.setImageURI(cover);
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
     * Sets the click listener
     *
     * @param link the link
     * @param tracking the tracking data
     */
    public void setOnClick(final String link, @Nullable final TouchpointTracking tracking) {
        setClickable(true);
        onClick(link);
        this.tracking = tracking;
    }

    @Override
    public void onClick(final String deepLink) {
        if (onClickCallback != null) {
            setOnClickListener(v -> onClickCallback.onClick(deepLink));
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
     *
     * @param onClickCallback
     */
    public void setOnClickCallback(@Nullable final OnClickCallback onClickCallback) {
        this.onClickCallback = onClickCallback;
    }

    @Nullable
    @Override
    public TouchpointTracking getTracking() {
        return tracking;
    }

    public int getCoverCardHeight() {
        cardCoverRow.measure(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        return cardCoverImage.getLayoutParams().height + cardCoverRow.getMeasuredHeight();
    }

    public void showSkeleton() {
        skeletonView.setVisibility(VISIBLE);
        cardCoverRow.showSkeleton();
    }
}