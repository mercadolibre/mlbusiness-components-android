package com.mercadolibre.android.mlbusinesscomponents.components.row.model;

import com.mercadolibre.android.mlbusinesscomponents.components.pickup.model.DescriptionItemsInterface;
import com.mercadolibre.android.mlbusinesscomponents.components.pill.model.PillResponseInterface;
import java.util.List;

public interface TouchpointRowItemInterface {

    String getLeftImage();

    String getLeftImageAccessory();

    String getMainTitle();

    default String getMainTitleStatus() {
        return null;
    }

    String getMainSubtitle();

    List<DescriptionItemsInterface> getMainDescription();

    List<DescriptionItemsInterface> getMainCharacteristics();

    default List<DescriptionItemsInterface> getStatusDescription() {
        return null;
    }

    String getRightTopLabel();

    String getRightPrimaryLabel();

    String getRightSecondaryLabel();

    String getRightMiddleLabel();

    PillResponseInterface getRightBottomInfo();

    String getLink();

    String getRightLabelStatus();

    default String getLeftImageStatus() {
        return null;
    }

    boolean isValid();

    default MainTitleTopInterface getMainTitleTop() { return null; }
}
