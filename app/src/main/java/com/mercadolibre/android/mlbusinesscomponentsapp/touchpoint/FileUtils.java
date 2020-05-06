package com.mercadolibre.android.mlbusinesscomponentsapp.touchpoint;

import android.content.Context;
import android.support.annotation.RawRes;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

public final class FileUtils {

    private FileUtils() {
        // no-op
    }

    /**
     * Load file from raw
     *
     * @param context The activity
     * @param resId The res id
     * @return A file content as string
     */
    public static String loadFromRaw(final Context context, @RawRes final int resId) {
        try (final InputStream is = context.getResources().openRawResource(resId)) {
            final Writer writer = new StringWriter();
            final char[] buffer = new char[1024];
            final Reader reader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
            int n;
            while ((n = reader.read(buffer)) != -1) {
                writer.write(buffer, 0, n);
            }
            return writer.toString();
        } catch (final IOException e) {
            throw new RuntimeException(e);
        }
    }
}
