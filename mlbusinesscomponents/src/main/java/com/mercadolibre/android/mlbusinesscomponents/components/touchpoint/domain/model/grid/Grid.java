package com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.grid;

import android.support.annotation.Keep;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.TouchpointContent;
import java.io.Serializable;
import java.util.List;

@Keep
public class Grid implements TouchpointContent, Serializable {

    private static final long serialVersionUID = -8525135238159542478L;

    private final List<GridItem> items;

    /**
     * Constructor
     *
     * @param items The list of {@link GridItem} to be showed
     */
    public Grid(final List<GridItem> items) {
        this.items = items;
    }

    public List<GridItem> getItems() {
        return items;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || !getClass().equals(o.getClass())) {
            return false;
        }

        final Grid grid = (Grid) o;

        return getItems() != null ? getItems().equals(grid.getItems()) : grid.getItems() == null;
    }

    @Override
    public int hashCode() {
        return getItems() != null ? getItems().hashCode() : 0;
    }
}
