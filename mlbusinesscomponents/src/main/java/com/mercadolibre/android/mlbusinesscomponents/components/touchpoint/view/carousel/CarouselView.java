package com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.view.carousel;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.widget.TextView;
import com.mercadolibre.android.mlbusinesscomponents.R;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.carousel.CarouselCardResponse;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.carousel.CarouselResponse;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.view.AbstractTouchpointChildView;
import java.util.List;

import static android.support.v7.widget.RecyclerView.SCROLL_STATE_IDLE;

public class CarouselView extends AbstractTouchpointChildView<CarouselResponse> {

    private final CarouselAdapter adapter;

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
        final RecyclerView recyclerView = findViewById(R.id.touchpoint_carousel_recycler_view);
        adapter = new CarouselAdapter();
        adapter.setOnClickCallback(onClickCallback);
        adapter.setTracker(tracker);
        adapter.setExtraData(tracking);
        initList(context, recyclerView);
    }

    @Override
    public void bind(@Nullable final CarouselResponse model) {
        if (model != null && model.isValid()) {
            showCards(model.getItems());
        }
    }

    private void initList(final Context context, final RecyclerView recyclerView) {
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        recyclerView.addItemDecoration(new CarouselDecorator(getResources().getDimensionPixelOffset(R.dimen.ui_1_25m)));
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull final RecyclerView recyclerView, final int state) {
                super.onScrollStateChanged(recyclerView, state);
                if (state == SCROLL_STATE_IDLE) {
                    print();
                }
            }
        });
    }

    private void showCards(final List<CarouselCardResponse> cards) {
        adapter.setItems(cards);
    }

    @Override
    public void print() {
        //TODO: ver items visibles y tackear
    }
}
