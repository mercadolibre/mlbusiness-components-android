package com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.view.flex_cover_carousel.flex_cover_card

import androidx.cardview.widget.CardView
import com.mercadolibre.android.mlbusinesscomponents.components.rowpill.PillInterface
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.callback.OnClickCallback
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.cover_carousel.model.cover_card.CoverCardInterfaceModel
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.flex_cover_carousel.FlexCoverCard

interface FlexCoverCardInterfaceView {

    fun bind(model: FlexCoverCard)
    fun bind(model: FlexCoverCard, size: Int)
    fun setCoverImage(cover: String)
    fun setOnClick(link: String)
    fun dismissClickable()
    fun setOnClickCallback(onClickCallback: OnClickCallback)
    fun getCoverCardHeight(): Int
    fun getView(): CardView
    fun changeBackgroundColor(backgroundColor: String)
    fun showTitle(title: String)
    fun showDescription(description: String)
    fun showSubtitle(subtitle: String)
    fun showPill(pill: PillInterface, view: FlexCoverCardInterfaceView)
}