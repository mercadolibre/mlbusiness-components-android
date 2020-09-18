package com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.view.hybrid_carousel;

import android.content.Context;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.AttributeSet;
import com.mercadolibre.android.mlbusinesscomponents.R;
import com.mercadolibre.android.mlbusinesscomponents.common.TouchpointImageLoader;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.callback.OnClickCallback;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.hybrid_carousel.TouchpointItemType;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.hybrid_carousel.model.HybridCarouselCardContainerModel;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.hybrid_carousel.response.HybridCarousel;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.view.AbstractTouchpointChildView;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.view.carousel.CarouselDecorator;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.view.carousel.HeightCalculatorDelegate;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.view.carousel.HorizontalScrollingEnhancer;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.view.carousel.card.TrackListener;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.view.hybrid_carousel.default_card.card.HybridCarouselDefaultCardView;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.view.hybrid_carousel.view_more.card.HybridCarouselViewMoreCardView;
import com.mercadolibre.android.mlbusinesscomponents.components.utils.ScaleUtils;
import java.util.List;

import static androidx.recyclerview.widget.RecyclerView.SCROLL_STATE_IDLE;

public class HybridCarouselView extends AbstractTouchpointChildView<HybridCarousel> implements HybridCarouselInterfaceView {

    private HybridCarouselPresenter presenter;
    private HybridCarouselAdater carouselAdapter;
    private RecyclerView recyclerView;
    private TrackListener trackListener;
    private HorizontalScrollingEnhancer horizontalScrollingEnhancer;
    private int currentHeight = 0;
    private HybridCarouselDefaultCardView defaultTemplateView;
    private HybridCarouselViewMoreCardView viewMoreTemplateView;

    public HybridCarouselView(@NonNull final Context context) {
        this(context, null);
    }

    public HybridCarouselView(@NonNull final Context context, @Nullable final AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HybridCarouselView(@NonNull final Context context, @Nullable final AttributeSet attrs, final int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inflate(getContext(), R.layout.touchpoint_hybrid_carousel_view, this);
        recyclerView = findViewById(R.id.touchpoint_hybrid_carousel_recycler_view);
        presenter = new HybridCarouselPresenter(this);
        carouselAdapter = new HybridCarouselAdater();
        defaultTemplateView = new HybridCarouselDefaultCardView(getContext());
        viewMoreTemplateView = new HybridCarouselViewMoreCardView(getContext());
        initList(context);
    }

    @Override
    public void bind(@Nullable final HybridCarousel model) {
        if (model == null) {
            setVisibility(GONE);
            return;
        }
        decorate();
        presenter.mapResponse(model);
    }

    @Override
    public int getStaticHeight() {
        return getResources().getDimensionPixelSize(R.dimen.hybrid_carousel_static_height);
    }

    @Override
    public void print() {

    }

    @Override
    public void showItems(final List<HybridCarouselCardContainerModel> items,
        final HeightCalculatorDelegate heightCalculator) {

        if (items == null && items.isEmpty()) {
            return;
        }

        final HybridCarouselCardContainerModel model = items.get(heightCalculator.getMaxHeightItemIndex());
        if (TouchpointItemType.DEFAULT.ordinal() == model.getContent().getItemType() ) {
            defaultTemplateView.bind(model);
            setItemsWithHeight(defaultTemplateView, items);
        } else {
            viewMoreTemplateView.bind(model);
            setItemsWithHeight(viewMoreTemplateView, items);
        }
    }

    private <T extends View> void setItemsWithHeight(final T templateView,
        final List<HybridCarouselCardContainerModel> items) {
        final int measureSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        templateView.measure(measureSpec, measureSpec);
        if (currentHeight != templateView.getMeasuredHeight()) {
            currentHeight = templateView.getMeasuredHeight();
            carouselAdapter.setCardHeight(currentHeight);
            carouselAdapter.setItems(items);
        }
    }

    private void initList(final Context context) {
        recyclerView.setAdapter(carouselAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull final RecyclerView recyclerView, final int state) {
                super.onScrollStateChanged(recyclerView, state);
                if (state == SCROLL_STATE_IDLE) {
                    if (trackListener != null) {
                        trackListener.print();
                    }
                }
            }
        });

        if (horizontalScrollingEnhancer != null) {
            horizontalScrollingEnhancer.enhanceHorizontalScroll(recyclerView);
        }
    }

    /**
     * Sets the image loader
     *
     * @param imageLoader the image loader delegate
     */
    public void setImageLoader(final TouchpointImageLoader imageLoader) {
        carouselAdapter.setImageLoader(imageLoader);
    }

    /**
     * Sets the click callback
     *
     * @param onClickCallback the callback
     */
    public void setOnClickCallback(@Nullable final OnClickCallback onClickCallback) {
        carouselAdapter.setOnClickCallback(onClickCallback);
    }

    public void setTrackListener(final TrackListener trackListener) {
        this.trackListener = trackListener;
    }

    public void setHorizontalScrollingEnhancer(
        final HorizontalScrollingEnhancer horizontalScrollingEnhancer) {
        this.horizontalScrollingEnhancer = horizontalScrollingEnhancer;
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
}
