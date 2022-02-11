package com.mercadolibre.android.mlbusinesscomponents.components.adbanner;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import com.mercadolibre.android.mlbusinesscomponents.R;
import com.mercadolibre.android.picassodiskcache.PicassoDiskLoader;

public class MLBusinessAdBannerView extends ConstraintLayout {
        private final AppCompatImageView image;

    public interface OnClickAdBannerView {
        void OnClickAdBannerViewLink(@NonNull final String deepLink);
    }

    public MLBusinessAdBannerView(final Context context) {
        this(context, null);
    }

    public MLBusinessAdBannerView(final Context context, final AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MLBusinessAdBannerView(final Context context, final AttributeSet attrs,
                                         final int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inflate(context, R.layout.ml_view_ad_banner, this);
        if (getBackground() == null) {
            setBackground(ContextCompat.getDrawable(getContext(), R.drawable.ad_banner_background));
        }
        image = findViewById(R.id.adBannerImage);
    }

    public void init(@NonNull final MLBusinessAdBannerData businessAdBannerData,
                     @NonNull final OnClickAdBannerView onClick) {
        loadImageUrl(businessAdBannerData.getImageUrl());
        image.setOnClickListener(v -> onClick
                .OnClickAdBannerViewLink(businessAdBannerData.getUrlDeepLink()));
    }

    private void loadImageUrl(@NonNull final String url) {
        final Context context;
        if ((context = getContext()) != null) {
            PicassoDiskLoader.get(context)
                    .load(url)
                    .transform(new RoundedDrawableTransform(6, 6))
                    .placeholder(R.drawable.rectangle_skeleton)
                    .into(image);
        }
    }
}
