package com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.callback;

import android.support.annotation.Nullable;

public interface OnClickCallback {

    /**
     * On click callback
     *
     * @param deepLink The desiree link to lunch
     */
    void onClick(@Nullable final String deepLink);
}
