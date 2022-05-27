package com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.view.flex_cover_carousel

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.mercadolibre.android.mlbusinesscomponents.R
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

    override fun bind(model: FlexCoverCarouselResponse) {
        if (!isInitialized) {
            initialize()
        }
        presenter.bind(model)
    }

    override fun setCards(cards: List<FlexCoverCard>) {
        cardsAdapter?.let {
            cardsAdapter?.updateData(cards)
        } ?: let {
            cardsAdapter = FlexCoverCarouselComponentAdapter(cards)
            adapter = cardsAdapter
            val decorator = FlexCoverCarouselComponentDecorator(resources.getDimensionPixelSize(R.dimen.ui_050m))
            addItemDecoration(decorator)
            setPadding(
                resources.getDimensionPixelSize(R.dimen.ui_1m),
                0,
                resources.getDimensionPixelSize(R.dimen.touchpoint_flex_cover_carousel_default_margin),
                0)
        }
    }

    private fun initialize() {
        layoutManager = linearLayoutManager
        clipToPadding = false
        clipChildren = false
        snapHelper.attachToRecyclerView(this)
        addOnScrollListener(setOnScrollListener())
    }

    private fun setOnScrollListener(): OnScrollListener {
        return object : OnScrollListener() {

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                cardsAdapter?.let {
                    updatePadding(it.itemCount)
                }

            }
        }
    }

    private fun updatePadding(itemCount: Int) {
        val currentPosition = linearLayoutManager.findLastVisibleItemPosition()
        val isAtFirstPosition = currentPosition == 0
        val isAtLastPosition = currentPosition == itemCount - 1
        val normalPadding = resources.getDimensionPixelSize(R.dimen.ui_1m)
        val extraPadding = resources.getDimensionPixelSize(R.dimen.touchpoint_flex_cover_carousel_default_margin)

        val (leftPadding, rightPadding) = when {
            isAtFirstPosition -> normalPadding to extraPadding
            isAtLastPosition -> extraPadding to normalPadding
            else -> normalPadding to extraPadding
        }

        setPadding(leftPadding, 0, rightPadding, 0)
    }

}
