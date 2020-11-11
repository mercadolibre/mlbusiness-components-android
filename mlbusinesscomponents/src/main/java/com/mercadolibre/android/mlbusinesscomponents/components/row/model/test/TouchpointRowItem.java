package com.mercadolibre.android.mlbusinesscomponents.components.row.model.test;

import android.text.TextUtils;
import androidx.annotation.Keep;
import com.mercadolibre.android.mlbusinesscomponents.components.pill.model.PillResponseInterface;
import com.mercadolibre.android.mlbusinesscomponents.components.pickup.model.DescriptionItemsInterface;
import com.mercadolibre.android.mlbusinesscomponents.components.row.model.TouchpointRowItemInterface;
import java.util.ArrayList;
import java.util.List;

@Keep
public class TouchpointRowItem implements TouchpointRowItemInterface {

    private final String leftImage;
    private final String leftImageAccessory;
    private final String mainTitle;
    private final String mainSubtitle;
    private final List<DescriptionItems> mainDescription;
    private final List<DescriptionItems> mainCharacteristics;
    private final String rightTopLabel;
    private final String rightPrimarylabel;
    private final String rightSecondarylabel;
    private final String rightMiddleLabel;
    private final PillResponse pillResponse;
    private final String link;
    private final String rightLabelStatus;
    private final boolean isValid;

    public TouchpointRowItem() {
        leftImage = "https://mla-s1-p.mlstatic.com/952848-MLA41109062105_032020-O.jpg";
        leftImageAccessory = "https://mla-s1-p.mlstatic.com/952848-MLA41109062105_032020-O.jpg";
        mainTitle = "Pizza Vegana";
        mainSubtitle = "Restaurante";

        List<DescriptionItems> list = new ArrayList<>();
        DescriptionItems descriptionItems1 =
            new DescriptionItems("image", "https://mla-s1-p.mlstatic.com/952848-MLA41109062105_032020-O.jpg", "#000000");
        DescriptionItems descriptionItems2 = new DescriptionItems("text", "623m", "#73000000");
        DescriptionItems descriptionItems3 = new DescriptionItems("text", " · ", "#00as0000");
        DescriptionItems descriptionItems4 =
            new DescriptionItems("image", "https://mla-s1-p.mlstatic.com/952848-MLA41109062105_032020-O.jpg", "#000000");
        DescriptionItems descriptionItems5 = new DescriptionItems("text", "4.3 (24)", "#73000000");

        list.add(descriptionItems1);
        list.add(descriptionItems2);
        list.add(descriptionItems3);
        list.add(descriptionItems4);
        list.add(descriptionItems5);

        mainDescription = new ArrayList<>(list);
        mainCharacteristics = new ArrayList<>(list);
        rightTopLabel = null;
        rightPrimarylabel = "10%";
        rightSecondarylabel = "OFF";
        rightMiddleLabel = "Tope de $ 100";
        pillResponse = new PillResponse("discount_payers_checked", "NIVEL 5", new FeatureFormatResponse("#FFFFFF", "#000000"));
        link = "mercadopago://home";
        rightLabelStatus = "blocked";
        isValid = setIsValidState();
    }

    private boolean setIsValidState() {
        return !TextUtils.isEmpty(getLeftImage())
            || !TextUtils.isEmpty(getMainTitle())
            || !TextUtils.isEmpty(getMainSubtitle())
            || !TextUtils.isEmpty(getLink());
    }

    @Override
    public String getLeftImage() {
        return leftImage;
    }

    @Override
    public String getLeftImageAccessory() {
        return leftImageAccessory;
    }

    @Override
    public String getMainTitle() {
        return mainTitle;
    }

    @Override
    public String getMainSubtitle() {
        return mainSubtitle;
    }

    @Override
    public List<DescriptionItemsInterface> getMainDescription() {
        return new ArrayList<>(mainDescription);
    }

    @Override
    public List<DescriptionItemsInterface> getMainCharacteristics() {
        return new ArrayList<>(mainCharacteristics);
    }

    @Override
    public String getRightTopLabel() {
        return rightTopLabel;
    }

    @Override
    public String getRightPrimaryLabel() {
        return rightPrimarylabel;
    }

    @Override
    public String getRightSecondaryLabel() {
        return rightSecondarylabel;
    }

    @Override
    public String getRightMiddleLabel() {
        return rightMiddleLabel;
    }

    @Override
    public PillResponseInterface getRightBottomInfo() {
        return pillResponse;
    }

    @Override
    public String getLink() {
        return link;
    }

    @Override
    public String getRightLabelStatus() {
        return rightLabelStatus;
    }

    @Override
    public boolean isValid() {
        return isValid;
    }
}
