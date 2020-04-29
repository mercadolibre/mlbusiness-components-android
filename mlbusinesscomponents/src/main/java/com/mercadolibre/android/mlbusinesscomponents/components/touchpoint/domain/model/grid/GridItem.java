package com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.grid;

import android.support.annotation.Nullable;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.tracking.print.TouchpointTracking;

public class GridItem {

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

    @Nullable
    public TouchpointTracking getTracking() {
        return tracking;
    }
}
