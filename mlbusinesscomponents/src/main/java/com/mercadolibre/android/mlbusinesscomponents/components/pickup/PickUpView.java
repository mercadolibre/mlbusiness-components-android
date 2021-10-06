package com.mercadolibre.android.mlbusinesscomponents.components.pickup;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import androidx.annotation.Nullable;
import com.mercadolibre.android.mlbusinesscomponents.components.pickup.model.DescriptionItemsInterface;
import java.util.List;

public class PickUpView extends LinearLayout {

    private PickUpPresenter presenter;

    public PickUpView(final Context context) {
        this(context, null);
    }

    public PickUpView(final Context context, @Nullable final AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PickUpView(final Context context, @Nullable final AttributeSet attrs, final int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        presenter = new PickUpPresenter();
    }

    public void bindViews(@Nullable final String size, final List<DescriptionItemsInterface> mainDescription) {
        if (getChildCount() > 0) {
            removeAllViews();
        }
        setVisibility(VISIBLE);
        presenter.addDescriptionLabels(size, mainDescription, this);
    }

    public void addTextDescription(@Nullable final String size, final String content, final String color) {
        MainDescriptionLabelsText mainDescriptionLabelsText = new MainDescriptionLabelsText(getContext());
        mainDescriptionLabelsText.setText(size, content, color);
        addView(mainDescriptionLabelsText);
    }

    public void addImageDescription(@Nullable final String size, final String content, final String color) {
        MainDescriptionLabesImage mainDescriptionLabesImage = new MainDescriptionLabesImage(getContext());
        mainDescriptionLabesImage.setImage(size, content, color);
        addView(mainDescriptionLabesImage);
    }

    public void removeMargingStart() {
        final View firstItem = getChildAt(0);
        final LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) firstItem.getLayoutParams();
        if (layoutParams != null) {
            layoutParams.setMarginStart(0);
        }
    }
}
