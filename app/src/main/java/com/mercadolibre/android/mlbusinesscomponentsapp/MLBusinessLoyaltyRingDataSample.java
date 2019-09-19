package com.mercadolibre.android.mlbusinesscomponentsapp;

import com.mercadolibre.android.mlbusinesscomponents.components.loyalty.MLBusinessLoyaltyRingData;

public class MLBusinessLoyaltyRingDataSample implements MLBusinessLoyaltyRingData {
    @Override
    public String getRingHexaColor() {
        return "#3483FA";
    }

    @Override
    public int getRingNumber() {
        return 3;
    }

    @Override
    public float getRingPercentage() {
        return 0.6f;
    }

    @Override
    public String getTitle() {
        return "Sumaste 20 Mercado Puntos";
    }

    @Override
    public String getButtonTitle() {
        return "Mis beneficios";
    }

    @Override
    public String getButtonDeepLink() {
        return "https://www.mercadolibre.com.ar/";
    }
}
