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
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.callback.OnClickCallback;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.cover_carousel.model.cover_card.CoverCardInterface;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.cover_carousel.response.CoverCarouselInterface;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.view.AbstractTouchpointChildView;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.view.carousel.card.TrackListener;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.view.cover_carousel.cover_card.CoverCardView;
import java.util.ArrayList;
import java.util.List;

import static com.mercadolibre.android.mlbusinesscomponents.components.utils.TrackingUtils.trackShow;

public class CoverCarouselView extends AbstractTouchpointChildView<CoverCarouselInterface> implements
    CoverCarouselViewInterface {

    private static final int MARGIN_BETWEEN_PAGES = 8;
    private static final int MARGIN_BETWEEN_SCALED_PAGES = -10;
    private static final int VIEW_PAGER_PADDING_DIP = 16;

    private final CoverCarouselPresenter presenter;

    private final LinearLayout carouselContainer;
    private final LinearLayout headerContainer;
    private final TextView headerTitle;
    private final TextView headerAction;
    private final LinearLayout skeleton;

    private final ViewPager viewPager;
    private final CoverCardViewPagerAdapter viewPagerAdapter;

    private TrackListener trackListener;

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

        carouselContainer = findViewById(R.id.touchpoint_cover_carousel_container);
        headerContainer = findViewById(R.id.touchpoint_cover_carousel_header_container);
        headerTitle = findViewById(R.id.touchpoint_cover_carousel_header_title);
        headerAction = findViewById(R.id.touchpoint_cover_carousel_header_action);
        skeleton = findViewById(R.id.touchpoint_cover_carousel_skeleton);

        viewPager = findViewById(R.id.cover_carouse_view_pager);
        viewPagerAdapter = new CoverCardViewPagerAdapter(getContext());

        initViewPager();
    }

    private void initViewPager() {
        viewPager.setAdapter(viewPagerAdapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(final int position, final float positionOffset, final int positionOffsetPixels) {
                //no op..
            }

            @Override
            public void onPageSelected(final int position) {
                if (trackListener != null) {
                    trackListener.print();
                }
            }

            @Override
            public void onPageScrollStateChanged(final int state) {
                //no op..
            }
        });
    }

    @Override
    public void bind(@Nullable final CoverCarouselInterface model) {
        presenter.mapResponse(model);

        if (trackListener == null) {
            trackShow(tracker, new ArrayList<>(model.getItems()));
        }
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
        viewPagerAdapter.setElementsView(items);
        presenter.getMaxHeight(viewPagerAdapter.getElementsList());
    }

    @Override
    public void setViewPagerHeight(final int maxHeight, final boolean isSkeletonVisible) {
        final ViewGroup.LayoutParams params = viewPager.getLayoutParams();

        if (isSkeletonVisible) {
            final int viewPagerPadding = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                VIEW_PAGER_PADDING_DIP,
                getResources().getDisplayMetrics()
            );
            params.height = maxHeight + viewPagerPadding;
        } else {
            params.height = maxHeight;
        }
    }

    @Override
    public void setAnimations(final boolean alphaAnimation, final boolean scaleAnimation,
        final boolean pressAnimation) {
        final ViewPager.PageTransformer transformer = new CoverCarouselPageTransformer(
            alphaAnimation, scaleAnimation, pressAnimation, getContext()
        );

        viewPager.setPageTransformer(false, transformer);
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
    public void showSkeleton() {
        skeleton.setVisibility(VISIBLE);
        carouselContainer.setVisibility(GONE);
    }

    @Override
    public void hideSkeleton() {
        skeleton.setVisibility(GONE);
        carouselContainer.setVisibility(VISIBLE);
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
        headerAction.setVisibility(GONE);
    }

    @Override
    public void setHeaderActionTitle(final String title) {
        headerAction.setText(title);
    }

    @Override
    public void setHeaderActionClickListener(final String link) {
        if (onClickCallback != null) {
            headerAction.setOnClickListener(v -> onClickCallback.onClick(link));
        }
    }

    public void setTrackListener(final TrackListener trackListener) {
        this.trackListener = trackListener;
    }

    @Override
    public void setOnClickCallback(@Nullable final OnClickCallback onClickCallback) {
        this.onClickCallback = onClickCallback;
        viewPagerAdapter.setOnClickCallback(this.onClickCallback);
    }
}
