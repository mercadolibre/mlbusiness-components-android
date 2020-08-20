package com.mercadolibre.android.mlbusinesscomponentsapp;

import androidx.annotation.NonNull;
import com.mercadolibre.android.mlbusinesscomponents.components.crossselling.MLBusinessCrossSellingBoxData;

public class MLBusinessCrossSellingBoxDataSample implements MLBusinessCrossSellingBoxData {
    @NonNull
    @Override
    public String getIconUrl() {
        return "https://www.pngrepo.com/png/4897/170/gift.png";
    }

    @NonNull
    @Override
    public String getText() {
        return "Gana $50 de regalo para tus pagos diarios";
    }

    @NonNull
    @Override
    public String getButtonTitle() {
        return "Invita a más amigos a usar la app";
    }

    @NonNull
    @Override
    public String getButtonDeepLink() {
        return "https://www.mercadolibre.com.ar/";
    }
}
