package com.mercadolibre.android.mlbusinesscomponents.components.utils;

import android.content.Context;
import androidx.annotation.NonNull;
import android.util.TypedValue;

public final class ScaleUtils {

    private ScaleUtils() {
        //no-op
    }

    public static float getPxFromDp(@NonNull final Context context, final float dp) {
        return Math.round(
            TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, context.getResources().getDisplayMetrics()));
    }

    public static int getPxFromSp(@NonNull final Context context, final float sp) {
        return Math.round(
                TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, context.getResources().getDisplayMetrics()));
    }
}
