package com.mercadolibre.android.mlbusinesscomponents.common;

import android.content.Context;
import android.content.res.Resources;
import android.net.Uri;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import android.text.TextUtils;
import android.util.Patterns;
import com.facebook.common.util.UriUtil;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.backends.pipeline.PipelineDraweeControllerBuilder;
import com.facebook.drawee.controller.ControllerListener;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TouchpointAssetLoader {
    private static final String IDENTIFIER_TEMPLATE = "%s%s";
    private static final String DRAWABLE_RESOURCES_TYPE = "drawable";

    private final SimpleDraweeView imageContainer;
    private final String imageSource;
    private final ControllerListener listener;
    /* default */ final int defaultLocalSource;
    private final String prefix;
    private final PipelineDraweeControllerBuilder controllerBuilder;

    /**
     * Constructor
     *
     * @param builder A {@link Builder}
     * @param controllerBuilder fresco controller (needed for testing)
     */
    @VisibleForTesting
    /* default */ TouchpointAssetLoader(final Builder builder,
        final PipelineDraweeControllerBuilder controllerBuilder) {
        imageContainer = builder.imageContainer;
        imageSource = builder.imageSource;
        listener = builder.listener;
        prefix = builder.prefix;
        defaultLocalSource = getLocalIconId(builder.defaultLocalSource);
        this.controllerBuilder = controllerBuilder;
    }

    /**
     * Constructor
     *
     * @param builder A {@link Builder}
     */
    /* default */ TouchpointAssetLoader(final Builder builder) {
        imageContainer = builder.imageContainer;
        imageSource = builder.imageSource;
        prefix = builder.prefix;
        listener = builder.listener;
        defaultLocalSource = getLocalIconId(builder.defaultLocalSource);
        controllerBuilder = Fresco.newDraweeControllerBuilder();
    }

    /**
     * Create Image loader builder
     *
     * @return A {@link Builder}
     */
    public static Builder create() {
        return new Builder();
    }

    /* default */ void load() {
        if (isUrl()) {
            setImageURL();
        } else {
            final int localIconId = getLocalIconId(imageSource);
            if (localIconId == 0) {
                // no op.. should set local resources
            } else {
                setLocalImage(localIconId);
            }
        }
    }

    private void setImageURL() {
        final DraweeController controller = controllerBuilder
            .setUri(imageSource)
            .setControllerListener(listener)
            .build();
        imageContainer.setController(controller);
    }

    /* default */ void setLocalImage(final int resource) {
        try {
            final Uri uri = new Uri.Builder()
                .scheme(UriUtil.LOCAL_RESOURCE_SCHEME)
                .path(String.valueOf(resource))
                .build();
            imageContainer.setImageURI(uri.toString());
        } catch (final UnsupportedOperationException e) {
            //no op..
        }
    }

    /* default */ int getLocalIconId(final String resource) {
        if (TextUtils.isEmpty(resource)) {
            return 0;
        }
        final Context context = imageContainer.getContext();
        final Resources resources = context.getResources();
        return resources.getIdentifier(String.format(IDENTIFIER_TEMPLATE, prefix, resource),
            DRAWABLE_RESOURCES_TYPE, context.getPackageName());
    }

    private boolean isUrl() {
        final Pattern urlPattern = Patterns.WEB_URL;
        if (imageSource != null) {
            final Matcher utlMatcher = urlPattern.matcher(imageSource.toLowerCase(Locale.getDefault()));
            return utlMatcher.matches();
        }
        return false;
    }

    public static class Builder {

        /* default */ SimpleDraweeView imageContainer;
        /* default */ ControllerListener listener;
        /* default */ String imageSource;
        /* default */ String defaultLocalSource;
        /* default */ String prefix;

        /**
         * Sets the imageContainer.
         *
         * @param imageContainer The image container
         * @return The builder.
         */
        public Builder withContainer(@NonNull final SimpleDraweeView imageContainer) {
            this.imageContainer = imageContainer;
            return this;
        }

        /**
         * Sets the imageSource.
         *
         * @param imageSource The image
         * @return The builder.
         */
        public Builder withSource(@NonNull final String imageSource) {
            this.imageSource = imageSource;
            return this;
        }

        /**
         * Sets the controller listener.
         *
         * @param listener The controller listener
         * @return The builder.
         */
        public Builder withControllerListener(@NonNull final ControllerListener listener) {
            this.listener = listener;
            return this;
        }

        /**
         * Sets default local source.
         *
         * @param defaultLocalSource The local default image
         * @return The builder.
         */
        public Builder withDefaultLocalSource(final String defaultLocalSource) {
            this.defaultLocalSource = defaultLocalSource;
            return this;
        }

        /**
         * Sets local source prefix.
         *
         * @param prefix The resource prefix
         * @return The builder.
         */
        public Builder withPrefix(final String prefix) {
            this.prefix = prefix;
            return this;
        }

        /**
         * Load image
         */
        public void load() {
            final TouchpointAssetLoader loader = new TouchpointAssetLoader(this);
            loader.load();
        }
    }
}
