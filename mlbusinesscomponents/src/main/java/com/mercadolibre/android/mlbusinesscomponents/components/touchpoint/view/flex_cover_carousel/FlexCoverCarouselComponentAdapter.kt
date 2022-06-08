package com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.view.flex_cover_carousel

import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import androidx.recyclerview.widget.RecyclerView
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.callback.OnClickCallback
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.flex_cover_carousel.FlexCoverCard
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.tracking.MLBusinessTouchpointTracker
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.view.flex_cover_carousel.flex_cover_card.FlexCoverCardView

class FlexCoverCarouselComponentAdapter(
    var cards: List<FlexCoverCard> = ArrayList(),
    val onClickCallback: OnClickCallback?,
    val tracker: MLBusinessTouchpointTracker
) : RecyclerView.Adapter<FlexCoverCarouselComponentViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FlexCoverCarouselComponentViewHolder {
        val card = FlexCoverCardView(parent.context).apply {
            layoutParams = layoutParams ?: ViewGroup.LayoutParams(MATCH_PARENT, WRAP_CONTENT)
        }
        return FlexCoverCarouselComponentViewHolder(card)
    }

    override fun onBindViewHolder(holder: FlexCoverCarouselComponentViewHolder, position: Int) {
        holder.bind(cards[position], onClickCallback, tracker)
    }

    override fun getItemCount(): Int = cards.size

    fun updateData(cards: List<FlexCoverCard>) {
        this.cards = cards
        notifyDataSetChanged()
    }
}
