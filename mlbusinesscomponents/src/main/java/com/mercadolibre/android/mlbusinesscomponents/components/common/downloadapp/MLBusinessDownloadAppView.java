package com.mercadolibre.android.mlbusinesscomponents.components.common.downloadapp;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import com.mercadolibre.android.mlbusinesscomponents.R;
import com.mercadolibre.android.ui.widgets.MeliButton;
import java.lang.ref.WeakReference;

public class MLBusinessDownloadAppView extends ConstraintLayout {

    public interface OnClickDownloadApp {
        void OnClickDownloadAppButton(@NonNull final String deepLink);
    }

    private WeakReference<OnClickDownloadApp> onClickDownloadApp;

    public MLBusinessDownloadAppView(final Context context) {
        this(context, null);
    }

    public MLBusinessDownloadAppView(final Context context, final AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MLBusinessDownloadAppView(final Context context, final AttributeSet attrs, final int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        configureDownloadAppView(context);
    }

    private void configureDownloadAppView(final Context context) {
        inflate(context, R.layout.ml_view_download, this);
        if (getBackground() == null) {
            setBackground(ContextCompat.getDrawable(getContext(), R.drawable.download_background));
        }
    }

    public void init(@NonNull final MLBusinessDownloadAppData businessDownloadAppData,
        @NonNull final OnClickDownloadApp onClick) {
        findViewById(R.id.imageSite).setBackgroundResource(businessDownloadAppData.getAppSite().getResource());
        ((AppCompatTextView) findViewById(R.id.titleDownload)).setText(businessDownloadAppData.getTitle());

        final MeliButton downloadButton = findViewById(R.id.downloadButton);
        downloadButton.setText(businessDownloadAppData.getButtonTitle());
        onClickDownloadApp = new WeakReference<>(onClick);
        downloadButton.setOnClickListener(v -> {
            final OnClickDownloadApp listener = onClickDownloadApp.get();
            if (listener != null) {
                listener.OnClickDownloadAppButton(businessDownloadAppData.getButtonDeepLink());
            }
        });
    }

    public void updateView(@NonNull final MLBusinessDownloadAppData businessDownloadAppData,
        @NonNull final OnClickDownloadApp onClick) {
        init(businessDownloadAppData, onClick);
    }

    public enum AppSite {
        ML(R.drawable.mercado_libre),
        MP(R.drawable.mercado_pago);

        private final int resource;

        AppSite(final int resource) {
            this.resource = resource;
        }

        @DrawableRes
        int getResource() {
            return resource;
        }
    }
}