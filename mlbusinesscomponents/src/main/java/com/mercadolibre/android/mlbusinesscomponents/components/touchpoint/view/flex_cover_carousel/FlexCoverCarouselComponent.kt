package com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.view.flex_cover_carousel

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.mercadolibre.android.mlbusinesscomponents.R
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.callback.OnClickCallback
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.AdditionalEdgeInsets
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.flex_cover_carousel.FlexCoverCard
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.flex_cover_carousel.FlexCoverCarouselResponse
import com.mercadolibre.android.mlbusinesscomponents.components.utils.ScaleUtils

class FlexCoverCarouselComponent @JvmOverloads constructor(
    context: Context,
    attr: AttributeSet? = null,
    defStyle: Int = 0
) : RecyclerView(context, attr, defStyle), FlexCoverCarouselComponentInterface {

    private val verticalPadding = resources.getDimensionPixelSize(R.dimen.ui_1_5m)
    private val linearLayoutManager = LinearLayoutManager(context, HORIZONTAL, false)
    private val presenter = FlexCoverCarouselComponentPresenter(this)
    private var cardsAdapter: FlexCoverCarouselComponentAdapter? = null
    private val snapHelper = PagerSnapHelper()
    private var isInitialized = false
    private var wasAtFirstElement: Boolean? = null
    private var wasAtLastElement: Boolean? = null
    private var normalPadding = 0
    private var biggerPadding = 0

    override fun bind(model: FlexCoverCarouselResponse, onClickCallback: OnClickCallback?) {
        if (!isInitialized) {
            initialize()
        }
        presenter.bind(model, onClickCallback)
    }

    override fun notifyPadding(additionalEdgeInsets: AdditionalEdgeInsets) {
        normalPadding = ScaleUtils.getPxFromDp(context, additionalEdgeInsets.left.toFloat()).toInt()
        biggerPadding = ScaleUtils.getPxFromDp(context, additionalEdgeInsets.right.toFloat()).toInt()
    }

    override fun setCards(cards: List<FlexCoverCard>, onClickCallback: OnClickCallback?) {
        cardsAdapter?.let {
            it.updateData(cards)
        } ?: let {
            cardsAdapter = FlexCoverCarouselComponentAdapter(cards, onClickCallback)
            adapter = cardsAdapter
            val decorator =
                FlexCoverCarouselComponentDecorator(resources.getDimensionPixelSize(R.dimen.ui_1m))
            addItemDecoration(decorator)
        }
        updatePadding(cards.size, wasAtFirstElement == null || isAtFirstElement(0), isAtLastElement(0))
    }

    private fun initialize() {
        layoutManager = linearLayoutManager
        clipToPadding = false
        clipChildren = false
        snapHelper.attachToRecyclerView(this)
        addOnScrollListener(createScrollListener())
    }

    private fun createScrollListener() = object : OnScrollListener() {

        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {}

        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            updatePadding(cardsAdapter?.itemCount ?: 0, isAtFirstElement(dx), isAtLastElement(dx))
        }
    }

    private fun updatePadding(itemCount: Int, isAtFirstElement: Boolean, isAtLastElement: Boolean) {
        if (isAtFirstElement == wasAtFirstElement || isAtLastElement == wasAtLastElement) return
        val (leftPadding, rightPadding) = when {
            itemCount <= 1 -> normalPadding  to normalPadding
            isAtFirstElement -> normalPadding to biggerPadding
            isAtLastElement -> biggerPadding to normalPadding
            else -> normalPadding to biggerPadding
        }
        setPadding(leftPadding, verticalPadding, rightPadding, verticalPadding)
        wasAtFirstElement = isAtFirstElement
        wasAtLastElement = isAtLastElement
    }

    private fun isAtFirstElement(scrollingTo: Int) = with(linearLayoutManager) {
        findFirstCompletelyVisibleItemPosition() == 0 ||
                (findFirstVisibleItemPosition() == 0 && findLastVisibleItemPosition() == 1 && scrollingTo < 0)
    }

    private fun isAtLastElement(scrollingTo: Int) = with(linearLayoutManager) {
        findLastCompletelyVisibleItemPosition() == getLastItemIndex() ||
                (findLastVisibleItemPosition() == getLastItemIndex() && findFirstVisibleItemPosition() == getLastItemIndex() - 1 && scrollingTo > 0)
    }

    private fun getLastItemIndex() = linearLayoutManager.itemCount - 1
}
