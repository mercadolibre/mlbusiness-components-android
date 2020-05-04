package com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.grid;

import android.support.annotation.Nullable;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.tracking.TouchpointTrackeable;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.tracking.print.TouchpointTracking;

public class GridItem implements TouchpointTrackeable {

    private final String image;
    private final String title;
    private final String subtitle;
    private final String link;
    @Nullable private final TouchpointTracking tracking;

    /**
     * Constructor
     *
     * @param image An image
     * @param title A title
     * @param subtitle A subtitle
     * @param link The desired link to launch
     * @param tracking A {@link TouchpointTracking}
     */
    public GridItem(final String image, final String title, final String subtitle, final String link,
        @Nullable final TouchpointTracking tracking) {
        this.image = image;
        this.title = title;
        this.subtitle = subtitle;
        this.link = link;
        this.tracking = tracking;
    }

    public String getImage() {
        return image;
    }

    public String getTitle() {
        return title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public String getLink() {
        return link;
    }

    @Override
    @Nullable
    public TouchpointTracking getTracking() {
        return tracking;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || !getClass().equals(o.getClass())) {
            return false;
        }

        final GridItem gridItem = (GridItem) o;

        if (getImage() != null ? !getImage().equals(gridItem.getImage()) : gridItem.getImage() != null) {
            return false;
        }
        if (getTitle() != null ? !getTitle().equals(gridItem.getTitle()) : gridItem.getTitle() != null) {
            return false;
        }
        if (getSubtitle() != null ? !getSubtitle().equals(gridItem.getSubtitle()) : gridItem.getSubtitle() != null) {
            return false;
        }
        if (getLink() != null ? !getLink().equals(gridItem.getLink()) : gridItem.getLink() != null) {
            return false;
        }
        return getTracking() != null ? getTracking().equals(gridItem.getTracking()) : gridItem.getTracking() == null;
    }

    @Override
    public int hashCode() {
        int result = getImage() != null ? getImage().hashCode() : 0;
        result = 31 * result + (getTitle() != null ? getTitle().hashCode() : 0);
        result = 31 * result + (getSubtitle() != null ? getSubtitle().hashCode() : 0);
        result = 31 * result + (getLink() != null ? getLink().hashCode() : 0);
        result = 31 * result + (getTracking() != null ? getTracking().hashCode() : 0);
        return result;
    }
}
