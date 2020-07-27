package com.mercadolibre.android.mlbusinesscomponents.components.row.model.test;

import android.support.annotation.Keep;
import android.text.TextUtils;
import com.mercadolibre.android.mlbusinesscomponents.components.row.model.DescriptionItemsInterface;
import com.mercadolibre.android.mlbusinesscomponents.components.pill.model.PillResponseInterface;
import com.mercadolibre.android.mlbusinesscomponents.components.row.model.TouchpointRowItemInterface;
import java.util.ArrayList;
import java.util.List;

@Keep
public class TouchpointRowItem implements TouchpointRowItemInterface {

    @Override
    public String getLeftImage() {
        return "https://mla-s1-p.mlstatic.com/952848-MLA41109062105_032020-O.jpg";
    }

    @Override
    public String getLeftImageAccessory() {
        return "https://mla-s1-p.mlstatic.com/952848-MLA41109062105_032020-O.jpg";
    }

    @Override
    public String getMainTitle() {
        return "Pizza Vegana";
    }

    @Override
    public String getMainSubtitle() {
        return "Restaurante";
    }

    @Override
    public List<DescriptionItemsInterface> getMainDescription() {
        List<DescriptionItemsInterface> list = new ArrayList<>();
        DescriptionItems descriptionItems1 = new DescriptionItems("image", "https://mla-s1-p.mlstatic.com/952848-MLA41109062105_032020-O.jpg", "#000000");
        DescriptionItems descriptionItems2 = new DescriptionItems("text", "623m", "#73000000");
        DescriptionItems descriptionItems3 = new DescriptionItems("text", " · ", "#00as0000");
        DescriptionItems descriptionItems4 = new DescriptionItems("image", "https://mla-s1-p.mlstatic.com/952848-MLA41109062105_032020-O.jpg", "#000000");
        DescriptionItems descriptionItems5 = new DescriptionItems("text", "4.3 (24)", "#73000000");

        list.add(descriptionItems1);
        list.add(descriptionItems2);
        list.add(descriptionItems3);
        list.add(descriptionItems4);
        list.add(descriptionItems5);

        return list;
    }

    @Override
    public String getRightTopLabel() {
        return null;
    }

    @Override
    public String getRightPrimaryLabel() {
        return "10%";
    }

    @Override
    public String getRightSecondaryLabel() {
        return "OFF";
    }

    @Override
    public String getRightMiddleLabel() {
        return "Tope de $ 100";
    }

    @Override
    public PillResponseInterface getRightBottomInfo() {
        return new PillResponse("discount_payers_checked", "NIVEL 5", new FeatureFormatResponse("#FFFFFF", "#000000"));
    }

    @Override
    public String getLink() {
        return "mercadopago://home";
    }

    @Override
    public String getRightLabelStatus() {
        return "blocked";
    }

    @Override
    public boolean isValid() {
        return !TextUtils.isEmpty(getLeftImage())
            || !TextUtils.isEmpty(getMainTitle())
            || !TextUtils.isEmpty(getMainSubtitle())
            || !TextUtils.isEmpty(getLink());
    }
}
