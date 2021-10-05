package com.mercadolibre.android.mlbusinesscomponents.components.pickup;

import com.mercadolibre.android.mlbusinesscomponents.R;

public enum SizeType {
    SMALL(R.dimen.ui_fontsize_xsmall),
    MEDIUM(R.dimen.ui_fontsize_medium),
    LARGE(R.dimen.ui_fontsize_xlarge);

    private final int fontSize;

    SizeType(final int fontSize) {
        this.fontSize = fontSize;
    }

    public int getFontSize() {
        return fontSize;
    }

}

