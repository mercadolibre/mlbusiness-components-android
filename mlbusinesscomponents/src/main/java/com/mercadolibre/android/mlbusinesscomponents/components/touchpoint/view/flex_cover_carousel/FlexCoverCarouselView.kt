package com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.view.flex_cover_carousel

import android.content.Context
import android.util.AttributeSet
import com.mercadolibre.android.mlbusinesscomponents.R
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.AdditionalEdgeInsets
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.flex_cover_carousel.FlexCoverCarouselResponse
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.view.AbstractTouchpointChildView

class FlexCoverCarouselView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AbstractTouchpointChildView<FlexCoverCarouselResponse>(context, attrs, defStyleAttr){

    private var component: FlexCoverCarouselComponent

    init {
        inflate(context, R.layout.touchpoint_flex_cover_carousel_view, this)
        component = findViewById(R.id.touchpoint_flex_cover_carousel_card_component)
    }

    override fun bind(model: FlexCoverCarouselResponse?) {
        additionalInsets?.let { component.notifyPadding(it) }
        model?.let {
            component.bind(model, onClickCallback)
        }
    }

    override fun getStaticHeight(): Int {
        return 0
    }

    override fun print() {
        //no op..
    }
}
