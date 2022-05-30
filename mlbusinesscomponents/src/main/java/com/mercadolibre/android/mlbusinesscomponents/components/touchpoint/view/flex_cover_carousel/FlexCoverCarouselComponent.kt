package com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.view.flex_cover_carousel

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.mercadolibre.android.mlbusinesscomponents.R
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.callback.OnClickCallback
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.flex_cover_carousel.FlexCoverCard
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.flex_cover_carousel.FlexCoverCarouselResponse

class FlexCoverCarouselComponent(
    context: Context,
    attr: AttributeSet?
) : RecyclerView(context, attr), FlexCoverCarouselComponentInterface {

    private val linearLayoutManager = LinearLayoutManager(context, HORIZONTAL, false)
    private val presenter = FlexCoverCarouselComponentPresenter(this)
    private var cardsAdapter: FlexCoverCarouselComponentAdapter? = null
    private val snapHelper = PagerSnapHelper()
    private var isInitialized = false

    override fun bind(model: FlexCoverCarouselResponse, onClickCallback: OnClickCallback?) {
        if (!isInitialized) {
            initialize()
        }
        presenter.bind(model, onClickCallback)
    }

    override fun setCards(cards: List<FlexCoverCard>, onClickCallback: OnClickCallback?) {
        cardsAdapter?.let {
            cardsAdapter?.updateData(cards)
        } ?: let {
            cardsAdapter = FlexCoverCarouselComponentAdapter(cards, onClickCallback)
            adapter = cardsAdapter
            val decorator = FlexCoverCarouselComponentDecorator(resources.getDimensionPixelSize(R.dimen.ui_050m))
            addItemDecoration(decorator)
            setPadding(
                resources.getDimensionPixelSize(R.dimen.ui_1m),
                0,
                resources.getDimensionPixelSize(R.dimen.ui_1m),
                0)
        }
    }

    private fun initialize() {
        layoutManager = linearLayoutManager
        clipToPadding = false
        clipChildren = false
        snapHelper.attachToRecyclerView(this)
    }

}
