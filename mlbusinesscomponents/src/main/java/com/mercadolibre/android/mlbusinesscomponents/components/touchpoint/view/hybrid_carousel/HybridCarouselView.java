package com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.view.hybrid_carousel;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import com.mercadolibre.android.mlbusinesscomponents.R;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.hybrid_carousel.model.HybridCarouselCardContainerModel;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.hybrid_carousel.response.HybridCarousel;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.view.AbstractTouchpointChildView;
import java.util.List;

public class HybridCarouselView extends AbstractTouchpointChildView<HybridCarousel> implements HybridCarouselInterfaceView {

    private HybridCarouselPresenter presenter;
    private HybridCarouselAdater carouselAdapter;

    public HybridCarouselView(@NonNull final Context context) {
        this(context, null);
    }

    public HybridCarouselView(@NonNull final Context context, @Nullable final AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HybridCarouselView(@NonNull final Context context, @Nullable final AttributeSet attrs, final int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inflate(getContext(), R.layout.touchpoint_carousel_view, this);
        presenter = new HybridCarouselPresenter(this);
        carouselAdapter = new HybridCarouselAdater();
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
    public void showItems(final List<HybridCarouselCardContainerModel> items) {
        carouselAdapter.setItems(items);
    }
}
