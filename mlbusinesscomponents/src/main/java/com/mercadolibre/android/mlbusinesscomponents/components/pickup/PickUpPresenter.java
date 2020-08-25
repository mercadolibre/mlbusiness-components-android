package com.mercadolibre.android.mlbusinesscomponents.components.pickup;

import com.mercadolibre.android.mlbusinesscomponents.components.pickup.model.DescriptionItemsInterface;
import java.util.List;

public class PickUpPresenter {

    public void addDescriptionLabels(final List<DescriptionItemsInterface> mainDescription, final PickUpView pickUpView) {
        for (DescriptionItemsInterface item : mainDescription) {
            switch (item.getType()) {
            case "image":
                pickUpView.addImageDescription(item.getContent(), item.getColor());
                break;
            case "text":
                pickUpView.addTextDescription(item.getContent(), item.getColor());
                break;
            default:
                // no op..
            }
        }
    }
}


