package com.mercadolibre.android.mlbusinesscomponentsapp;

import androidx.annotation.Nullable;
import com.mercadolibre.android.mlbusinesscomponents.common.MLBusinessSingleItem;

public class SingleItemDataSample implements MLBusinessSingleItem {
    private final String imageUrl;
    private final String titleLabel;
    private final String subtitleLabel;
    @Nullable
    private final String deepLinkItem;
    @Nullable
    private final String trackId;

    SingleItemDataSample(final String imageUrl, final String titleLabel,
        final String subtitleLabel,
        @Nullable final String deepLinkItem, @Nullable final String trackId) {
        this.imageUrl = imageUrl;
        this.titleLabel = titleLabel;
        this.subtitleLabel = subtitleLabel;
        this.deepLinkItem = deepLinkItem;
        this.trackId = trackId;
    }

    @Override
    public String getImageUrl() {
        return imageUrl;
    }

    @Override
    public String getTitleLabel() {
        return titleLabel;
    }

    @Override
    public String getSubtitleLabel() {
        return subtitleLabel;
    }

    @Nullable
    @Override
    public String getDeepLinkItem() {
        return deepLinkItem;
    }
    @Nullable
    @Override
    public String getTrackId() {
        return trackId;
    }
}
