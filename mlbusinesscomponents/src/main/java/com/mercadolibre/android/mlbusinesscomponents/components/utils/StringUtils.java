package com.mercadolibre.android.mlbusinesscomponents.components.utils;

import android.support.annotation.Nullable;

public class StringUtils {
    public static boolean isValidString(@Nullable final String s) {
        return s != null && !s.isEmpty();
    }
}
