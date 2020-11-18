package com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.view.cover_carousel;

import android.content.Context;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;
import com.mercadolibre.android.mlbusinesscomponents.R;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.cover_carousel.model.cover_card.CoverCardInterface;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.cover_carousel.response.CoverCarousel;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.view.AbstractTouchpointChildView;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.view.cover_carousel.cover_card.CoverCardView;
import java.util.ArrayList;
import java.util.List;

public class CoverCarouselView extends AbstractTouchpointChildView<CoverCarousel> implements
    CoverCarouselViewInterface {

    private static final int MARGIN_BETWEEN_PAGES = 8;
    private static final int MARGIN_BETWEEN_SCALED_PAGES = -10;
    //TODO Add this logic to the presenter.

    private final CoverCarouselPresenter presenter;

    private final LinearLayout headerContainer;
    private final TextView headerTitle;
    private final TextView headerAction;

    private final ViewPager viewPager;
    private final CoverCardViewPagerAdapter viewPagerAdapter;

    public CoverCarouselView(@NonNull final Context context) {
        this(context, null);
    }

    public CoverCarouselView(@NonNull final Context context, @Nullable final AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CoverCarouselView(@NonNull final Context context, @Nullable final AttributeSet attrs,
        final int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inflate(context, R.layout.touchpoint_cover_carousel_view, this);

        presenter = new CoverCarouselPresenter(this);

        headerContainer = findViewById(R.id.touchpoint_cover_carousel_header_container);
        headerTitle = findViewById(R.id.touchpoint_cover_carousel_header_title);
        headerAction = findViewById(R.id.touchpoint_cover_carousel_header_action);

        viewPager = findViewById(R.id.cover_carouse_view_pager);
        viewPagerAdapter = new CoverCardViewPagerAdapter();
        viewPager.setAdapter(viewPagerAdapter);
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
        final List<CoverCardView> coverCardsViews = new ArrayList<>();

        CoverCardView view;
        for (final CoverCardInterface itemData : items) {
            view = new CoverCardView(getContext());
            view.bind(itemData);
            coverCardsViews.add(view);
        }

        presenter.getMaxHeight(coverCardsViews);
    }

    @Override
    public void setViewPagerHeight(final int maxHeight) {
        final ViewGroup.LayoutParams params = viewPager.getLayoutParams();
        params.height = maxHeight;
    }

    @Override
    public void setElementsViews(final List<CoverCardView> coverCardsViews) {
        viewPagerAdapter.setElementsView(coverCardsViews);
    }

    @Override
    public void setAnimations(final boolean alphaAnimation, final boolean scaleAnimation,
        final boolean pressAnimation) {
        final ViewPager.PageTransformer transformer = new CoverCarouselPageTransformer(
            alphaAnimation, scaleAnimation, pressAnimation, getContext()
        );

        viewPager.setPageTransformer(false , transformer);
    }

    @Override
    public void setMarginsForScaledAnimation() {
        viewPager.setPageMargin((int) TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP, MARGIN_BETWEEN_SCALED_PAGES, getResources().getDisplayMetrics()
        ));
    }

    @Override
    public void setMarginsForNonScaledAnimation() {
        viewPager.setPageMargin((int) TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP, MARGIN_BETWEEN_PAGES, getResources().getDisplayMetrics()
        ));
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
