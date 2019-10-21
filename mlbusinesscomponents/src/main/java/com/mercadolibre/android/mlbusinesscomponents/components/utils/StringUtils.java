package com.mercadolibre.android.mlbusinesscomponents.components.utils;

import android.support.annotation.Nullable;

public final class StringUtils {

    private StringUtils() {
    }

    public static boolean isValidString(@Nullable final String s) {
        return s != null && !s.isEmpty();
    }
}