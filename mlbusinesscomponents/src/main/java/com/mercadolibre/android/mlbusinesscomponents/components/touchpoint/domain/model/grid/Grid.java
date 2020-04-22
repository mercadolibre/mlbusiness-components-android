package com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.grid;

import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.TouchpointContent;
import java.io.Serializable;
import java.util.List;

public class Grid implements TouchpointContent, Serializable {

    private static final long serialVersionUID = -8525135238159542478L;

    private final String title;
    private final String subtitle;
    private final List<GridItem> items;

    /**
     * Constructor
     *
     * @param title A title
     * @param subtitle A subtitle
     * @param items The list of {@link GridItem} to be showed
     */
    public Grid(final String title, final String subtitle, final List<GridItem> items) {
        this.title = title;
        this.subtitle = subtitle;
        this.items = items;
    }

    public String getTitle() {
        return title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public List<GridItem> getItems() {
        return items;
    }
}
