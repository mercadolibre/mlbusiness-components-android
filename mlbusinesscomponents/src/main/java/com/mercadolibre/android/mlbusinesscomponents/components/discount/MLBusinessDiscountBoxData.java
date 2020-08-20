package com.mercadolibre.android.mlbusinesscomponents.components.discount;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.mercadolibre.android.mlbusinesscomponents.common.MLBusinessSingleItem;
import java.util.List;

public interface MLBusinessDiscountBoxData {

    @Nullable
    String getTitle();
    @Nullable
    String getSubtitle();
    @NonNull
    List<MLBusinessSingleItem> getItems();

    @Nullable
    default MLBusinessDiscountTracker getTracker() {
        return null;
    }
}
