package com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.view.flex_cover_carousel

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class FlexCoverCarouselComponentDecorator(
    private val margin: Int
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        with(outRect) {
            left = margin
            right = margin
        }
    }
}