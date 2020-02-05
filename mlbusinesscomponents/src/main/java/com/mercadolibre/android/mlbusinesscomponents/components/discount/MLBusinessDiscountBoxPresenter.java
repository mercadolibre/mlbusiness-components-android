package com.mercadolibre.android.mlbusinesscomponents.components.discount;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.mercadolibre.android.mlbusinesscomponents.common.MLBusinessSingleItem;
import com.mercadolibre.android.mlbusinesscomponents.components.utils.StringUtils;
import java.util.List;

/* default */ class MLBusinessDiscountBoxPresenter {

    private static final int MAX_ITEM_PER_ROW = 3;

    private int rowCount;

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
        final List<MLBusinessSingleItem> validItems = getItems(items);
        setRawCount(validItems.size(), view);
        if (rowCount == 1) {
            view.showRowWithItems(items, listener, 0);
        } else {
            view.showRowWithItems(validItems.subList(0, 3), listener, 0);
            view.showRowWithItems(validItems.subList(3, validItems.size()), listener, 3);
        }
    }

    private List<MLBusinessSingleItem> getItems(final List<MLBusinessSingleItem> items) {
        if (items.size() > 6) {
            return items.subList(0, 6);
        }
        return items;
    }

    private void setRawCount(final int itemCount, final MLBusinessDiscountBoxView view) {
        if (itemCount > MAX_ITEM_PER_ROW) {
            rowCount = 2;
        } else {
            rowCount = 1;
        }
        view.setRawCount(rowCount);
    }
}
