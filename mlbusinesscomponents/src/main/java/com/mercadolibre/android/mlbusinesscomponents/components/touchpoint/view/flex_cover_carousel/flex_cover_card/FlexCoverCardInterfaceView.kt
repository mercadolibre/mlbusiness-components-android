package com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.view.flex_cover_carousel.flex_cover_card

import androidx.cardview.widget.CardView
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.callback.OnClickCallback
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.flex_cover_carousel.FlexCoverCard
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.flex_cover_carousel.Logo
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.flex_cover_carousel.Text
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.tracking.print.TouchpointTracking
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.view.flex_cover_carousel.pill_touchpoint.PillInterface

interface FlexCoverCardInterfaceView {

    fun bind(model: FlexCoverCard)
    fun bind(model: FlexCoverCard, size: Int)
    fun setCoverImage(cover: String)
    fun setOnClick(link: String)
    fun dismissClickable()
    fun setOnClickCallback(onClickCallback: OnClickCallback)
    fun getView(): CardView
    fun changeBackgroundColor(backgroundColor: String)
    fun showTitle(title: Text)
    fun showDescription(description: Text)
    fun showSubtitle(subtitle: Text)
    fun showPill(pill: PillInterface, view: FlexCoverCardInterfaceView)
    fun showLogo(logos: List<Logo>, view: FlexCoverCardInterfaceView)
    fun setTracking(tracking: TouchpointTracking?)
}