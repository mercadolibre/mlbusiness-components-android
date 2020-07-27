package com.mercadolibre.android.mlbusinesscomponents.components.row;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import com.facebook.drawee.view.SimpleDraweeView;
import com.mercadolibre.android.mlbusinesscomponents.R;
import com.mercadolibre.android.mlbusinesscomponents.common.TouchpointAssetLoader;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.view.carousel.card.AssetLoader;

public class MainDescriptionLabesImage extends FrameLayout {

    private final SimpleDraweeView image;

    public MainDescriptionLabesImage(@NonNull final Context context) {
        this(context, null);
    }

    public MainDescriptionLabesImage(@NonNull final Context context, @Nullable final AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MainDescriptionLabesImage(@NonNull final Context context, @Nullable final AttributeSet attrs, final int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inflate(context, R.layout.description_labels_icon, this);
        image = findViewById(R.id.main_description_icon);
    }

    public void setImage(final String content, final String color) {
        AssetLoader.getImage(content, image, (shouldLoadImage -> {
            image.setVisibility(VISIBLE);

            if (shouldLoadImage) {
                TouchpointAssetLoader.create().withContainer(image).withSource(content).load();
            }
        }));

    }
}
