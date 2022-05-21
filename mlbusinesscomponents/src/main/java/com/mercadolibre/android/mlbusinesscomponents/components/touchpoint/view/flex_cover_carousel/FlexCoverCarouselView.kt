package com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.view.flex_cover_carousel

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.viewpager2.widget.MarginPageTransformer

import androidx.viewpager2.widget.ViewPager2
import com.mercadolibre.android.mlbusinesscomponents.R
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.callback.OnClickCallback
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.flex_cover_carousel.FlexCoverCard
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.flex_cover_carousel.FlexCoverCarouselResponse
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.view.AbstractTouchpointChildView
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.view.carousel.card.TrackListener
import com.mercadolibre.android.mlbusinesscomponents.components.utils.ScaleUtils
import java.lang.Math.abs

class FlexCoverCarouselView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AbstractTouchpointChildView<FlexCoverCarouselResponse>(context, attrs, defStyleAttr),
    FlexCoverCarouselViewInterface {

      private val presenter: FlexCoverCarouselPresenter
      private var viewPager: ViewPager2
      private val adapter: FlexCoverCardViewPagerAdapter

    init {
        inflate(context, R.layout.touchpoint_flex_cover_carousel_view, this)
        viewPager = findViewById(R.id.view_pager)

        presenter = FlexCoverCarouselPresenter()
        adapter = FlexCoverCardViewPagerAdapter()
    }

    override fun setItemsList(items: List<FlexCoverCard>) {
        viewPager.adapter = FlexCoverCardViewPagerAdapter(items)
        viewPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        //viewPager.setPageTransformer(MarginPageTransformer(getInsetInPx(8)));
        viewPager.apply {
            offscreenPageLimit = 1
        }
    }

    override fun bind(model: FlexCoverCarouselResponse?) {
         model?.let {
             presenter.mapResponse(it, this)
         }
    }
    override fun getStaticHeight(): Int {
        return 0
    }

    override fun print() {
        //no op..
    }

    override fun setVisibilityGone() {
        visibility = GONE
    }

    private fun getInsetInPx(inset: Int): Int {
        return ScaleUtils.getPxFromDp(context, inset.toFloat()).toInt()
    }

    override fun setViewPagerHeight(maxHeight: Int) {
        val params: ViewGroup.LayoutParams = viewPager.layoutParams
        params.height = maxHeight + resources.getDimensionPixelSize(R.dimen.ui_3m)

    }

    override fun setOnClickCallback(onClickCallback: OnClickCallback?) {
        this.onClickCallback = onClickCallback
       // this.onClickCallback?.let { viewPagerAdapter.setOnClickCallback(it) }
    }
}