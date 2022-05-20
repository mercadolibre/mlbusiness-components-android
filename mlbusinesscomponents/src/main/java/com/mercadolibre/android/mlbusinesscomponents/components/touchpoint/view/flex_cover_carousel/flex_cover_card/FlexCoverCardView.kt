package com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.view.flex_cover_carousel.flex_cover_card

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.util.TypedValue
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import com.facebook.drawee.view.SimpleDraweeView
import com.mercadolibre.android.mlbusinesscomponents.R
import com.mercadolibre.android.mlbusinesscomponents.common.Constants
import com.mercadolibre.android.mlbusinesscomponents.components.rowpill.PillInterface
import com.mercadolibre.android.mlbusinesscomponents.components.rowpill.RowPillView
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.callback.OnClickCallback
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.flex_cover_carousel.FlexCoverCard
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.flex_cover_carousel.Logo
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.tracking.MLBusinessTouchpointTracker
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.tracking.TouchpointTrackeable
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.tracking.print.TouchpointTracking
import com.mercadolibre.android.mlbusinesscomponents.components.utils.StringUtils
import com.mercadolibre.android.mlbusinesscomponents.components.utils.TrackingUtils

class FlexCoverCardView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : CardView(context, attrs, defStyleAttr), FlexCoverCardInterfaceView, TouchpointTrackeable {

    private val presenter: FlexCoverCardPresenter
    private val cardCoverImage: SimpleDraweeView
    private var onClickCallback: OnClickCallback? = null
    private val title: TextView
    private val subtitle: TextView
    private val description: TextView
    private val pill: RowPillView
    private val logo: SimpleDraweeView
    private var tracking: TouchpointTracking? = null
    private var tracker: MLBusinessTouchpointTracker? = null

    init {
        inflate(getContext(), R.layout.touchpoint_flex_cover_carousel_card_view, this)
        presenter = FlexCoverCardPresenter()
        cardCoverImage = findViewById(R.id.touchpoint_flex_cover_carousel_card_image)
        logo = findViewById(R.id.touchpoint_flex_cover_carousel_logo)
        title = findViewById(R.id.touchpoint_flex_cover_carousel_title)
        subtitle = findViewById(R.id.touchpoint_flex_cover_carousel_subtitle)
        description = findViewById(R.id.touchpoint_flex_cover_carousel_description)
        pill = findViewById(R.id.touchpoint_flex_cover_carousel_pill)
        setCornerRadius()
    }

    override fun bind(model: FlexCoverCard) {
        bind(model, Constants.NON_SIZE)
    }

    override fun bind(model: FlexCoverCard, size: Int) {
        if (size != Constants.NON_SIZE) {
            setNewHeight(size)
        }
        presenter.bindView(model, this)
    }

    private fun setNewHeight(size: Int) {
        val layoutParams: ViewGroup.LayoutParams = layoutParams
        layoutParams.height = size
    }

    override fun setCoverImage(cover: String) {
        cardCoverImage.setImageURI(cover)
    }

    override fun setOnClick(link: String) {
        isClickable = true
        setOnClickListener { onClickEvent(link) }
    }

    private fun onClickEvent(link: String) {
        if (onClickCallback != null) {
            if (tracker != null) {
                TrackingUtils.trackTap(tracker, tracking)
            } else {
                onClickCallback?.sendTapTracking(tracking)
            }
            onClickCallback?.onClick(link)
        }
    }

    fun setTracking(tracking: TouchpointTracking?) {
        this.tracking = tracking
    }

    override fun dismissClickable() {
        isClickable = false
    }

    override fun setOnClickCallback(onClickCallback: OnClickCallback) {
        this.onClickCallback = onClickCallback
    }

    override fun getCoverCardHeight(): Int {
        val constraintLayout: ConstraintLayout =
            findViewById(R.id.touchpoint_flex_cover_carousel_card_container)
        return cardCoverImage.layoutParams.height + (constraintLayout.layoutParams.height - cardCoverImage.layoutParams.height)
    }

    override fun getView(): FlexCoverCardView {
        return this
    }

    override fun changeBackgroundColor(backgroundColor: String) {
        if (StringUtils.isValidString(backgroundColor)) setCardBackgroundColor(
            Color.parseColor(
                backgroundColor
            )
        )
    }

    override fun showTitle(title: String) {
        this.title.text = title
        this.title.visibility = VISIBLE
    }

    override fun showDescription(description: String) {
        this.description.text = description
        this.description.visibility = VISIBLE
    }

    override fun showSubtitle(subtitle: String) {
        this.subtitle.text = subtitle
        this.subtitle.visibility = VISIBLE
    }

    override fun showPill(pill: PillInterface, view: FlexCoverCardInterfaceView) {
        this.pill.bind(pill)
        this.pill.setTextSize(
            resources.getDimension(
                R.dimen.ui_font_pill_text
            )
        )
        this.pill.visibility = VISIBLE
    }

    override fun showLogo(logos: List<Logo>, view: FlexCoverCardInterfaceView) {
        if (logos.size == 1 && logos[DEFAULT_LOGO_SIZE].type == "image") {
            logo.visibility = VISIBLE
            logo.setImageURI(logos[0].image)
            setLogoStyle(logos)
        }
    }

    private fun setLogoStyle(logos: List<Logo>) {
        if (logos[DEFAULT_LOGO_SIZE].style.width != null && logos[DEFAULT_LOGO_SIZE].style.height != null) {
            val width = logos[DEFAULT_LOGO_SIZE].style.width
            val height = logos[DEFAULT_LOGO_SIZE].style.height
            logo.layoutParams.width = TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                width.toFloat(),
                resources.displayMetrics
            ).toInt()
            logo.layoutParams.height = TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                height.toFloat(),
                resources.displayMetrics
            ).toInt()
        }
    }

    private fun setCornerRadius() {
        radius = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            CORNER_RADIUS_VALUE,
            resources.displayMetrics
        )
    }

    companion object {
        private const val CORNER_RADIUS_VALUE = 6f
        private const val DEFAULT_LOGO_SIZE = 0
    }

    override fun getTracking(): TouchpointTracking? {
        return tracking
    }
}