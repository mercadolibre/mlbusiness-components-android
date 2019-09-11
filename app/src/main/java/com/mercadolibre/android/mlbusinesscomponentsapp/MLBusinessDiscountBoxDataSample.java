package com.mercadolibre.android.mlbusinesscomponentsapp;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.mercadolibre.android.mlbusinesscomponents.components.discount.MLBusinessDiscountBoxData;

import java.util.List;

public class MLBusinessDiscountBoxDataSample implements MLBusinessDiscountBoxData {

    @Nullable
    @Override
    public String getTitle() {
        return "200 descuentos";
    }

    @Nullable
    @Override
    public String getSubtitle() {
        return "35 exclusivos nivel 3";
    }

    @NonNull
    @Override
    public List<com.mercadolibre.android.mlbusinesscomponents.common.MLBusinessSingleItem> getItems() {
        return DataSampleUtils.getItems();
    }
}
