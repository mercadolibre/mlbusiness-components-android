package com.mercadolibre.android.mlbusinesscomponents.components.common;

import androidx.annotation.NonNull;

public interface MLBusinessInfoData {

    @NonNull
    String getIcon();

    @NonNull
    String getDescription();

    @NonNull
    String getIconBackgroundHexaColor();

    int getLogoSize();

    int getTextSize();

}
