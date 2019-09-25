package com.mercadolibre.android.mlbusinesscomponents.components.common.dividingline;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;
import com.mercadolibre.android.mlbusinesscomponents.R;
import com.mercadolibre.android.mlbusinesscomponents.components.utils.ScaleUtils;

public class MLBusinessDividingLineView extends View {

    private final Paint linePaint = new Paint();
    private boolean hasTriangle;

    private static final int DEFAULT_SIZE = 11;

    public MLBusinessDividingLineView(final Context context) {
        this(context, null);
    }

    public MLBusinessDividingLineView(final Context context, @Nullable final AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MLBusinessDividingLineView(final Context context, @Nullable final AttributeSet attrs,
        final int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initDividingLineView(context, attrs);
    }

    private void initDividingLineView(final Context context, @Nullable final AttributeSet attrs) {
        final TypedArray typedArray = context.obtainStyledAttributes(
            attrs,
            R.styleable.MLBusinessDividingLineView,
            0, 0);

        hasTriangle = typedArray.getBoolean(R.styleable.MLBusinessDividingLineView_hasTriangle, false);

        typedArray.recycle();

        final int dividerColor = ContextCompat.getColor(context, R.color.divider_line_gray);
        linePaint.setAntiAlias(true);
        linePaint.setColor(dividerColor);
    }

    @Override
    protected void onMeasure(final int widthMeasureSpec, final int heightMeasureSpec) {
        setMeasuredDimension(widthMeasureSpec, measureHeight(heightMeasureSpec));
    }

    private int getMeasurement(final int measureSpec, final int preferred) {
        final int specSize = MeasureSpec.getSize(measureSpec);
        int measurement = preferred;

        switch (MeasureSpec.getMode(measureSpec)) {
        case MeasureSpec.EXACTLY:
            measurement = specSize;
            break;
        case MeasureSpec.AT_MOST:
            measurement = Math.min(preferred, specSize);
            break;
        case MeasureSpec.UNSPECIFIED: default:
            break;
        }

        return measurement;
    }

    private int measureHeight(final int measureSpec) {
        final int preferred = (int) ScaleUtils.getPxFromDp(getContext(), DEFAULT_SIZE);
        return getMeasurement(measureSpec, preferred);
    }

    @Override
    protected void onDraw(final Canvas canvas) {

        final float averageHeight;

        if (hasTriangle) {
            averageHeight = 0f;
            final float mediumLength = getWidth() / 2f;

            final float maximumTrianglePoint =
                (averageHeight + ScaleUtils.getPxFromDp(getContext(), 10));
            final float factor = ScaleUtils.getPxFromDp(getContext(), 7.67f);

            linePaint.setStrokeWidth(ScaleUtils.getPxFromDp(getContext(), 1f));

            // Draw triangle
            canvas.drawLine((mediumLength - factor + 0.5f), averageHeight, mediumLength - 0.15f,
                maximumTrianglePoint, linePaint);
            canvas.drawLine(mediumLength + 0.15f, maximumTrianglePoint, (mediumLength + factor - 0.5f),
                averageHeight, linePaint);

            linePaint.setStrokeWidth(ScaleUtils.getPxFromDp(getContext(), 1.5f));

            //Initial line
            canvas.drawLine(0, averageHeight, (mediumLength - factor), averageHeight, linePaint);

            //Final line
            canvas.drawLine((mediumLength + factor), averageHeight, getWidth(), averageHeight,
                linePaint);
        } else {
            averageHeight = getHeight() / 2;
            canvas.drawLine(0, averageHeight, getWidth(), averageHeight, linePaint);
        }
    }
}