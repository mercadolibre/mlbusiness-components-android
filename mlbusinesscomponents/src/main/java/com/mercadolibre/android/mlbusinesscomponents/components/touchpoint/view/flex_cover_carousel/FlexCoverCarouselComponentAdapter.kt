package com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.view.flex_cover_carousel

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mercadolibre.android.mlbusinesscomponents.R
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.flex_cover_carousel.FlexCoverCard

class FlexCoverCarouselComponentAdapter(
    var cards: List<FlexCoverCard> = ArrayList()
) : RecyclerView.Adapter<FlexCoverCarouselComponentViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FlexCoverCarouselComponentViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.touchpoint_flex_cover_carousel_item, parent, false)

        return FlexCoverCarouselComponentViewHolder(view)
    }

    override fun onBindViewHolder(holder: FlexCoverCarouselComponentViewHolder, position: Int) {
        holder.bind(cards[position])
    }

    override fun getItemCount(): Int = cards.size

    fun updateData(cards: List<FlexCoverCard>) {
        this.cards = cards
        notifyDataSetChanged()
    }
}
