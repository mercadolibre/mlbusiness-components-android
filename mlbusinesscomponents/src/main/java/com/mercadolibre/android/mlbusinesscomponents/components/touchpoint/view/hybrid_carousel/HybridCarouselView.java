package com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.view.hybrid_carousel;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import com.mercadolibre.android.mlbusinesscomponents.R;
import com.mercadolibre.android.mlbusinesscomponents.common.TouchpointImageLoader;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.callback.OnClickCallback;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.hybrid_carousel.model.HybridCarouselCardContainerModel;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.hybrid_carousel.response.HybridCarousel;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.view.AbstractTouchpointChildView;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.view.carousel.HeightCalculatorDelegate;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.view.carousel.HorizontalScrollingEnhancer;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.view.carousel.card.TrackListener;
import java.util.List;

import static android.support.v7.widget.RecyclerView.SCROLL_STATE_IDLE;

public class HybridCarouselView extends AbstractTouchpointChildView<HybridCarousel> implements HybridCarouselInterfaceView {

    private HybridCarouselPresenter presenter;
    private HybridCarouselAdater carouselAdapter;
    private RecyclerView recyclerView;
    private TrackListener trackListener;
    private HorizontalScrollingEnhancer horizontalScrollingEnhancer;

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
        initList(context);
    }

    @Override
    public void bind(@Nullable final HybridCarousel model) {
        if (model == null) {
            setVisibility(GONE);
            return;
        }

        presenter.mapResponse(model);
    }

    @Override
    public int getStaticHeight() {
        return 0;
    }

    @Override
    public void print() {

    }

    @Override
    public void showItems(final List<HybridCarouselCardContainerModel> items, final HeightCalculatorDelegate heightCalculator) {
        carouselAdapter.setCardHeight(heightCalculator.getFixedCardHeight());
        carouselAdapter.setItems(items);
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
}
