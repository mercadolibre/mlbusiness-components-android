package com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.view.flex_cover_carousel

import android.content.Context
import android.util.AttributeSet
import androidx.viewpager.widget.ViewPager
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.mercadolibre.android.mlbusinesscomponents.R
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.callback.OnClickCallback
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.AdditionalEdgeInsets
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.flex_cover_carousel.FlexCoverCard
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.flex_cover_carousel.FlexCoverCarouselResponse
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.tracking.TouchpointTrackeable
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.view.AbstractTouchpointChildView
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.view.carousel.card.TrackListener
import com.mercadolibre.android.mlbusinesscomponents.components.utils.ScaleUtils
import com.mercadolibre.android.mlbusinesscomponents.components.utils.TrackingUtils
import kotlin.math.roundToInt

class FlexCoverCarouselView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AbstractTouchpointChildView<FlexCoverCarouselResponse>(context, attrs, defStyleAttr),
    FlexCoverCarouselViewInterface {

    private val presenter: FlexCoverCarouselPresenter
    private val viewPager: FlexCoverCarouselViewPager
    private val viewPagerAdapter: FlexCoverCardViewPagerAdapter

    private var trackListener: TrackListener? = null

    init {
        inflate(context, R.layout.touchpoint_flex_cover_carousel_view, this)
        viewPager = findViewById(R.id.view_pager)

        presenter = FlexCoverCarouselPresenter()
        viewPagerAdapter = FlexCoverCardViewPagerAdapter(getContext())
        initViewPager()
    }

    private fun initViewPager() {
        setCarouselPadding(viewPager.currentItem)
        viewPager.adapter = viewPagerAdapter
        viewPager.clipToPadding = false
        viewPager.pageMargin = resources.getDimensionPixelOffset(R.dimen.ui_1m)
        viewPager.addOnPageChangeListener(object : OnPageChangeListener {
            override fun onPageScrolled(i: Int, v: Float, i1: Int) {
                //no op..
            }

            override fun onPageSelected(position: Int) {
                //no op..
                setCarouselPadding(position)
                trackListener?.print()
            }

            override fun onPageScrollStateChanged(state: Int) {
                //no op..
            }
        })
    }

    override fun bind(model: FlexCoverCarouselResponse?) {
        model?.let { presenter.mapResponse(it, this) }

        if (trackListener == null) {
            if (model != null) {
                TrackingUtils.trackShow(tracker, ArrayList<TouchpointTrackeable>(model.items))
            }
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

    override fun setItemsList(items: List<FlexCoverCard>) {
        viewPagerAdapter.setElementsView(items)
        viewPager.currentItem = 0
    }

    override fun decorate() {
        additionalInsets?.let { setViewPagerPaddingsFromInsets(it) }
    }

    private fun setViewPagerPaddingsFromInsets(
        additionalInsets: AdditionalEdgeInsets
    ) {
        viewPager.setPadding(
            getInsetInPx(additionalInsets.left),
            getInsetInPx(additionalInsets.top),
            getInsetInPx(additionalInsets.right),
            getInsetInPx(additionalInsets.bottom)
        )
    }

    private fun setCarouselPadding(page: Int) {
        if (page > 0 && page < viewPagerAdapter.count) {
            viewPager.setPadding(
                getInsetInPx(DEFAULT_CAROUSEL_PADDING),
                0,
                resources.getDimension(R.dimen.ui_2m).roundToInt(),
                0
            )
        } else {
            viewPager.setDefaultPagerPadding()
        }
    }

    private fun ViewPager.setDefaultPagerPadding() {
        setPadding(
            resources.getDimension(R.dimen.ui_2m).roundToInt(),
            0,
            getInsetInPx(DEFAULT_CAROUSEL_PADDING),
            0
        )
    }

    private fun getInsetInPx(inset: Int): Int {
        return ScaleUtils.getPxFromDp(context, inset.toFloat()).toInt()
    }

    override fun setOnClickCallback(onClickCallback: OnClickCallback?) {
        this.onClickCallback = onClickCallback
        this.onClickCallback?.let { viewPagerAdapter.setOnClickCallback(it) }
    }

    fun setTrackListener(trackListener: TrackListener?) {
        this.trackListener = trackListener
    }

    companion object {
        private const val DEFAULT_CAROUSEL_PADDING = 104
    }
}