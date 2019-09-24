package com.mercadolibre.android.mlbusinesscomponentsapp;

import android.support.annotation.NonNull;
import com.mercadolibre.android.mlbusinesscomponents.components.loyalty.MLBusinessLoyaltyHeaderData;

public class MLBusinessLoyaltyHeaderDataSample implements MLBusinessLoyaltyHeaderData {

    @NonNull
    @Override
    public String getBackgroundHexaColor() {
        return "#1AC2B0";
    }

    @NonNull
    @Override
    public String getPrimaryHexaColor() {
        return "#FFFFFF";
    }

    @NonNull
    @Override
    public String getSecondaryHexaColor() {
        return "#A3E6DF";
    }

    @Override
    public int getRingNumber() {
        return 2;
    }

    @Override
    public float getRingPercentage() {
        return 0.66f;
    }

    @Override
    public String getTitle() {
        return "Beneficios";
    }

    @Override
    public String getSubtitle() {
        return "Nivel 2 - Mercado Puntos";
    }
}
