package com.mercadolibre.android.mlbusinesscomponentsapp;

import androidx.annotation.NonNull;

import com.mercadolibre.android.mlbusinesscomponents.components.adbanner.MLBusinessAdBannerData;

public class MLBusinessAdBannerDataSample implements MLBusinessAdBannerData {
    @NonNull
    @Override
    public String getImageUrl() {
        return "https://http2.mlstatic.com/D_NQ_750106-MLA46995964779_082021-F.webp";
    }

    @NonNull
    @Override
    public String getUrlDeepLink() {
        return "https://www.mercadolibre.com.ar/";
    }
}
