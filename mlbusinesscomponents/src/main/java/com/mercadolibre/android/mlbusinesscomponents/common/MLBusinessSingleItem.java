package com.mercadolibre.android.mlbusinesscomponents.common;

import android.support.annotation.Nullable;

public interface MLBusinessSingleItem {
    String getImageUrl();
    String getTitleLabel();
    String getSubtitleLabel();
    @Nullable
    String getDeepLinkItem();
    @Nullable
    String getTrackId();
}
