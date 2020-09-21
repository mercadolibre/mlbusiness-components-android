package com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.view.carousel;

import android.content.Context;
import android.graphics.Rect;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import com.mercadolibre.android.mlbusinesscomponents.R;
import com.mercadolibre.android.mlbusinesscomponents.common.TouchpointImageLoader;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.carousel.Carousel;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.carousel.CarouselCard;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.tracking.TouchpointTrackeable;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.view.AbstractTouchpointChildView;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.view.carousel.card.CarouselCardView;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.view.carousel.card.TrackListener;
import com.mercadolibre.android.mlbusinesscomponents.components.utils.ScaleUtils;
import java.util.ArrayList;
import java.util.List;

import static androidx.recyclerview.widget.RecyclerView.SCROLL_STATE_IDLE;
import static com.mercadolibre.android.mlbusinesscomponents.components.utils.TrackingUtils.trackPrint;
import static com.mercadolibre.android.mlbusinesscomponents.components.utils.TrackingUtils.trackShow;

public class CarouselView extends AbstractTouchpointChildView<Carousel> {

    private final CarouselAdapter adapter;
    private final RecyclerView recyclerView;
    private final Rect rect;
    private Carousel model;
    private TrackListener trackListener;
    private TouchpointImageLoader imageLoader;
    private HorizontalScrollingEnhancer horizontalScrollingEnhancer;
    private CarouselCardView templateCardView;
    private int currentHeight = 0;

    /**
     * Constructor
     *
     * @param context The execution context.
     */
    public CarouselView(final Context context) {
        this(context, null);
    }

    /**
     * Constructor
     *
     * @param context The execution context.
     * @param attrs The style attributes.
     */
    public CarouselView(final Context context, @Nullable final AttributeSet attrs) {
        this(context, attrs, 0);
    }

    /**
     * Constructor
     *
     * @param context The execution context.
     * @param attrs The style attributes.
     * @param defStyleAttr Default style attributes.
     */
    public CarouselView(final Context context, @Nullable final AttributeSet attrs, final int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inflate(getContext(), R.layout.touchpoint_carousel_view, this);
        recyclerView = findViewById(R.id.touchpoint_carousel_recycler_view);
        adapter = new CarouselAdapter();
        rect = new Rect();
        templateCardView = new CarouselCardView(getContext());
        initList(context);
    }

    private void initList(final Context context) {
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull final RecyclerView recyclerView, final int state) {
                super.onScrollStateChanged(recyclerView, state);
                if (state == SCROLL_STATE_IDLE) {
                    if (trackListener == null) {
                        print();
                    } else {
                        trackListener.print();
                    }
                }
            }
        });

        if (horizontalScrollingEnhancer != null) {
            horizontalScrollingEnhancer.enhanceHorizontalScroll(recyclerView);
        }
    }

    public void setTrackListener(final TrackListener trackListener) {
        this.trackListener = trackListener;
    }

    public void setHorizontalScrollingEnhancer(
        final HorizontalScrollingEnhancer horizontalScrollingEnhancer) {
        this.horizontalScrollingEnhancer = horizontalScrollingEnhancer;
    }

    public void setImageLoader(final TouchpointImageLoader imageLoader) {
        adapter.setImageLoader(imageLoader);
    }

    @Override
    public void bind(@Nullable final Carousel model) {
        if (model != null && model.isValid() && !model.equals(this.model)) {
            this.model = model;
            adapter.setCanOpenMercadoPago(isMPInstalled);
            adapter.setOnClickCallback(onClickCallback);
            adapter.setTracker(tracker);
            adapter.setExtraData(tracking);
            showCards(model.getItems(), model);
            decorate();
            if (trackListener == null) {
                trackShow(tracker, new ArrayList<>(model.getItems()));
            }
        }
    }

    private void decorate() {
        if (additionalInsets != null) {
            setPadding(0, (int) ScaleUtils.getPxFromDp(getContext(), additionalInsets.getTop()),
                0, (int) ScaleUtils.getPxFromDp(getContext(), additionalInsets.getBottom()));
            if (recyclerView.getItemDecorationCount() == 0) {
                recyclerView.addItemDecoration(
                    new CarouselDecorator((int) ScaleUtils.getPxFromDp(getContext(), additionalInsets.getLeft()),
                        (int) ScaleUtils.getPxFromDp(getContext(), additionalInsets.getRight())));
            }
        }
    }

    private void showCards(final List<CarouselCard> cards, final HeightCalculatorDelegate heightCalculator) {
        if (cards != null && !cards.isEmpty()) {
            templateCardView.bind(cards.get(heightCalculator.getMaxHeightItemIndex()));
            final int measureSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
            templateCardView.measure(measureSpec, measureSpec);
            if (currentHeight != templateCardView.getMeasuredHeight()) {
                currentHeight = templateCardView.getMeasuredHeight();
                adapter.setCardHeight(currentHeight);
            }
            adapter.setItems(cards);
        }
    }

    @Override
    public void print() {
        recyclerView.getHitRect(rect);
        findData(recyclerView);
        trackPrint(tracker, printProvider);
    }

    private void findData(final ViewGroup viewGroup) {
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            final View child = viewGroup.getChildAt(i);
            if (child instanceof ViewGroup && !(child instanceof TouchpointTrackeable)) {
                findData((ViewGroup) child);
            }
            if (shouldTrackPrint(child)) {
                printProvider.accumulateData(((TouchpointTrackeable) child).getTracking());
            }
        }
    }

    private boolean shouldTrackPrint(final View child) {
        return child instanceof TouchpointTrackeable && child.getLocalVisibleRect(rect);
    }

    @Override
    public int getStaticHeight() {
        return getResources().getDimensionPixelSize(R.dimen.carousel_static_height);
    }
}
