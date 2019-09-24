package com.mercadolibre.android.mlbusinesscomponents.components.loyalty;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Handler;
import android.os.Looper;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.ColorInt;
import android.support.annotation.Dimension;
import android.support.annotation.FloatRange;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ProgressBar;

import com.mercadolibre.android.mlbusinesscomponents.R;
import com.mercadolibre.android.mlbusinesscomponents.components.utils.ScaleUtils;
import com.mercadolibre.android.ui.font.Font;
import com.mercadolibre.android.ui.font.TypefaceHelper;

class LoyaltyProgress extends View {

    private final float DEFAULT_SIZE_RING_STROKE = 3.4f;

    private int loyaltyNumber;
    private final Rect boundsText = new Rect();
    private final Paint textPaint = new Paint();
    private final Paint ringPaint = new Paint();
    private final Paint progressPaint = new Paint();
    private final RectF boundsF = new RectF();
    private boolean animatedEnd;
    private float progress;

    private static final String PROPERTY_PROGRESS = "progress";

    public LoyaltyProgress(final Context context) {
        this(context, null);
    }

    public LoyaltyProgress(final Context context, final AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoyaltyProgress(final Context context, final AttributeSet attrs,
        final int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initLoyaltyProgress(context, attrs);
    }

    private void initLoyaltyProgress(final Context context, final AttributeSet attrs) {
        final int DEFAULT_SIZE_LOYALTY_NUMBER = 28;
        final int DEFAULT_LOYALTY_NUMBER = 1;

        final TypedArray typedArray = context.obtainStyledAttributes(
            attrs,
            R.styleable.LoyaltyProgress,
            0, 0);

        loyaltyNumber =
            typedArray
                .getInteger(R.styleable.LoyaltyProgress_loyaltyNumber, DEFAULT_LOYALTY_NUMBER);
        final int colorLoyaltyText =
            ContextCompat
                .getColor(getContext(), typedArray.getResourceId(R.styleable.LoyaltyProgress_colorLoyaltyProgressText,
                    R.color.ui_components_android_color_accent));
        final float sizeLoyaltyNumber =
            typedArray.getDimension(R.styleable.LoyaltyProgress_sizeLoyaltyNumber,
                ScaleUtils.getPxFromSp(context,
                    DEFAULT_SIZE_LOYALTY_NUMBER));

        final float sizeRingStroke =
            typedArray.getDimension(R.styleable.LoyaltyProgress_sizeRingStroke,
                ScaleUtils.getPxFromDp(context,
                    DEFAULT_SIZE_RING_STROKE));

        typedArray.recycle();

        setSaveEnabled(true);
        initTextPaint(context, loyaltyNumber, colorLoyaltyText, sizeLoyaltyNumber);
        initRingPaint(context, sizeRingStroke);
        initProgressPaint(context, sizeRingStroke);
    }

    private void initTextPaint(final Context context, final int loyaltyNumber, final int colorLoyaltyText,
        final float sizeLoyaltyNumber) {
        final String loyaltyText = String.valueOf(loyaltyNumber);
        textPaint.setAntiAlias(true);
        textPaint.setColor(colorLoyaltyText);
        textPaint.setTextSize(sizeLoyaltyNumber);
        textPaint.getTextBounds(loyaltyText, 0, loyaltyText.length(), boundsText);
        TypefaceHelper.setTypeface(context.getApplicationContext(), textPaint, Font.SEMI_BOLD);
    }

    private void configPaint(final Paint paint, final float sizeRingStroke) {
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(sizeRingStroke);
    }

    private void initProgressPaint(final Context context, final float sizeRingStroke) {
        configPaint(progressPaint, sizeRingStroke);
        progressPaint.setStrokeCap(Paint.Cap.ROUND);
        progressPaint
            .setColor(ContextCompat.getColor(context, R.color.ui_components_android_color_accent));
    }

    private void initRingPaint(final Context context, final float sizeRingStroke) {
        configPaint(ringPaint, sizeRingStroke);
        ringPaint
            .setColor(ContextCompat.getColor(context, R.color.ui_meli_light_grey));
    }

    public void setNumber(final int loyaltyNumber) {
        final String loyaltyText = String.valueOf(loyaltyNumber);
        textPaint.getTextBounds(loyaltyText, 0, loyaltyText.length(), boundsText);
        this.loyaltyNumber = loyaltyNumber;
    }

    public void setColorProgress(@ColorInt final int color) {
        progressPaint.setColor(color);
    }

    public void setColorText(@ColorInt final int color) {
        textPaint.setColor(color);
    }

    public void setSizeText(@Dimension final float sizeLoyaltyNumber) {
        textPaint.setTextSize(sizeLoyaltyNumber);
    }

    public void setColorSecondaryRing(@ColorInt final int color) {
        ringPaint.setColor(color);
    }

    public void setProgress(@FloatRange(from = 0, to = 1) final float progress) {
        this.progress = progress;
    }

    public void setAnimation() {
        try {
            if (!animatedEnd) {
                new Handler(Looper.getMainLooper()).post(() -> {
                    final float progressValue = progress;
                    final ValueAnimator valueAnimator = new ValueAnimator();
                    valueAnimator
                        .setValues(
                            PropertyValuesHolder.ofFloat(PROPERTY_PROGRESS, 0, progressValue));
                    valueAnimator
                        .setDuration(1500)
                        .setInterpolator(new AccelerateDecelerateInterpolator());
                    valueAnimator.addUpdateListener(animation -> {
                        progress = (float) animation.getAnimatedValue(PROPERTY_PROGRESS);
                        invalidate();
                    });
                    valueAnimator.addListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(final Animator animation) {
                            animatedEnd = true;
                        }
                    });
                    valueAnimator.start();
                });
            }
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }

    static class SavedState extends BaseSavedState {
        float progress;
        boolean animationEnd;

        /**
         * Constructor called from {@link ProgressBar#onSaveInstanceState()}
         */
        SavedState(Parcelable superState) {
            super(superState);
        }

        /**
         * Constructor called from {@link #CREATOR}
         */
        private SavedState(Parcel in) {
            super(in);
            progress = in.readFloat();
            animationEnd = in.readByte() != 0;
        }

        @Override
        public void writeToParcel(Parcel out, int flags) {
            super.writeToParcel(out, flags);
            out.writeFloat(progress);
            out.writeByte((byte) (animationEnd ? 1 : 0));
        }

        public static final Parcelable.Creator<SavedState> CREATOR
            = new Parcelable.Creator<SavedState>() {
            public SavedState createFromParcel(Parcel in) {
                return new SavedState(in);
            }

            public SavedState[] newArray(int size) {
                return new SavedState[size];
            }
        };
    }

    @Override
    public Parcelable onSaveInstanceState() {
        // Force our ancestor class to save its state
        Parcelable superState = super.onSaveInstanceState();
        SavedState ss = new SavedState(superState);

        ss.progress = progress;
        ss.animationEnd = animatedEnd;

        return ss;
    }

    @Override
    public void onRestoreInstanceState(Parcelable state) {
        SavedState ss = (SavedState) state;
        super.onRestoreInstanceState(ss.getSuperState());

        progress = ss.progress;
        animatedEnd = ss.animationEnd;
    }

    @Override
    protected void onMeasure(final int widthMeasureSpec, final int heightMeasureSpec) {
        final int width = measureSize(widthMeasureSpec);
        final int height = measureSize(heightMeasureSpec);
        setMeasuredDimension(width, height);
    }

    private int getMeasurement(int measureSpec, int preferred) {
        int specSize = MeasureSpec.getSize(measureSpec);
        int measurement = preferred;

        switch (MeasureSpec.getMode(measureSpec)) {
        case MeasureSpec.EXACTLY:
            measurement = specSize;
            break;
        case MeasureSpec.AT_MOST:
            measurement = Math.min(preferred, specSize);
        case MeasureSpec.UNSPECIFIED:
            break;
        }

        return measurement;
    }

    private int measureSize(int measureSpec) {
        final int DEFAULT_SIZE = 46; // Dimension in dp
        int preferred = (int) ScaleUtils.getPxFromDp(getContext(), DEFAULT_SIZE);
        return getMeasurement(measureSpec, preferred);
    }

    @Override
    protected synchronized void onDraw(final Canvas canvas) {
        //draw text
        final String loyaltyText = String.valueOf(loyaltyNumber);

        int averageWidth = (getWidth() / 2);
        int averageHeight = (getHeight() / 2);
        int x = averageWidth - boundsText.centerX();
        int y = averageHeight - boundsText.centerY();

        canvas.drawText(loyaltyText, x, y, textPaint);

        //draw ring
        float radius = (float) (getWidth() / 2.2);
        canvas.drawCircle(averageWidth, averageHeight, radius, ringPaint);

        //draw progress with round corners
        float defaultInset = ScaleUtils.getPxFromDp(getContext(), 8.5f);
        float defaultStrokeWidth = ScaleUtils.getPxFromDp(getContext(), DEFAULT_SIZE_RING_STROKE);
        float currentStrokeWidth = progressPaint.getStrokeWidth();

        // We calculate the necessary amount of inset proportionally
        // based on the current stroke width
        float currentInset = currentStrokeWidth * defaultInset / defaultStrokeWidth;
        final float startAngle = 270f;

        boundsF.set(averageWidth - radius, averageHeight - radius, averageWidth + radius, averageHeight + radius);
        canvas.drawArc(boundsF, startAngle, progress * 360f, false, progressPaint);
    }
}
