package com.mercadolibre.android.mlbusinesscomponents.components.crossselling;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import android.util.AttributeSet;
import com.mercadolibre.android.mlbusinesscomponents.R;
import com.mercadolibre.android.picassodiskcache.PicassoDiskLoader;

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
        ((AppCompatTextView) findViewById(R.id.crossSellingTitle))
            .setText(businessCrossSellingBoxData.getText());
        final AppCompatTextView action = findViewById(R.id.crossSellingAction);
        action.setText(businessCrossSellingBoxData.getButtonTitle());
        action.setOnClickListener(v -> onClick
            .OnClickCrossSellingButton(businessCrossSellingBoxData.getButtonDeepLink()));
    }

    private void loadImageUrl(@NonNull final String url) {
        final Context context;
        if ((context = getContext()) != null) {
            PicassoDiskLoader.get(context)
                .load(url)
                .placeholder(R.drawable.skeleton)
                .into((AppCompatImageView) findViewById(R.id.crossSellingImage));
        }
    }
}