package com.mercadolibre.android.mlbusinesscomponents.components.common.crossselling;

import android.support.annotation.NonNull;

public interface MLBusinessCrossSellingBoxData {
    @NonNull
    String getIconUrl();

    @NonNull
    String getTitle();

    @NonNull
    String getButtonTitle();

    @NonNull
    String getButtonDeepLink();
}
