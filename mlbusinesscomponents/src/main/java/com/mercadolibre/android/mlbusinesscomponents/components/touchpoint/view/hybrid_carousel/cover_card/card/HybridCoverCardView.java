package com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.view.hybrid_carousel.cover_card.card;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.mercadolibre.android.mlbusinesscomponents.R;
import com.mercadolibre.android.mlbusinesscomponents.components.row.TouchpointRowView;
import com.mercadolibre.android.mlbusinesscomponents.components.row.model.test.TouchpointRowItem;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.callback.OnClickCallback;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.hybrid_carousel.model.HybridCarouselCardContainerModel;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.tracking.TouchpointTrackeable;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.tracking.print.TouchpointTracking;

import static com.mercadolibre.android.mlbusinesscomponents.common.Constants.NON_SIZE;

public class HybridCoverCardView extends CardView implements TouchpointTrackeable {

    private HybridCoverCardPresenter presenter;
    private final LinearLayout cardCointaier;
    private final SimpleDraweeView cardCoverImage;
    private final TouchpointRowView cardCoverRow;

    @Nullable private TouchpointTracking tracking;

    @Nullable private OnClickCallback onClickCallback;

    public HybridCoverCardView(@NonNull final Context context) {
        this(context, null);
    }

    public HybridCoverCardView(@NonNull final Context context, @Nullable final AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HybridCoverCardView(@NonNull final Context context, @Nullable final AttributeSet attrs, final int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inflate(getContext(), R.layout.touchpoint_hybrid_carousel_cover_card_view, this);
        cardCointaier = findViewById(R.id.touchpoint_hybrid_carousel_cover_card_container);
        cardCoverImage = findViewById(R.id.touchpoint_hybrid_carousel_cover_card_image);
        cardCoverRow = findViewById(R.id.touchpoint_hybrid_carousel_cover_card_row);
        presenter = new HybridCoverCardPresenter(this);
    }

    /**
     * binds the view.
     *
     * @param model the data to bind
     */
    public void bind(final HybridCarouselCardContainerModel model) {
        bind(model, NON_SIZE);
    }

    /**
     * binds the view.
     *
     * @param model the data to bind
     * @param size the card's size
     */
    public void bind(final HybridCarouselCardContainerModel model, final int size) {
        tracking = model.getTracking();
        if(size != NON_SIZE) {
            setNewHeight(size);
        }
        presenter.bindView(model);
    }

    private void setNewHeight(final int size) {
        ViewGroup.LayoutParams layoutParams = getLayoutParams();
        layoutParams.height = size;
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

    public void init() {
        cardCoverRow.bind(new TouchpointRowItem());
    }
}