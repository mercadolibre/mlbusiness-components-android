package com.mercadolibre.android.mlbusinesscomponents.common;

import android.support.annotation.Nullable;
import java.util.Map;

public interface MLBusinessSingleItem {
    String getImageUrl();
    String getTitleLabel();
    String getSubtitleLabel();
    @Nullable
    String getDeepLinkItem();
    @Nullable
    @Deprecated
    String getTrackId();

    @Nullable
    default Map<String, Object> getEventData() {
        return null;
    }
}
