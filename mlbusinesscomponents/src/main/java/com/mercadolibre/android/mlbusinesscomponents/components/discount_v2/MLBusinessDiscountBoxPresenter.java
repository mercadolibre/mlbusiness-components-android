package com.mercadolibre.android.mlbusinesscomponents.components.discount_v2;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.mercadolibre.android.mlbusinesscomponents.common.MLBusinessSingleItem;
import com.mercadolibre.android.mlbusinesscomponents.components.utils.StringUtils;
import java.util.List;

/* default */ class MLBusinessDiscountBoxPresenter {

    private static final int MAX_ITEM_PER_ROW = 3;

    private int rowCount = 1;

    /* default */ void bind(@NonNull final MLBusinessDiscountBoxData model, @Nullable final OnClickDiscountBox listener,
        @NonNull final MLBusinessDiscountBoxView view) {
        setTitle(model.getTitle(), view);
        setSubtitle(model.getSubtitle(), view);
        setBox(model.getItems(), listener, view);
    }

    private void setTitle(@Nullable final String title, final MLBusinessDiscountBoxView view) {
        if (StringUtils.isValidString(title)) {
            view.showTitle(title);
            return;
        }
        view.hideTitle();
    }

    private void setSubtitle(@Nullable final String subtitle, final MLBusinessDiscountBoxView view) {
        if (StringUtils.isValidString(subtitle)) {
            view.showSubtitle(subtitle);
            return;
        }
        view.hideSubtitle();
    }

    private void setBox(final List<MLBusinessSingleItem> items, @Nullable final OnClickDiscountBox listener,
        final MLBusinessDiscountBoxView view) {
        setColumns(items.size(), view);
        if (rowCount == 1) {
            view.showRowWithItems(items, listener, 0);
            return;
        }
        view.showRowWithItems(items.subList(0, 2), listener, 0);
        view.showRowWithItems(items.subList(3, items.size() - 1), listener, 3);
    }

    private void setColumns(final int itemCount, final MLBusinessDiscountBoxView view) {
        if (itemCount > MAX_ITEM_PER_ROW) {
            rowCount = 2;
        }
        view.setColumnsCount(rowCount);
    }
}
