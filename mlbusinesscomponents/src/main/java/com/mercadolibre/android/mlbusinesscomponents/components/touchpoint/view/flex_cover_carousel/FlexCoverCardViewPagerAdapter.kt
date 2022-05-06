package com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.view.flex_cover_carousel

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.callback.OnClickCallback
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.flex_cover_carousel.FlexCoverCard
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.view.flex_cover_carousel.flex_cover_card.FlexCoverCardInterfaceView
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.view.flex_cover_carousel.flex_cover_card.FlexCoverCardView

class FlexCoverCardViewPagerAdapter internal constructor(private val context: Context) :
    PagerAdapter() {
    private lateinit var onClickCallback: OnClickCallback
    val elementsList = mutableListOf<FlexCoverCardInterfaceView>()


    fun setElementsView(itemsView: List<FlexCoverCard>) {
        elementsList.clear()
        addItemsInElementsView(itemsView)
        notifyDataSetChanged()
    }

    fun setOnClickCallback(onClickCallback: OnClickCallback) {
        this.onClickCallback = onClickCallback
    }

    private fun addItemsInElementsView(itemsView: List<FlexCoverCard>) {
        var view: FlexCoverCardView
        itemsView.let {
            for (model in itemsView) {
                view = FlexCoverCardView(context)
                view.setOnClickCallback(onClickCallback)
                view.bind(model)
                elementsList.add(view)
            }
        }
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val virtualPosition = position % elementsList.size
        container.addView(elementsList[virtualPosition].getView())
        return elementsList[virtualPosition]
    }

    override fun getCount(): Int {
        return elementsList.size
    }

    override fun destroyItem(container: ViewGroup, position: Int, view: Any) {
        container.removeView(view as View)
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }
}