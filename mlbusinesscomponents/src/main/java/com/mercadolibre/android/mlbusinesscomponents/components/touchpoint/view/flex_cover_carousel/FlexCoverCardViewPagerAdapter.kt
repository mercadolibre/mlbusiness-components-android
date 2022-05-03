package com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.view.flex_cover_carousel

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.callback.OnClickCallback
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.view.flex_cover_carousel.flex_cover_card.FlexCoverCardInterfaceView
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.view.flex_cover_carousel.flex_cover_card.FlexCoverCardView
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.cover_carousel.model.cover_card.CoverCardInterfaceModel as CoverCardInterfaceModel1

class FlexCoverCardViewPagerAdapter internal constructor(private val context: Context) :
    PagerAdapter() {
    private var onClickCallback: OnClickCallback? = null
    val elementsList = mutableListOf<FlexCoverCardInterfaceView>()


    fun setElementsView(itemsView: List<CoverCardInterfaceModel1?>?) {
        if (itemsView?.size!! <= elementsList.size) {
            while (itemsView.size != elementsList.size) {
                elementsList.removeAt(elementsList.size - 1)
            }
        }
        addItemsInElementsView(itemsView as List<CoverCardInterfaceModel1>)
        notifyDataSetChanged()
    }

    fun setOnClickCallback(onClickCallback: OnClickCallback?) {
        this.onClickCallback = onClickCallback
    }

    private fun addItemsInElementsView(itemsView: List<CoverCardInterfaceModel1>) {
        var view: FlexCoverCardView
        var itemsViewIndex = 0
        for (model in itemsView) {
            if (itemsViewIndex < elementsList.size) {
                elementsList[itemsViewIndex].bind(model)
            } else {
                view = FlexCoverCardView(context)
                view.setOnClickCallback(onClickCallback)
                view.bind(model)
                elementsList.add(elementsList.size, view)
            }
            itemsViewIndex++
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