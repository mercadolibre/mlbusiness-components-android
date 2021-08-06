package com.mercadolibre.android.mlbusinesscomponentsapp;

import androidx.annotation.NonNull;

import com.mercadolibre.android.mlbusinesscomponents.components.loyalty.MLBusinessLoyaltyHeaderData;

public class MLBusinessLoyaltyHeaderDataSampleWithSubscription implements MLBusinessLoyaltyHeaderData {

    @NonNull
    @Override
    public String getBackgroundHexaColor() {
        return "#b00c94";
    }

    @NonNull
    @Override
    public String getPrimaryHexaColor() {
        return "#FFFFFF";
    }

    @NonNull
    @Override
    public String getSecondaryHexaColor() {
        return "#FFFFFF";
    }

    @Override
    public int getRingNumber() {
        return 0;
    }

    @Override
    public float getRingPercentage() {
        return 0;
    }

    @Override
    public String getTitle() {
        return null;
    }

    @Override
    public String getSubtitle() {
        return "Suscríbete al nivel 6 por R$ 19,90 / mes";
    }
}
