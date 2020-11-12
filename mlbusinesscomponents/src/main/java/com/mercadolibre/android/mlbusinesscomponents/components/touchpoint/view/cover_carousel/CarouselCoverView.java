package com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.view.cover_carousel;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;
import com.mercadolibre.android.mlbusinesscomponents.R;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.cover_carousel.model.cover_card.CoverCardInterface;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.cover_carousel.response.CoverCarousel;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.view.AbstractTouchpointChildView;
import java.util.List;

public class CarouselCoverView extends AbstractTouchpointChildView<CoverCarousel> implements
    CoverCarouselViewInterface {

    private final CarouselCoverPresenter presenter;

    private final RecyclerView recyclerView;
    private final CarouselCoverAdapter adapter;
    private final LinearLayout headerContainer;
    private final TextView headerTitle;
    private final TextView headerAction;

    public CarouselCoverView(@NonNull final Context context) {
        this(context, null);
    }

    public CarouselCoverView(@NonNull final Context context, @Nullable final AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CarouselCoverView(@NonNull final Context context, @Nullable final AttributeSet attrs,
        final int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inflate(context, R.layout.touchpoint_cover_carousel_view, this);
        presenter = new CarouselCoverPresenter(this);
        recyclerView = findViewById(R.id.touchpoint_cover_carousel_recycler_view);
        SnapHelper helper = new PagerSnapHelper();
        helper.attachToRecyclerView(recyclerView);
        headerContainer = findViewById(R.id.touchpoint_cover_carousel_header_container);
        headerTitle = findViewById(R.id.touchpoint_cover_carousel_header_title);
        headerAction = findViewById(R.id.touchpoint_cover_carousel_header_action);
        adapter = new CarouselCoverAdapter();
        intiList(context);
    }

    private void intiList(final Context context) {
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
    }

    @Override
    public void bind(@Nullable final CoverCarousel model) {
        presenter.mapResponse(model);
    }

    @Override
    public int getStaticHeight() {
        return 0;
    }

    @Override
    public void print() {
        //no op..
    }

    @Override
    public void setVisibilityGone() {
        setVisibility(GONE);
    }

    @Override
    public void setItemsList(final List<CoverCardInterface> items) {
        adapter.bindItems(items);
    }

    @Override
    public void hideHeaderContainer() {
        headerContainer.setVisibility(GONE);
    }

    @Override
    public void setHeaderTitle(final String title) {
        headerTitle.setText(title);
    }

    @Override
    public void hideHeaderAction() {
        headerContainer.setVisibility(INVISIBLE);
    }

    @Override
    public void setHeaderActionTitle(final String title) {
        headerAction.setText(title);
    }

    @Override
    public void setHeaderActionClickListener(final String link) {
        //TODO: Set listener.
    }
}
