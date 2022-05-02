package com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.view.flex_cover_carousel

import android.content.Context
import kotlin.jvm.JvmOverloads
import android.util.AttributeSet
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.view.AbstractTouchpointChildView
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.cover_carousel.response.CoverCarouselInterfaceModel
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.view.cover_carousel.CoverCarouselViewInterface
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.view.flex_cover_carousel.FlexCoverCarouselPresenter
import android.widget.ViewFlipper
import androidx.viewpager.widget.ViewPager
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.view.flex_cover_carousel.FlexCoverCardViewPagerAdapter
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.view.carousel.card.TrackListener
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.mercadolibre.android.mlbusinesscomponents.components.utils.TrackingUtils
import java.util.ArrayList
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.tracking.TouchpointTrackeable
import android.view.View
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.cover_carousel.model.cover_card.CoverCardInterfaceModel
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.AdditionalEdgeInsets
import com.mercadolibre.android.mlbusinesscomponents.components.utils.ScaleUtils
import android.view.ViewGroup
import android.util.TypedValue
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.view.flex_cover_carousel.FlexCoverCarouselView
import com.mercadolibre.android.mlbusinesscomponents.R
import androidx.viewpager.widget.ViewPager.PageTransformer
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.view.cover_carousel.CoverCarouselPageTransformer
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.callback.OnClickCallback

class FlexCoverCarouselView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AbstractTouchpointChildView<CoverCarouselInterfaceModel?>(context, attrs, defStyleAttr),
    CoverCarouselViewInterface {
    private val presenter: FlexCoverCarouselPresenter
    private val flipper: ViewFlipper
    private val viewPager: ViewPager
    private val viewPagerAdapter: FlexCoverCardViewPagerAdapter
    private var trackListener: TrackListener? = null
    private fun initViewPager() {
        viewPager.adapter = viewPagerAdapter
        viewPager.addOnPageChangeListener(object : OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                //no op..
            }

            override fun onPageSelected(position: Int) {
                if (trackListener != null) {
                    trackListener!!.print()
                }
            }

            override fun onPageScrollStateChanged(state: Int) {
                //no op..
            }
        })
    }

    override fun bind(model: CoverCarouselInterfaceModel?) {
        presenter.mapResponse(model, this)
        if (trackListener == null) {
            TrackingUtils.trackShow(tracker, ArrayList<TouchpointTrackeable>(model!!.items))
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

    override fun setItemsList(items: List<CoverCardInterfaceModel>) {
        viewPagerAdapter.setElementsView(items)
        viewPager.currentItem = 0
        presenter.getMaxHeight(viewPagerAdapter.elementsList, this)
    }

    override fun decorate() {
        // Traer este valor desde la firma
        // if (additionalInsets != null) {
        setViewPagerPaddingsFromInsets(additionalInsets!!)
        //  }
    }

    private fun setViewPagerPaddingsFromInsets(
        additionalInsets: AdditionalEdgeInsets
    ) {
        viewPager.clipToPadding = false
        // eliminar valores harcodeados
        viewPager.setPadding(getInsetInPx(16), 0, getInsetInPx(72), getInsetInPx(12))
    }

    private fun getInsetInPx(inset: Int): Int {
        return ScaleUtils.getPxFromDp(context, inset.toFloat()).toInt()
    }

    override fun setViewPagerHeight(maxHeight: Int, isSkeletonVisible: Boolean) {
        val params = viewPager.layoutParams
        if (isSkeletonVisible) {
            val viewPagerPadding = TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                VIEW_PAGER_PADDING_DIP.toFloat(),
                resources.displayMetrics
            ).toInt()
            params.height = maxHeight + viewPagerPadding
        } else {
            params.height = maxHeight + resources.getDimensionPixelSize(R.dimen.ui_2m)
        }
    }

    override fun setAnimations(
        alphaAnimation: Boolean, scaleAnimation: Boolean,
        pressAnimation: Boolean
    ) {
        val transformer: PageTransformer = CoverCarouselPageTransformer(
            alphaAnimation, scaleAnimation, pressAnimation, context
        )
        viewPager.setPageTransformer(false, transformer)
        viewPager.setPageTransformer(false) { page: View, position: Float ->
            if (viewPager.currentItem == viewPagerAdapter.count - 1) {
                page.translationX = (viewPager.paddingRight - viewPager.paddingLeft).toFloat()
            } else {
                page.translationX = 0f
            }
        }
    }

    override fun setMarginsForScaledAnimation() {
        viewPager.pageMargin = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            MARGIN_BETWEEN_SCALED_PAGES.toFloat(),
            resources.displayMetrics
        ).toInt()
    }

    override fun setMarginsForNonScaledAnimation() {
        viewPager.pageMargin = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            MARGIN_BETWEEN_PAGES.toFloat(),
            resources.displayMetrics
        ).toInt()
    }

    override fun showSkeleton() {
        flipper.displayedChild = COVER_CAROUSEL_SKELETON_INDEX
    }

    override fun hideSkeleton() {
        flipper.displayedChild = COVER_CAROUSEL_CONTAINER_INDEX
    }

    override fun hideHeaderContainer() {}
    override fun setHeaderTitle(title: String) {}
    override fun hideHeaderAction() {}
    override fun setHeaderActionTitle(title: String) {}
    override fun setHeaderActionClickListener(link: String) {
        if (onClickCallback != null) {
        }
    }

    fun setTrackListener(trackListener: TrackListener?) {
        this.trackListener = trackListener
    }

    override fun setOnClickCallback(onClickCallback: OnClickCallback?) {
        this.onClickCallback = onClickCallback
        viewPagerAdapter.setOnClickCallback(this.onClickCallback)
    }

    companion object {
        private const val COVER_CAROUSEL_CONTAINER_INDEX = 0
        private const val COVER_CAROUSEL_SKELETON_INDEX = 1
        private const val MARGIN_BETWEEN_PAGES = 8
        private const val MARGIN_BETWEEN_SCALED_PAGES = -10
        private const val VIEW_PAGER_PADDING_DIP = 16
    }

    init {
        inflate(context, R.layout.touchpoint_flex_cover_carousel_view, this)
        presenter = FlexCoverCarouselPresenter()
        flipper = findViewById(R.id.touchpoint_flex_cover_carousel_view_flipper)
        viewPager = findViewById(R.id.flex_cover_carousel_view_pager)
        viewPagerAdapter = FlexCoverCardViewPagerAdapter(getContext())
        initViewPager()
    }
}