package com.mercadolibre.android.mlbusinesscomponents.components.common;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import com.mercadolibre.android.mlbusinesscomponents.R;
import com.squareup.picasso.Picasso;

public class CrossSellingBoxView extends ConstraintLayout {

    public interface OnClickCrossSellingBoxView {
        void OnClickCrossSellingButton(@NonNull final String deepLink);
    }

    public CrossSellingBoxView(final Context context) {
        super(context);
        inflate(context, R.layout.ml_view_crossselling, this);
    }

    public CrossSellingBoxView(final Context context, final AttributeSet attrs) {
        super(context, attrs);
        inflate(context, R.layout.ml_view_crossselling, this);
    }

    public CrossSellingBoxView(final Context context, final AttributeSet attrs,
        final int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inflate(context, R.layout.ml_view_crossselling, this);
    }

    public void init(@NonNull final String iconUrl, @NonNull final String title,
        @NonNull final String buttonLink, @NonNull final String buttonTitle,
        @NonNull final OnClickCrossSellingBoxView onClick) {
        loadImageUrl(iconUrl);
        ((AppCompatTextView) findViewById(R.id.crossSellingTitle)).setText(title);
        AppCompatTextView action = findViewById(R.id.crossSellingAction);
        action.setText(buttonTitle);
        action.setOnClickListener(v -> onClick.OnClickCrossSellingButton(buttonLink));
    }

    private void loadImageUrl(@NonNull String url) {
        Context context;
        if ((context = getContext()) != null) {
            Picasso
                .with(context)
                .load(url)
                .placeholder(R.drawable.skeleton)
                .into((AppCompatImageView) findViewById(R.id.crossSellingImage));
        }
    }
}
