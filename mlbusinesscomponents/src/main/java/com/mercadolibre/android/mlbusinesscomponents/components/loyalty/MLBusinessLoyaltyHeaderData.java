package com.mercadolibre.android.mlbusinesscomponents.components.loyalty;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

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
