package com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.view.grid;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.grid.Grid;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.grid.GridItem;
import java.util.List;

/* default */ class GridPresenter {

    private static final int MAX_ITEM_PER_ROW = 3;

    private int rowCount;

    /* default */ void bind(@NonNull final Grid model, @NonNull final GridView view) {
        setGrid(model.getItems(), view);
    }

    private void setGrid(final List<GridItem> items, final GridView view) {
        final List<GridItem> filterItems = filterItems(items);
        setRawCount(filterItems.size(), view);
        if (rowCount == 1) {
            view.showRowWithItems(items);
        } else {
            view.showRowWithItems(filterItems.subList(0, 3));
            view.showRowWithItems(filterItems.subList(3, filterItems.size()));
        }
    }

    private List<GridItem> filterItems(final List<GridItem> items) {
        if (items.size() > 6) {
            return items.subList(0, 6);
        }
        return items;
    }

    private void setRawCount(final int itemCount, final GridView view) {
        if (itemCount > MAX_ITEM_PER_ROW) {
            rowCount = 2;
        } else {
            rowCount = 1;
        }
        view.setRawCount(rowCount);
    }
}
