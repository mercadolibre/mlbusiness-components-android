package com.mercadolibre.android.mlbusinesscomponents.components.row.model;

import com.mercadolibre.android.mlbusinesscomponents.components.pill.model.PillResponseInterface;
import java.util.List;

public interface TouchpointRowItemInterface {

    String getLeftImage();

    String getLeftImageAccessory();

    String getMainTitle();

    String getMainSubtitle();

    List<DescriptionItemsInterface> getMainDescription();

    String getRightTopLabel();

    String getRightPrimaryLabel();

    String getRightSecondaryLabel();

    String getRightMiddleLabel();

    PillResponseInterface getRightBottomInfo();

    String getLink();

    String getRightLabelStatus();

    boolean isValid();
}
