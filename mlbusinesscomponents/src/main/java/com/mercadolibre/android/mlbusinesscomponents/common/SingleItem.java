package com.mercadolibre.android.mlbusinesscomponents.commons;

import android.support.annotation.Nullable;

public interface SingleItem {
    String getImageUrl();
    String getTitleLabel();
    String getSubtitleLabel();
    @Nullable
    String getDeepLinkItem();
    @Nullable
    String getTrackId();
}
