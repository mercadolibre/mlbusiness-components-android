package com.mercadolibre.android.mlbusinesscomponents.components.utils;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

public final class ViewUtils {

    private ViewUtils() {
    }

    public static boolean loadOrGone(@NonNull final TextView textView, @Nullable final String text) {
        if (StringUtils.isValidString(text)) {
            textView.setVisibility(View.VISIBLE);
            textView.setText(text);
            return true;
        } else {
            textView.setVisibility(View.GONE);
            return false;
        }
    }
}