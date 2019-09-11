package com.mercadolibre.android.mlbusinesscomponents.components.utils;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.TypedValue;

public class ScaleUtils {

    public static float getPxFromDp(@NonNull final Context context, final float dp) {
        return dp * context.getResources().getDisplayMetrics().density;
    }

    public static int getPxFromSp(@NonNull final Context context, final float sp) {
        return (int) TypedValue
            .applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, context.getResources().getDisplayMetrics());
    }
}
