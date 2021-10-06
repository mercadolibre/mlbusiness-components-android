package com.mercadolibre.android.mlbusinesscomponents.components.pickup;

import com.mercadolibre.android.mlbusinesscomponents.R;

public enum SizeType {
    SMALL(R.dimen.ui_fontsize_xsmall, R.dimen.ui_1_75m),
    MEDIUM(R.dimen.ui_fontsize_small, R.dimen.ui_2m);

    private final int fontSize;
    private final int imageSize;

    SizeType(final int fontSize, final int imageSize) {
        this.fontSize = fontSize;
        this.imageSize = imageSize;
    }

    public int getFontSize() {
        return fontSize;
    }

    public int getImageSize() {
        return imageSize;
    }

}

