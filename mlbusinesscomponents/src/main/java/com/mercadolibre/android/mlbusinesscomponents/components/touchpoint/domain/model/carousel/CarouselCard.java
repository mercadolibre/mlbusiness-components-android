package com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.carousel;

import androidx.annotation.Keep;
import androidx.annotation.Nullable;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.tracking.TouchpointTrackeable;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.tracking.print.TouchpointTracking;
import java.io.Serializable;

@Keep
public class CarouselCard implements TouchpointTrackeable, Serializable {

    private static final long serialVersionUID = 7278154289419469849L;

    private final String image;
    private final String topLabel;
    private final String mainLabel;
    private final String rightLabel;
    private final CarouselPill pill;
    private final String title;
    private final String subtitle;
    private final String link;
    private final String textColor;
    private final String backgroundColor;
    private final CarouselTextFormat titleFormat;
    private final CarouselTextFormat subtitleFormat;
    private final LogoImageFormat imageFormat;
    @Nullable private final TouchpointTracking tracking;
    @Nullable private final String type;

    /**
     * Constructor
     *
     * @param image The logo to show
     * @param pill The pill to show
     * @param title The title to show
     * @param subtitle The subtitle to show
     * @param rightLabel The right title
     * @param mainLabel The main label
     * @param topLabel The bottom label
     * @param link The link to launch
     * @param textColor The text color
     * @param backgroundColor the card background color
     * @param titleFormat the title format
     * @param subtitleFormat the subtitle format
     * @param imageFormat the image format
     * @param type the type of Card
     * @param tracking A {@link TouchpointTracking}
     */
    public CarouselCard(final String image, final CarouselPill pill, final String title,
        final String subtitle,
        final String rightLabel, final String mainLabel, final String topLabel, final String link,
        final String textColor, final String backgroundColor,
        final CarouselTextFormat titleFormat,
        final CarouselTextFormat subtitleFormat,
        final LogoImageFormat imageFormat,
        @Nullable final TouchpointTracking tracking,
        @Nullable final String type) {
        this.image = image;
        this.pill = pill;
        this.title = title;
        this.subtitle = subtitle;
        this.rightLabel = rightLabel;
        this.mainLabel = mainLabel;
        this.topLabel = topLabel;
        this.link = link;
        this.textColor = textColor;
        this.backgroundColor = backgroundColor;
        this.titleFormat = titleFormat;
        this.subtitleFormat = subtitleFormat;
        this.imageFormat = imageFormat;
        this.tracking = tracking;
        this.type = type;
    }

    public String getImage() {
        return image;
    }

    public CarouselPill getPill() {
        return pill;
    }

    public String getTitle() {
        return title;
    }

    public String getRightLabel() {
        return rightLabel;
    }

    public String getMainLabel() {
        return mainLabel;
    }

    public String getTopLabel() {
        return topLabel;
    }

    public String getLink() {
        return link;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public String getTextColor() {
        return textColor;
    }

    public String getBackgroundColor() {
        return backgroundColor;
    }

    public CarouselTextFormat getTitleFormat() {
        return titleFormat;
    }

    public CarouselTextFormat getSubtitleFormat() {
        return subtitleFormat;
    }

    public LogoImageFormat getImageFormat() {
        return imageFormat;
    }

    @Override
    @Nullable
    public TouchpointTracking getTracking() {
        return tracking;
    }

    @Nullable
    public  String getType() {
        return type;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || !getClass().equals(o.getClass())) {
            return false;
        }

        final CarouselCard that = (CarouselCard) o;

        if (getImage() != null ? !getImage().equals(that.getImage()) : that.getImage() != null) {
            return false;
        }
        if (getTopLabel() != null ? !getTopLabel().equals(that.getTopLabel()) : that.getTopLabel() != null) {
            return false;
        }
        if (getMainLabel() != null ? !getMainLabel().equals(that.getMainLabel()) : that.getMainLabel() != null) {
            return false;
        }
        if (getRightLabel() != null ? !getRightLabel().equals(that.getRightLabel()) : that.getRightLabel() != null) {
            return false;
        }
        if (getPill() != null ? !getPill().equals(that.getPill()) : that.getPill() != null) {
            return false;
        }
        if (getTitle() != null ? !getTitle().equals(that.getTitle()) : that.getTitle() != null) {
            return false;
        }
        if (getSubtitle() != null ? !getSubtitle().equals(that.getSubtitle()) : that.getSubtitle() != null) {
            return false;
        }
        if (getLink() != null ? !getLink().equals(that.getLink()) : that.getLink() != null) {
            return false;
        }
        if (getTextColor() != null ? !getTextColor().equals(that.getTextColor()) : that.getTextColor() != null) {
            return false;
        }
        return getBackgroundColor() != null ? getBackgroundColor().equals(that.getBackgroundColor()) : that.getBackgroundColor() == null;
    }

    @Override
    public int hashCode() {
        int result = getImage() != null ? getImage().hashCode() : 0;
        result = 31 * result + (getTopLabel() != null ? getTopLabel().hashCode() : 0);
        result = 31 * result + (getMainLabel() != null ? getMainLabel().hashCode() : 0);
        result = 31 * result + (getRightLabel() != null ? getRightLabel().hashCode() : 0);
        result = 31 * result + (getPill() != null ? getPill().hashCode() : 0);
        result = 31 * result + (getTitle() != null ? getTitle().hashCode() : 0);
        result = 31 * result + (getSubtitle() != null ? getSubtitle().hashCode() : 0);
        result = 31 * result + (getLink() != null ? getLink().hashCode() : 0);
        result = 31 * result + (getTextColor() != null ? getTextColor().hashCode() : 0);
        result = 31 * result + (getBackgroundColor() != null ? getBackgroundColor().hashCode() : 0);
        return result;
    }

    public double getHeight() {
        boolean hasTopLabel = false, hasMainLabel = false, hasTitle = false, hasSubtitle = false;

        if (topLabel != null && !topLabel.isEmpty()) {
            hasTopLabel = true;
        }
        if (mainLabel != null && !mainLabel.isEmpty()) {
            hasMainLabel = true;
        }
        if (title != null && !title.isEmpty()) {
            hasTitle = true;
        }
        if (subtitle != null && !subtitle.isEmpty()) {
            hasSubtitle = true;
        }

        double spaceToMainLabel = 100, topLabelHeight = 14, mainLabelHeight = 28, titleHeight = 23, subtitleHeight = 13, spaceToBottom = 13;

        double maxItemHeight = spaceToMainLabel
            + (hasTopLabel ? topLabelHeight : 0.0)
            + (hasMainLabel ? mainLabelHeight : 0.0)
            + (hasTitle ? titleHeight : 0.0)
            + (hasSubtitle ? subtitleHeight : 0.0)
            + spaceToBottom;

        return maxItemHeight;
    }
}
