package com.mercadolibre.android.mlbusinesscomponents.components.pickup;

import android.content.Context;
import android.graphics.Color;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.mercadolibre.android.mlbusinesscomponents.R;

public class MainDescriptionLabelsText extends FrameLayout {

    private final TextView textView;

    public MainDescriptionLabelsText(@NonNull final Context context) {
        this(context, null);
    }

    public MainDescriptionLabelsText(@NonNull final Context context, @Nullable final AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MainDescriptionLabelsText(@NonNull final Context context, @Nullable final AttributeSet attrs, final int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inflate(context, R.layout.description_labels_text, this);
        textView = findViewById(R.id.main_description_text);
        final FrameLayout.LayoutParams layoutParams = (LayoutParams) getLayoutParams();
        if (layoutParams != null) {
            layoutParams.setMarginStart(getResources().getDimensionPixelSize(R.dimen.ui_025m));
        }

    }

    public void setText(@Nullable final String size, final String content, final String color) {
        float textSize;
        if (content != null && !content.isEmpty()) {
            textView.setText(content);
        }

        if (size != null && !size.isEmpty()) {
            textSize = getResources().getDimension(SizeType.getSizeOrDefault(size.toUpperCase(), SizeType.SMALL.getFontSize()));
            textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
        }

        if (color == null || color.isEmpty()) {
            textView.setTextColor(ContextCompat.getColor(getContext(), R.color.light_grey));
        } else {
            try {
                textView.setTextColor(Color.parseColor(color));
            }catch (Exception e){
                textView.setTextColor(ContextCompat.getColor(getContext(), R.color.light_grey));
            }
        }
    }
}
