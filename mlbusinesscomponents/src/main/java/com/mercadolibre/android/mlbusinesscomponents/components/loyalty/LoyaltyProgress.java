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
import android.support.annotation.ColorRes;
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

    @ColorRes
    private int colorLoyaltyText;
    @Dimension
    private float sizeLoyaltyNumber;

    private int loyaltyNumber;
    private final Rect bounds = new Rect();
    private final Paint textPaint = new Paint();
    private final Paint borderPaint = new Paint();
    private final RectF boundsF = new RectF();
    private boolean animatedEnd;
    private float progress = 0;

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
        colorLoyaltyText =
            typedArray.getResourceId(R.styleable.LoyaltyProgress_colorLoyaltyProgressText,
                R.color.ui_components_android_color_accent);
        sizeLoyaltyNumber =
            typedArray.getDimension(R.styleable.LoyaltyProgress_sizeLoyaltyNumber,
                ScaleUtils.getPxFromSp(context,
                    DEFAULT_SIZE_LOYALTY_NUMBER));

        typedArray.recycle();

        initPaint(context);
    }

    private void initPaint(final Context context) {
        borderPaint.setAntiAlias(true);
        borderPaint.setStyle(Paint.Style.STROKE);
        borderPaint.setStrokeWidth(ScaleUtils.getPxFromDp(context, 4.3f));
        borderPaint.setStrokeCap(Paint.Cap.ROUND);
        borderPaint
            .setColor(ContextCompat.getColor(context, R.color.ui_components_android_color_accent));
    }

    public void setLoyaltyNumber(final int loyaltyNumber) {
        this.loyaltyNumber = loyaltyNumber;
    }

    public void setColorProgress(@ColorInt final int color) {
        borderPaint.setColor(color);
    }

    public void setColorText(@ColorInt final int color) {
        textPaint.setColor(color);
    }

    public void setProgress(@FloatRange(from = 0, to = 1) final float progress) {
        this.progress = progress;
    }

    public float getProgress() {
        return progress;
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
    protected void onDraw(final Canvas canvas) {
        super.onDraw(canvas);

        //draw text
        final String loyaltyText = String.valueOf(loyaltyNumber);
        textPaint.setColor(ContextCompat.getColor(getContext(), colorLoyaltyText));
        textPaint.setTextSize(sizeLoyaltyNumber);
        textPaint.getTextBounds(loyaltyText, 0, loyaltyText.length(), bounds);
        TypefaceHelper.setTypeface(getContext().getApplicationContext(), textPaint, Font.SEMI_BOLD);

        int x = (getWidth() / 2) - bounds.centerX();
        int y = (getHeight() / 2) - bounds.centerY();

        canvas.drawText(loyaltyText, x, y, textPaint);

        //draw progress with round corners
        float dx = ScaleUtils.getPxFromDp(getContext(), 8.5f);
        final float startAngle = 270f;
        boundsF.set(getBackground().getBounds());
        boundsF.inset(dx, dx);

        canvas.drawArc(boundsF, startAngle, progress * 360f, false, borderPaint);
    }
}
