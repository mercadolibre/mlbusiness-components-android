package com.mercadolibre.android.mlbusinesscomponentsapp;

import android.support.annotation.NonNull;
import com.mercadolibre.android.mlbusinesscomponents.components.common.downloadapp.MLBusinessDownloadAppData;
import com.mercadolibre.android.mlbusinesscomponents.components.common.downloadapp.MLBusinessDownloadAppView;

public class MLBusinessDownloadAppDataSample implements MLBusinessDownloadAppData {
    @NonNull
    @Override
    public MLBusinessDownloadAppView.AppSite getAppSite() {
        return MLBusinessDownloadAppView.AppSite.MP;
    }

    @NonNull
    @Override
    public String getTitle() {
        return "Exclusivo con la app de Mercado Pago";
    }

    @NonNull
    @Override
    public String getButtonTitle() {
        return "Descargar";
    }

    @NonNull
    @Override
    public String getButtonDeepLink() {
        return "https://www.mercadolibre.com.ar/";
    }
}
