package com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.grid;

import android.support.annotation.Nullable;
import java.util.Map;

public class GridItem {

    private final String image;
    private final String title;
    private final String subtitle;
    private final String link;
    @Nullable private final Map<String, Object> eventData;

    /**
     * Constructor
     *
     * @param image An image
     * @param title A title
     * @param subtitle A subtitle
     * @param link The desired link to launch
     * @param eventData A event data to track
     */
    public GridItem(final String image, final String title, final String subtitle, final String link,
        @Nullable final Map<String, Object> eventData) {
        this.image = image;
        this.title = title;
        this.subtitle = subtitle;
        this.link = link;
        this.eventData = eventData;
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
    public Map<String, Object> getEventData() {
        return eventData;
    }
}
