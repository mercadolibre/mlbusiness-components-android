package com.mercadolibre.android.mlbusinesscomponents.components.row;

import android.support.annotation.Keep;
import android.text.TextUtils;
import java.io.Serializable;
import java.util.List;

@Keep
public class TouchpointRowItem implements Serializable {

    private final String leftImage;
    private final String leftImageAccessory;
    private final String mainTitle;
    private final String mainSubtitle;
    private final List<DescriptionItems> mainDescription;
    private final String rightTopLabel;
    private final String rightPrimaryLabel;
    private final String rightSecondaryLabel;
    private final String rightMiddleLabel;
    private final PillResponse rightBottomInfo;
    private final String link;

    /**
     * Constructor
     *
     * @param leftImage The main image.
     * @param leftImageAccessory the image accessory
     * @param mainTitle the main title
     * @param mainSubtitle the main subtitle
     * @param mainDescription the main description with rating and distance
     * @param rightTopLabel the top label
     * @param rightPrimaryLabel the main label
     * @param rightSecondaryLabel the secondary label
     * @param rightMiddleLabel the label under the  main label
     * @param rightBottomInfo the bottom information
     * @param link the link where to push
     */
    public TouchpointRowItem(final String leftImage, final String leftImageAccessory, final String mainTitle, final String mainSubtitle,
        final List<DescriptionItems> mainDescription, final String rightTopLabel, final String rightPrimaryLabel,
        final String rightSecondaryLabel,
        final String rightMiddleLabel, final PillResponse rightBottomInfo, final String link) {
        this.leftImage = leftImage;
        this.leftImageAccessory = leftImageAccessory;
        this.mainTitle = mainTitle;
        this.mainSubtitle = mainSubtitle;
        this.mainDescription = mainDescription;
        this.rightTopLabel = rightTopLabel;
        this.rightPrimaryLabel = rightPrimaryLabel;
        this.rightSecondaryLabel = rightSecondaryLabel;
        this.rightMiddleLabel = rightMiddleLabel;
        this.rightBottomInfo = rightBottomInfo;
        this.link = link;
    }

    public String getLeftImage() {
        return leftImage;
    }

    public String getLeftImageAccessory() {
        return leftImageAccessory;
    }

    public String getMainTitle() {
        return mainTitle;
    }

    public String getMainSubtitle() {
        return mainSubtitle;
    }

    public List<DescriptionItems> getMainDescription() {
        return mainDescription;
    }

    public String getRightTopLabel() {
        return rightTopLabel;
    }

    public String getRightPrimaryLabel() {
        return rightPrimaryLabel;
    }

    public String getRightSecondaryLabel() {
        return rightSecondaryLabel;
    }

    public String getRightMiddleLabel() {
        return rightMiddleLabel;
    }

    public PillResponse getRightBottomInfo() {
        return rightBottomInfo;
    }

    public String getLink() {
        return link;
    }

    //CPD-OFF
    @SuppressWarnings("PMD.NPathComplexity")
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TouchpointRowItem)) {
            return false;
        }

        final TouchpointRowItem that = (TouchpointRowItem) o;

        if (leftImage != null ? !leftImage.equals(that.leftImage) : that.leftImage != null) {
            return false;
        }
        if (leftImageAccessory != null ? !leftImageAccessory.equals(that.leftImageAccessory) : that.leftImageAccessory != null) {
            return false;
        }
        if (mainTitle != null ? !mainTitle.equals(that.mainTitle) : that.mainTitle != null) {
            return false;
        }
        if (mainSubtitle != null ? !mainSubtitle.equals(that.mainSubtitle) : that.mainSubtitle != null) {
            return false;
        }
        if (mainDescription != null ? !mainDescription.equals(that.mainDescription) : that.mainDescription != null) {
            return false;
        }
        if (rightTopLabel != null ? !rightTopLabel.equals(that.rightTopLabel) : that.rightTopLabel != null) {
            return false;
        }
        if (rightPrimaryLabel != null ? !rightPrimaryLabel.equals(that.rightPrimaryLabel) : that.rightPrimaryLabel != null) {
            return false;
        }
        if (rightSecondaryLabel != null ? !rightSecondaryLabel.equals(that.rightSecondaryLabel) : that.rightSecondaryLabel != null) {
            return false;
        }
        if (rightMiddleLabel != null ? !rightMiddleLabel.equals(that.rightMiddleLabel) : that.rightMiddleLabel != null) {
            return false;
        }
        if (rightBottomInfo != null ? !rightBottomInfo.equals(that.rightBottomInfo) : that.rightBottomInfo != null) {
            return false;
        }
        return link != null ? link.equals(that.link) : that.link == null;
    }

    @SuppressWarnings({ "checkstyle:magicnumber", "PMD.NPathComplexity" })
    @Override
    public int hashCode() {
        int result = leftImage != null ? leftImage.hashCode() : 0;
        result = 31 * result + (leftImageAccessory != null ? leftImageAccessory.hashCode() : 0);
        result = 31 * result + (mainTitle != null ? mainTitle.hashCode() : 0);
        result = 31 * result + (mainSubtitle != null ? mainSubtitle.hashCode() : 0);
        result = 31 * result + (mainDescription != null ? mainDescription.hashCode() : 0);
        result = 31 * result + (rightTopLabel != null ? rightTopLabel.hashCode() : 0);
        result = 31 * result + (rightPrimaryLabel != null ? rightPrimaryLabel.hashCode() : 0);
        result = 31 * result + (rightSecondaryLabel != null ? rightSecondaryLabel.hashCode() : 0);
        result = 31 * result + (rightMiddleLabel != null ? rightMiddleLabel.hashCode() : 0);
        result = 31 * result + (rightBottomInfo != null ? rightBottomInfo.hashCode() : 0);
        result = 31 * result + (link != null ? link.hashCode() : 0);
        return result;
    }


    public boolean isValid() {
        return !TextUtils.isEmpty(leftImage)
            || !TextUtils.isEmpty(mainTitle)
            || !TextUtils.isEmpty(mainSubtitle)
            || !TextUtils.isEmpty(link);
    }
}
