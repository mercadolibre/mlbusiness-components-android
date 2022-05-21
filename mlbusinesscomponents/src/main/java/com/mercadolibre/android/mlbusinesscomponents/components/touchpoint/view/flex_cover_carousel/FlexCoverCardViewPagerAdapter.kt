package com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.view.flex_cover_carousel

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mercadolibre.android.mlbusinesscomponents.R
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.flex_cover_carousel.FlexCoverCard

class FlexCoverCardViewPagerAdapter(
    var items: List<FlexCoverCard> = ArrayList()
) : RecyclerView.Adapter<FlexCoverCarouselViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FlexCoverCarouselViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.touchpoint_flex_cover_carousel_item, parent, false)

        return FlexCoverCarouselViewHolder(view)
    }

    override fun onBindViewHolder(holder: FlexCoverCarouselViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size


    /*
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
        for (model in itemsView) {
            val view = FlexCoverCardView(context)
            view.setOnClickCallback(onClickCallback)
            view.bind(model)
            elementsList.add(view)
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
    }*/
}