package com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.view.flex_cover_carousel

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.mercadolibre.android.mlbusinesscomponents.R
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.flex_cover_carousel.FlexCoverCard
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.view.flex_cover_carousel.flex_cover_card.FlexCoverCardView

class FlexCoverCarouselViewHolder(container: View) : RecyclerView.ViewHolder(container) {

    private val view: FlexCoverCardView = container.findViewById(R.id.flex_cover_carousel_item)
    private var model: FlexCoverCard? = null

    fun bind(item: FlexCoverCard?) {
        item?.let {
            this.model = item
            view.bind(item)
        }
    }
}