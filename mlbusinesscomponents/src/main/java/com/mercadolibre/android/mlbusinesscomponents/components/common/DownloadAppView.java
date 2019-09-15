package com.mercadolibre.android.mlbusinesscomponents.components.common;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import com.mercadolibre.android.mlbusinesscomponents.R;
import com.mercadolibre.android.ui.widgets.MeliButton;

public class DownloadAppView extends ConstraintLayout {

    public DownloadAppView(final Context context) {
        super(context);
        inflate(context, R.layout.ml_view_download, this);
    }

    public DownloadAppView(final Context context, final AttributeSet attrs) {
        super(context, attrs);
        inflate(context, R.layout.ml_view_download, this);
    }

    public DownloadAppView(final Context context, final AttributeSet attrs,
        final int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inflate(context, R.layout.ml_view_download, this);
    }

    public void init(@NonNull final AppSite site, @NonNull String title,
        @NonNull String buttonTitle) {
        findViewById(R.id.imageSite).setBackgroundResource(site.getResource());
        ((AppCompatTextView) findViewById(R.id.titleDownload)).setText(title);
        ((MeliButton) findViewById(R.id.downloadButton)).setText(buttonTitle);
    }

    public void setOnClickDownloadButton(@NonNull final OnClickListener onClick) {
        findViewById(R.id.downloadButton).setOnClickListener(onClick);
    }

    public enum AppSite {
        ML(R.drawable.ui_ic_clear_fullscreen),
        MP(R.drawable.ui_ic_clear_fullscreen);

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
