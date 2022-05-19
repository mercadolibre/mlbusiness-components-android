package com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.view.flex_cover_carousel;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;

import androidx.viewpager.widget.ViewPager;

import com.mercadolibre.android.mlbusinesscomponents.R;
import com.mercadolibre.android.mlbusinesscomponents.components.utils.ScaleUtils;

public class FlexCoverCarouselViewPager extends ViewPager {

    private static final int MARGIN = 16;
    private static final float DEFAULT_RATIO = 2.1929f;
    private float ratio = DEFAULT_RATIO;

    /**
     * Constructor
     *
     * @param context The execution context.
     */
    public FlexCoverCarouselViewPager(final Context context) {
        super(context);
    }

    /**
     * Constructor
     *
     * @param context The execution context.
     * @param attrs The style attributes.
     */
    public FlexCoverCarouselViewPager(final Context context, final AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    private void init(final AttributeSet attrs) {
        if (attrs != null) {
            @SuppressLint("CustomViewStyleable")
            final TypedArray styled = getContext().obtainStyledAttributes(attrs, R.styleable.RatioViewPager);
            styled.recycle();
        }
    }

    @Override
    protected void onMeasure(final int widthMeasureSpec, final int heightMeasureSpec) {
        final int width = MeasureSpec.getSize(widthMeasureSpec);
        final int height = (int) ((width - ScaleUtils.getPxFromDp(getContext(), MARGIN)) / ratio);
        setMeasuredDimension(width, height);
        measureChildren(MeasureSpec.makeMeasureSpec(width, MeasureSpec.EXACTLY),
                MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY));
        super.onMeasure(MeasureSpec.makeMeasureSpec(width, MeasureSpec.EXACTLY),
                MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY));
    }

    public void setRatio(final float newRatio) {
        ratio = newRatio;
    }
}