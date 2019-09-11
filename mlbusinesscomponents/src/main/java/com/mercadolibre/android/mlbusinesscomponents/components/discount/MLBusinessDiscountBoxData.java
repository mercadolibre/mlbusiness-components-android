package com.mercadolibre.android.mlbusinesscomponents.components.discount;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.mercadolibre.android.mlbusinesscomponents.commons.SingleItem;
import java.util.List;

public interface MLBusinessDiscountBoxData {

    @Nullable
    String getTitle();
    @Nullable
    String getSubtitle();
    @NonNull
    List<SingleItem> getItems();
}
