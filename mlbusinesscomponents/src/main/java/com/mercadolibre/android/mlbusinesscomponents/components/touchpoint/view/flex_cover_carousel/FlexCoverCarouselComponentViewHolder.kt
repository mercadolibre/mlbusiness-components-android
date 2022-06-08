package com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.view.flex_cover_carousel

import androidx.recyclerview.widget.RecyclerView
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.callback.OnClickCallback
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.flex_cover_carousel.FlexCoverCard
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.tracking.MLBusinessTouchpointTracker
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.view.flex_cover_carousel.flex_cover_card.FlexCoverCardView

class FlexCoverCarouselComponentViewHolder(val view: FlexCoverCardView) : RecyclerView.ViewHolder(view) {

    private var model: FlexCoverCard? = null

    fun bind(item: FlexCoverCard?, onClickCallback: OnClickCallback?, tracker: MLBusinessTouchpointTracker?) {
        item?.let {
            this.model = item
            view.bind(item)
            view.setTracker(tracker)
            onClickCallback?.let { onClick -> view.setOnClickCallback(onClick) }
        }
    }
}