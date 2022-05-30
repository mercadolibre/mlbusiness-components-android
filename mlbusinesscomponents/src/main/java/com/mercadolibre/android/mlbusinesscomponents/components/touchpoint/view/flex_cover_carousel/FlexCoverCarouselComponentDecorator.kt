package com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.view.flex_cover_carousel

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class FlexCoverCarouselComponentDecorator(
    private val marginBetweenItems: Int
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {

        when (parent.getChildAdapterPosition(view)) {
            0 -> outRect.right = marginBetweenItems / 2
            parent.adapter?.itemCount?.minus(1) -> outRect.left = marginBetweenItems / 2
            else -> {
                outRect.left = marginBetweenItems / 2
                outRect.right = marginBetweenItems / 2
            }
        }
    }
}