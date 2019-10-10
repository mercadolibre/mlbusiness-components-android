package com.mercadolibre.android.mlbusinesscomponents.components.crossselling;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import com.mercadolibre.android.mlbusinesscomponents.R;
import com.mercadolibre.android.mlbusinesscomponents.components.utils.FontUtils;
import com.squareup.picasso.Picasso;

public class MLBusinessCrossSellingBoxView extends ConstraintLayout {

    public interface OnClickCrossSellingBoxView {
        void OnClickCrossSellingButton(@NonNull final String deepLink);
    }

    public MLBusinessCrossSellingBoxView(final Context context) {
        this(context, null);
    }

    public MLBusinessCrossSellingBoxView(final Context context, final AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MLBusinessCrossSellingBoxView(final Context context, final AttributeSet attrs,
        final int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inflate(context, R.layout.ml_view_crossselling, this);
    }

    public void init(@NonNull final MLBusinessCrossSellingBoxData businessCrossSellingBoxData,
        @NonNull final OnClickCrossSellingBoxView onClick) {

        loadImageUrl(businessCrossSellingBoxData.getIconUrl());

        AppCompatTextView crossSellingTitle = findViewById(R.id.crossSellingTitle);
        crossSellingTitle.setText(businessCrossSellingBoxData.getText());

        final AppCompatTextView crossSellingAction = findViewById(R.id.crossSellingAction);
        crossSellingAction.setText(businessCrossSellingBoxData.getButtonTitle());
        crossSellingAction.setOnClickListener(v -> onClick
            .OnClickCrossSellingButton(businessCrossSellingBoxData.getButtonDeepLink()));

        FontUtils.setFontRegular(crossSellingTitle);
        FontUtils.setFontSemiBold(crossSellingAction);
    }

    private void loadImageUrl(@NonNull final String url) {
        final Context context;
        if ((context = getContext()) != null) {
            Picasso.with(context)
                .load(url)
                .placeholder(R.drawable.skeleton)
                .into((AppCompatImageView) findViewById(R.id.crossSellingImage));
        }
    }
}