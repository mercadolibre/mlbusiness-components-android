package com.mercadolibre.android.mlbusinesscomponents.components.utils;

import android.support.annotation.NonNull;
import android.widget.TextView;
import com.mercadolibre.android.ui.font.Font;
import com.mercadolibre.android.ui.font.TypefaceHelper;

public final class FontUtils {

    public static<T extends TextView> void setFontSemiBold(@NonNull final T view) {
        TypefaceHelper.setTypeface(view, Font.SEMI_BOLD);
    }

    public static<T extends TextView> void setFontRegular(@NonNull final T view) {
        TypefaceHelper.setTypeface(view, Font.REGULAR);
    }

    public static<T extends TextView> void setFontBold(@NonNull final T view) {
        TypefaceHelper.setTypeface(view, Font.BOLD);
    }
}
