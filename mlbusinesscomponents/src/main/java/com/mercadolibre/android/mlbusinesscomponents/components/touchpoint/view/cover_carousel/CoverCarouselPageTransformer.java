package com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.view.cover_carousel;

import android.animation.AnimatorInflater;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.viewpager.widget.ViewPager;
import com.mercadolibre.android.mlbusinesscomponents.R;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.view.cover_carousel.cover_card.CoverCardView;

public class CoverCarouselPageTransformer implements ViewPager.PageTransformer {

    private static final float ALPHA_VALUE = 1.5f;
    private static final float SCALE_VALUE = 0.9f;

    private final boolean alphaAnimation;
    private final boolean scaleAnimation;
    private final Context context;

    public CoverCarouselPageTransformer(final boolean alphaAnimation, final boolean scaleAnimation,
        final Context context) {
        this.alphaAnimation = alphaAnimation;
        this.scaleAnimation = scaleAnimation;
        this.context = context;
    }

    @Override
    public void transformPage(@NonNull final View page, final float position) {

        if (scaleAnimation) {
            setScaleAnimation(page, position);
        }
        if (alphaAnimation) {
            setAlphaAnimation(page, position);
        }
    }

    //TODO: Adjust the press animation,
    private void setPressAnimation(final View page) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            page.setOnClickListener(view -> {
                    view.setStateListAnimator(AnimatorInflater.loadStateListAnimator(
                        context, R.drawable.cover_card_click_animation
                    ));
                }
            );
        }
    }

    private void setScaleAnimation(final View page, final float position) {
        final float scaleFactor = 1 - Math.abs( position ) + SCALE_VALUE * Math.abs( position );

        page.setScaleX( scaleFactor );
        page.setScaleY( scaleFactor );
    }

    private void setAlphaAnimation(final View page, final float position) {
        if (position != 0) {
            page.setAlpha(ALPHA_VALUE - Math.abs(position));
        }
    }
}
