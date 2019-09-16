package com.mercadolibre.android.mlbusinesscomponents.components.common.downloadapp;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import com.mercadolibre.android.mlbusinesscomponents.R;
import com.mercadolibre.android.ui.widgets.MeliButton;

public class MLBusinessDownloadAppView extends ConstraintLayout {

    public MLBusinessDownloadAppView(final Context context) {
        super(context);
        inflate(context, R.layout.ml_view_download, this);
    }

    public MLBusinessDownloadAppView(final Context context, final AttributeSet attrs) {
        super(context, attrs);
        inflate(context, R.layout.ml_view_download, this);
    }

    public MLBusinessDownloadAppView(final Context context, final AttributeSet attrs,
        final int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inflate(context, R.layout.ml_view_download, this);
    }

    public void init(@NonNull final MLBusinessDownloadAppData businessDownloadAppData) {
        findViewById(R.id.imageSite)
            .setBackgroundResource(businessDownloadAppData.getAppSite().getResource());
        ((AppCompatTextView) findViewById(R.id.titleDownload))
            .setText(businessDownloadAppData.getTitle());
        ((MeliButton) findViewById(R.id.downloadButton))
            .setText(businessDownloadAppData.getButtonTitle());
    }

    public void setOnClickDownloadButton(@NonNull final OnClickListener onClick) {
        findViewById(R.id.downloadButton).setOnClickListener(onClick);
    }

    public enum AppSite {
        ML(R.drawable.mercado_libre),
        MP(R.drawable.mercado_pago);

        private final int resource;

        AppSite(int resource) {
            this.resource = resource;
        }

        @DrawableRes
        int getResource() {
            return resource;
        }
    }
}
