package com.mercadolibre.android.mlbusinesscomponents.components.adbanner;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;

import androidx.annotation.NonNull;

import com.squareup.picasso.Transformation;

public class RoundedDrawableTransform implements Transformation {
    private static final String KEY_ROUNDED = "rounded";

    private final int radius;
    private final int margin;

    public RoundedDrawableTransform(final int radius, final int margin) {
        this.radius = radius;
        this.margin = margin;
    }

    @Override
    public Bitmap transform(@NonNull final Bitmap source) {
        final Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setShader(new BitmapShader(source, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP));

        final Bitmap squaredBitmap = Bitmap.createBitmap(source.getWidth(), source.getHeight(), Bitmap.Config.ARGB_8888);
        final Canvas canvas = new Canvas(squaredBitmap);
        canvas.drawRoundRect(new RectF(margin, margin, source.getWidth() - margin, source.getHeight() - margin), radius, radius, paint);

        if (source != squaredBitmap) {
            source.recycle();
        }

        return squaredBitmap;
    }

    @Override
    public String key() {
        return KEY_ROUNDED;
    }
}
