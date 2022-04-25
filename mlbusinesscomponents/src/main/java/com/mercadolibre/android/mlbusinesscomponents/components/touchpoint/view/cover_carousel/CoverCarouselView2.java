package com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.view.cover_carousel;

import static com.mercadolibre.android.mlbusinesscomponents.components.utils.TrackingUtils.trackShow;

import android.content.Context;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

import com.mercadolibre.android.mlbusinesscomponents.R;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.callback.OnClickCallback;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.AdditionalEdgeInsets;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.cover_carousel.model.cover_card.CoverCardInterfaceModel;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.cover_carousel.response.CoverCarouselInterfaceModel;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.view.AbstractTouchpointChildView;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.view.carousel.card.TrackListener;
import com.mercadolibre.android.mlbusinesscomponents.components.utils.ScaleUtils;

import java.util.ArrayList;
import java.util.List;

public class CoverCarouselView2 extends AbstractTouchpointChildView<CoverCarouselInterfaceModel> implements
    CoverCarouselViewInterface {

    private static final int COVER_CAROUSEL_CONTAINER_INDEX = 0;
    private static final int COVER_CAROUSEL_SKELETON_INDEX = 1;

    private static final int MARGIN_BETWEEN_PAGES = 8;
    private static final int MARGIN_BETWEEN_SCALED_PAGES = -10;
    private static final int VIEW_PAGER_PADDING_DIP = 16;

    private final CoverCarouselPresenter2 presenter;

    private final LinearLayout headerContainer;
    private final TextView headerTitle;
    private final TextView headerAction;
    private final ViewFlipper flipper;

    private final ViewPager viewPager;
    private final CoverCardViewPagerAdapter2 viewPagerAdapter;

    private TrackListener trackListener;

    public CoverCarouselView2(@NonNull final Context context) {
        this(context, null);
    }

    public CoverCarouselView2(@NonNull final Context context, @Nullable final AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CoverCarouselView2(@NonNull final Context context, @Nullable final AttributeSet attrs,
                              final int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inflate(context, R.layout.touchpoint_cover_carousel_view2, this);

        presenter = new CoverCarouselPresenter2();

        flipper = findViewById(R.id.touchpoint_cover_carousel_view_flipper);
        headerContainer = findViewById(R.id.touchpoint_cover_carousel_header_container);
        headerTitle = findViewById(R.id.touchpoint_cover_carousel_header_title);
        headerAction = findViewById(R.id.touchpoint_cover_carousel_header_action);

        viewPager = findViewById(R.id.cover_carouse_view_pager);
        viewPagerAdapter = new CoverCardViewPagerAdapter2(getContext());

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
    public void bind(@Nullable final CoverCarouselInterfaceModel model) {
        presenter.mapResponse(model, this);

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
    public void setItemsList(final List<CoverCardInterfaceModel> items) {
        viewPagerAdapter.setElementsView(items);
        viewPager.setCurrentItem(0);
        presenter.getMaxHeight(viewPagerAdapter.getElementsList(), this);
    }

    @Override
    public void decorate() {
        if (additionalInsets != null) {
            setViewPagerPaddingsFromInsets(additionalInsets);
        }
    }

    private void setViewPagerPaddingsFromInsets(
        final AdditionalEdgeInsets additionalInsets) {
        viewPager.setPadding(
            getInsetInPx(additionalInsets.getLeft()),
            getInsetInPx(additionalInsets.getTop()),
            getInsetInPx(additionalInsets.getRight()),
            getInsetInPx(additionalInsets.getBottom())
        );
    }

    private int getInsetInPx(final int inset) {
        return (int) ScaleUtils.getPxFromDp(getContext(), inset);
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
            params.height = maxHeight + getResources().getDimensionPixelSize(R.dimen.ui_2m);
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
        flipper.setDisplayedChild(COVER_CAROUSEL_SKELETON_INDEX);
    }

    @Override
    public void hideSkeleton() {
        flipper.setDisplayedChild(COVER_CAROUSEL_CONTAINER_INDEX);
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
