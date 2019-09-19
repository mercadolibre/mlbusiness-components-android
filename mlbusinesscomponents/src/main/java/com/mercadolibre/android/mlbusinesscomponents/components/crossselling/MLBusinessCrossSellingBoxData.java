package com.mercadolibre.android.mlbusinesscomponents.components.crossselling;

import android.support.annotation.NonNull;

public interface MLBusinessCrossSellingBoxData {
    @NonNull
    String getIconUrl();

    @NonNull
    String getText();

    @NonNull
    String getButtonTitle();

    @NonNull
    String getButtonDeepLink();
}
