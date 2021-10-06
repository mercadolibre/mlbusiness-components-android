package com.mercadolibre.android.mlbusinesscomponents.components.pickup;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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

    public void setImage(@Nullable final String size, final String content, final String color) {
        AssetLoader.getImage(content, image, (shouldLoadImage -> {
            if (shouldLoadImage) {
                TouchpointAssetLoader.create().withContainer(image).withSource(content).load();
            }
        }));

        if (color != null && !color.isEmpty()) {
            try {
                image.setColorFilter(Color.parseColor(color));
            } catch (Exception e) {
                //no op..
            }
        }

        if (size != null && !size.isEmpty()) {
            int params;
            try {
                SizeType sizeType = SizeType.valueOf(size.toUpperCase());
                params = getResources().getDimensionPixelSize(sizeType.getImageSize());
                image.setLayoutParams(new LayoutParams(params, params));
            } catch (Exception e) {
                params = getResources().getDimensionPixelSize(SizeType.SMALL.getImageSize());
                image.setLayoutParams(new LayoutParams(params, params));
            }
        }
        image.setVisibility(VISIBLE);
    }
}
