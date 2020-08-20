package com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.view.carousel;

import android.graphics.Rect;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;

/* default */ public class CarouselDecorator extends RecyclerView.ItemDecoration {

    private final int marginLeft;
    private final int marginRight;

    public CarouselDecorator(final int marginLeft, final int marginRight) {
        this.marginLeft = marginLeft;
        this.marginRight = marginRight;
    }

    @Override
    public void getItemOffsets(@NonNull final Rect outRect, @NonNull final View view,
        @NonNull final RecyclerView parent, @NonNull final RecyclerView.State state) {
        final int childPosition = parent.getChildAdapterPosition(view);
        if (childPosition == 0) {
            outRect.left = marginLeft;
        }
        if (childPosition == state.getItemCount() - 1) {
            outRect.right = marginRight;
        }
    }
}