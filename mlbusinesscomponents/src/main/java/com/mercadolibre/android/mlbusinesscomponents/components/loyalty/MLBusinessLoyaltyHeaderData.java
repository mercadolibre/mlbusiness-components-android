package com.mercadolibre.android.mlbusinesscomponents.components.loyalty;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public interface MLBusinessLoyaltyHeaderData {

    @NonNull
    String getBackgroundHexaColor();

    @NonNull
    String getPrimaryHexaColor();

    @NonNull
    String getSecondaryHexaColor();

    int getRingNumber();

    float getRingPercentage();

    @Nullable
    String getTitle();

    @Nullable
    String getSubtitle();

}
