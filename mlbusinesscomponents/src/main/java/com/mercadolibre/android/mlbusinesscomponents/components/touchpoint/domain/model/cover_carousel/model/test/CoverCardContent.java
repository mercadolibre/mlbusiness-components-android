package com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.cover_carousel.model.test;

import android.text.TextUtils;
import com.google.gson.annotations.SerializedName;
import com.mercadolibre.android.mlbusinesscomponents.components.pickup.model.DescriptionItemsInterface;
import com.mercadolibre.android.mlbusinesscomponents.components.pill.model.PillResponseInterface;
import com.mercadolibre.android.mlbusinesscomponents.components.row.model.test.DescriptionItems;
import com.mercadolibre.android.mlbusinesscomponents.components.row.model.test.PillResponse;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.cover_carousel.model.cover_card.CoverCardContentInterface;
import java.util.ArrayList;
import java.util.List;

public class CoverCardContent implements CoverCardContentInterface {

    private final String cover;
    private final String leftImage;
    private final String leftImageAccessory;
    private final String mainTitle;
    private final String mainSubtitle;
    private final List<DescriptionItems> mainDescription;
    @SerializedName("main_secondary_description")
    private final List<DescriptionItems> mainCharacteristics;
    private final String rightTopLabel;
    private final String rightPrimaryLabel;
    private final String rightSecondaryLabel;
    private final String rightMiddleLabel;
    private final PillResponse pillResponse;
    private final String link;
    private final String rightLabelStatus;

    public CoverCardContent(final String cover, final String leftImage, final String leftImageAccessory,
        final String mainTitle, final String mainSubtitle,
        final List<DescriptionItems> mainDescription,
        final List<DescriptionItems> mainCharacteristics, final String rightTopLabel,
        final String rightPrimaryLabel, final String rightSecondaryLabel, final String rightMiddleLabel,
        final PillResponse pillResponse, final String link, final String rightLabelStatus) {
        this.cover = cover;
        this.leftImage = leftImage;
        this.leftImageAccessory = leftImageAccessory;
        this.mainTitle = mainTitle;
        this.mainSubtitle = mainSubtitle;
        this.mainDescription = mainDescription;
        this.mainCharacteristics = mainCharacteristics;
        this.rightTopLabel = rightTopLabel;
        this.rightPrimaryLabel = rightPrimaryLabel;
        this.rightSecondaryLabel = rightSecondaryLabel;
        this.rightMiddleLabel = rightMiddleLabel;
        this.pillResponse = pillResponse;
        this.link = link;
        this.rightLabelStatus = rightLabelStatus;
    }

    @Override
    public String getCover() {
        return cover;
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
        return rightPrimaryLabel;
    }

    @Override
    public String getRightSecondaryLabel() {
        return rightSecondaryLabel;
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
        return !TextUtils.isEmpty(getLeftImage())
            || !TextUtils.isEmpty(getMainTitle())
            || !TextUtils.isEmpty(getMainSubtitle())
            || !TextUtils.isEmpty(getLink());
    }
}
