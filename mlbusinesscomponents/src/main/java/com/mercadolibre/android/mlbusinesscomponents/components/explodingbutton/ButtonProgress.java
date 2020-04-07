package com.mercadolibre.android.mlbusinesscomponents.components.explodingbutton;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.TransitionDrawable;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.mercadolibre.android.mlbusinesscomponents.R;
import static com.mercadolibre.android.mlbusinesscomponents.components.explodingbutton.ButtonProgressState.DISABLED;

public class ButtonProgress extends LinearLayout implements View.OnClickListener {
    private ProgressBar progressBar;
    private ObjectAnimator animator;
    private TextView textProgressBar;
    private ImageView circle;
    private ImageView icon;
    @ColorRes
    private int rippleColor;
    @ColorRes
    private int colorText;
    @ColorRes
    private int backgroundColor = R.color.components_primary_color;
    @ColorRes
    private int progressColor = R.color.components_secondary_color;
    private String titleProgress;
    private int durationRipple = 500;
    private int durationTimeout = 7000;
    private int durationFinishProgress = 1000;
    private int durationAnimation = 200;
    private int durationDelayRipple = 500;
    private static final float DARKEN_FACTOR = 0.1f;
    private View reveal;
    private View container;
    private OnFinishAnimationListener onFinishAnimationListener;
    private OnClickListener onClickListener;

    public ButtonProgress(Context context) {
        super(context);
        initView(context);
    }

    public ButtonProgress(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public ButtonProgress(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    public ButtonProgress Builder(View reveal) {
        this.reveal = reveal;
        return this;
    }

    public ButtonProgress setTextInformation(String title, String titleProgress) {
        textProgressBar.setText(title);
        this.titleProgress = titleProgress;
        return this;
    }

    public ButtonProgress setTextSize(int size) {
        textProgressBar.setTextSize(size);
        return this;
    }

    public ButtonProgress setColorText(int color) {
        colorText = color;
        paintText(colorText);
        return this;
    }

    public void setState(ButtonProgressState state) {
        if (state == DISABLED) {
            this.setClickable(false);
            paintButton(R.color.mlbusiness_color_disable_button, R.color.mlbusiness_color_disable_button);
            paintText(R.color.ui_meli_white);
        } else {
            this.setClickable(true);
            paintButton(backgroundColor, progressColor);
            paintText(colorText);
        }
    }

    public ButtonProgress setColorButton(int backgroundColor, int progressColor) {
        this.backgroundColor = backgroundColor;
        this.progressColor = progressColor;
        paintButton(backgroundColor, progressColor);
        return this;
    }

    private void paintText(int color) {
        textProgressBar.setTextColor(ContextCompat.getColor(getContext(), color));
    }

    private void paintButton(int backgroundColor, int progressColor) {
        LayerDrawable dr = (LayerDrawable) getResources().getDrawable(R.drawable.button_background);
        GradientDrawable background = (GradientDrawable) dr.findDrawableByLayerId(R.id.background);
        ClipDrawable progress = (ClipDrawable) dr.findDrawableByLayerId(R.id.progress);
        background.setColor(ContextCompat.getColor(getContext(), backgroundColor));
        DrawableCompat.setTint(progress, progressColor);
        progressBar.setProgressDrawable(dr);
    }

    public ButtonProgress setDurationRipple(int duration) {
        this.durationRipple = duration;
        return this;
    }

    public ButtonProgress setDurationDelayRipple(int duration) {
        this.durationDelayRipple = duration;
        return this;
    }

    public ButtonProgress setDurationAnimationCircle(int duration) {
        this.durationAnimation = duration;
        return this;
    }

    public ButtonProgress setDurationFinishProgress(int duration) {
        this.durationFinishProgress = duration;
        return this;
    }

    public ButtonProgress setMaxTimeFromServices(int durationServices) {
        this.durationTimeout = durationServices;
        return this;
    }

    public ButtonProgress addFinishAnimationListener(OnFinishAnimationListener onFinishAnimationListener) {
        this.onFinishAnimationListener = onFinishAnimationListener;
        return this;
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public void finishProgress(@ColorRes int color, @Nullable @DrawableRes Integer icon) {
        this.rippleColor = color;
        if (icon != null) {
            this.icon.setImageResource(icon);
        }

        final int progress = progressBar.getProgress();
        if (animator != null) {
            animator.cancel();
        }
        animator = ObjectAnimator.ofInt(progressBar, "progress", progress, durationTimeout);
        animator.setInterpolator(new AccelerateDecelerateInterpolator());
        animator.setDuration(durationFinishProgress);

        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(final Animator animation) {
                animator.removeListener(this);
                createResultAnim();
            }
        });
        animator.start();
    }

    private void adjustHeight(final ImageView view) {
        final ViewGroup.LayoutParams params = view.getLayoutParams();
        params.height = (int) getContext().getResources().getDimension(R.dimen.ui_5m);
        params.width = (int) getContext().getResources().getDimension(R.dimen.ui_5m);
        view.setLayoutParams(params);
    }

    public ButtonProgress setViewParent(View view) {
        reveal = view;
        return this;
    }

    private void initView(Context context) {
        super.setOnClickListener(this);
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.view_color_options, this, true);
        textProgressBar = findViewById(R.id.cho_loading_buy_progress_text);
        progressBar = findViewById(R.id.cho_loading_buy_progress);
        circle = findViewById(R.id.cho_loading_buy_circular);
        icon = findViewById(R.id.cho_loading_buy_icon);
        container = findViewById(R.id.cho_loading_buy_container);
        adjustHeight(circle);
        adjustHeight(icon);
    }

    @Override
    public void onClick(View v) {
        startAnimationCustom();
        if (onClickListener != null) {
            onClickListener.onClick(v);
            setClickable(false);
        }
    }

    public void startAnimationCustom() {
        progressBar.setMax(durationTimeout);
        textProgressBar.setText(titleProgress);
        animator = ObjectAnimator.ofInt(progressBar, "progress", 0, durationTimeout);
        animator.setInterpolator(new LinearInterpolator());
        animator.setDuration(durationTimeout);
        animator.start();
    }

    void createResultAnim() {
        final int duration = durationAnimation;
        final int initialWidth = progressBar.getWidth();
        final int finalSize = progressBar.getHeight();
        final int initialRadius = getResources().getDimensionPixelOffset(R.dimen.ui_050m);
        final int finalRadius = finalSize / 2;
        final GradientDrawable initialBg = getProgressBarShape(ContextCompat.getColor(getContext(), rippleColor), initialRadius);
        final GradientDrawable finalBg = getProgressBarShape(ContextCompat.getColor(getContext(), rippleColor), initialRadius);
        final TransitionDrawable transitionDrawable = new TransitionDrawable(new Drawable[]{initialBg, finalBg});
        progressBar.setProgressDrawable(transitionDrawable);
        transitionDrawable.startTransition(duration);

        final ValueAnimator a = ValueAnimator.ofFloat(0, 1);
        a.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(final ValueAnimator animation) {
                final float interpolatedTime = animation.getAnimatedFraction();
                final int radius = getNewRadius(interpolatedTime);
                setRadius(initialBg, radius);
                setRadius(finalBg, radius);
                progressBar.getLayoutParams().width = getNewWidth(interpolatedTime);
                progressBar.requestLayout();
            }

            private int getNewRadius(final float t) {
                return initialRadius + (int) ((finalRadius - initialRadius) * t);
            }

            private int getNewWidth(final float t) {
                return initialWidth + (int) ((finalSize - initialWidth) * t);
            }

            private void setRadius(final Drawable bg, final int value) {
                final GradientDrawable layerBg = (GradientDrawable) bg;
                layerBg.setCornerRadius(value);
            }
        });
        a.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(final Animator animation) {
                animation.removeAllListeners();
                createResultIconAnim();
            }
        });
        textProgressBar.setVisibility(View.GONE);
        a.setInterpolator(new DecelerateInterpolator(2f));
        a.setDuration(duration);
        a.start();
        setColorDrawable();
    }

    private GradientDrawable getProgressBarShape(final int color, final int radius) {
        final GradientDrawable drawable = new GradientDrawable();
        drawable.setColor(color);
        drawable.setCornerRadius(radius);
        return drawable;
    }

    private void createResultIconAnim() {
        progressBar.setClickable(false);
        this.icon.setVisibility(View.VISIBLE);
        float ICON_SCALE = 3.0f;
        this.icon.setScaleY(ICON_SCALE);
        this.icon.setScaleX(ICON_SCALE);
        this.icon.setAlpha(0f);
        this.icon.animate().alpha(1.0f).scaleX(1.0f).scaleY(1.0f)
                .setInterpolator(new DecelerateInterpolator(2f))
                .setDuration(300)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(final Animator animation) {
                        animation.removeAllListeners();
                        createCircularReveal();
                    }
                }).start();
    }

    private void setColorDrawable() {
        Drawable background = circle.getBackground();
        if (background instanceof ShapeDrawable) {
            ((ShapeDrawable) background).getPaint().setColor(ContextCompat.getColor(getContext(), rippleColor));
        } else if (background instanceof GradientDrawable) {
            ((GradientDrawable) background).setColor(ContextCompat.getColor(getContext(), rippleColor));
        } else if (background instanceof ColorDrawable) {
            ((ColorDrawable) background).setColor(ContextCompat.getColor(getContext(), rippleColor));
        }
    }

    void createCircularReveal() {

        // when the icon anim has finished, paint the whole screen with the result color
        final float finalRadius = (float) Math.hypot(reveal.getWidth(), reveal.getHeight());
        // FIXME altura original del boton
        final int startRadius = (int) (getContext().getResources().getDimension(R.dimen.ui_6m) / 2);

        final int[] locationContainer = new int[2];
        container.getLocationOnScreen(locationContainer);
        final int cy = (locationContainer[1] - container.getMeasuredHeight() / 2);
        final int cx = locationContainer[0] + (container.getWidth() / 2);

        //try to avoid reveal detached view
        reveal.post(() -> {
            final Animator anim;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                anim = ViewAnimationUtils.createCircularReveal(reveal, cx, cy, startRadius, finalRadius);
            } else {
                anim = ObjectAnimator.ofFloat(reveal, "alpha", 0, 1);
            }
            anim.setDuration(durationRipple);
            anim.setStartDelay(durationDelayRipple);
            anim.setInterpolator(new AccelerateInterpolator());
            anim.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationStart(final Animator animation) {
                    circle.setVisibility(View.GONE);
                    icon.setVisibility(View.GONE);

                    final int startColor = ContextCompat.getColor(getContext(), rippleColor);
                    final int endColor = ContextCompat.getColor(getContext(), rippleColor);
                    final Drawable[] switchColors =
                            {new ColorDrawable(startColor), new ColorDrawable(endColor)};
                    final TransitionDrawable colorSwitch = new TransitionDrawable(switchColors);
                    reveal.setBackground(colorSwitch);
                    colorSwitch.startTransition((int) animation.getDuration());
                }

                @SuppressLint("ResourceAsColor")
                @Override
                public void onAnimationEnd(final Animator animation) {
                    Activity activity = (Activity) reveal.getContext();
                    if (activity != null) {
                        setStatusBarColor(getDarkPrimaryColor(ContextCompat.getColor(getContext(), rippleColor)), activity.getWindow());
                    }
                    if (onFinishAnimationListener != null) {
                        onFinishAnimationListener.finishAnimation();
                    }
                }
            });

            anim.start();
        });
    }

    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        onClickListener = null;
        onFinishAnimationListener = null;
    }

    @ColorInt
    public static int getDarkPrimaryColor(@ColorInt final int primaryColor) {
        final float[] hsv = new float[3];
        Color.colorToHSV(primaryColor, hsv);
        hsv[1] = hsv[1] + DARKEN_FACTOR;
        hsv[2] = hsv[2] - DARKEN_FACTOR;
        return Color.HSVToColor(hsv);
    }

    /**
     * Paint the status bar
     *
     * @param color the color to use. The color will be darkened by {@link #DARKEN_FACTOR} percent
     */
    @SuppressLint({"InlinedApi"})
    public static void setStatusBarColor(@ColorInt final int color, @NonNull final Window window) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(color);
        }
    }

}

