package com.mercadolibre.android.mlbusinesscomponents.components.pickup;

import androidx.annotation.Nullable;
import com.mercadolibre.android.mlbusinesscomponents.components.pickup.model.DescriptionItemsInterface;
import java.util.List;

public class PickUpPresenter {

    private static final String TEXT = "text";

    public void addDescriptionLabels(@Nullable final String size, final List<DescriptionItemsInterface> mainDescription, final PickUpView pickUpView) {
        if (mainDescription == null) {
            return;
        }
        for (DescriptionItemsInterface item : mainDescription) {
            switch (item.getType()) {
            case "image":
                pickUpView.addImageDescription(item.getContent(), item.getColor());
                break;
            case TEXT:
                pickUpView.addTextDescription(size, item.getContent(), item.getColor());
                break;
            default:
                // no op..
            }
        }
        if (!mainDescription.isEmpty()) {
            final DescriptionItemsInterface firstItem = mainDescription.get(0);
            if (firstItem != null && TEXT.equals(firstItem.getType())){
                pickUpView.removeMargingStart();
            }
        }
    }
}


