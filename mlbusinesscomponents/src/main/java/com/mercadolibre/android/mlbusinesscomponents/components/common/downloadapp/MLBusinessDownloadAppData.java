package com.mercadolibre.android.mlbusinesscomponents.components.common.downloadapp;

import androidx.annotation.NonNull;

public interface MLBusinessDownloadAppData {
    @NonNull
    MLBusinessDownloadAppView.AppSite getAppSite();

    @NonNull
    String getTitle();

    @NonNull
    String getButtonTitle();

    @NonNull
    String getButtonDeepLink();
}
