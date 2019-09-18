package com.mercadolibre.android.mlbusinesscomponentsapp;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.mercadolibre.android.mlbusinesscomponents.components.discount.MLBusinessDiscountBoxData;
import com.mercadolibre.android.mlbusinesscomponents.components.loyalty.MLBusinessLoyaltyHeaderData;

import java.util.List;

public class MLBusinessLoyaltyHeaderDataSample implements MLBusinessLoyaltyHeaderData {

    @Override
    public String getBackgroundHexaColor() {
        return "#1AC2B0";
    }

    @Override
    public String getPrimaryHexaColor() {
        return "#FFFFFF";
    }

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
        return 66f;
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
