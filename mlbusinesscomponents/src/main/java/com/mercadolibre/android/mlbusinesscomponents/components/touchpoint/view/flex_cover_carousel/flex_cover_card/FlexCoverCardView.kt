package com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.view.flex_cover_carousel.flex_cover_card

import android.animation.AnimatorInflater
import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.util.TypedValue
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.facebook.drawee.view.SimpleDraweeView
import com.mercadolibre.android.mlbusinesscomponents.R
import com.mercadolibre.android.mlbusinesscomponents.common.Constants
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.callback.OnClickCallback
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.flex_cover_carousel.FlexCoverCard
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.flex_cover_carousel.Logo
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.flex_cover_carousel.Text
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.tracking.MLBusinessTouchpointTracker
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.tracking.TouchpointTrackeable
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.tracking.print.TouchpointTracking
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.view.flex_cover_carousel.pill_touchpoint.PillInterface
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.view.flex_cover_carousel.pill_touchpoint.RowPillView
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
        setPressAnimation(context)
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
        cardCoverImage.refreshDrawableState()
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

    override fun setTracking(tracking: TouchpointTracking?) {
        this.tracking = tracking
    }

    override fun getTracking(): TouchpointTracking? {
        return tracking
    }

    override fun dismissClickable() {
        isClickable = false
    }

    override fun setOnClickCallback(onClickCallback: OnClickCallback) {
        this.onClickCallback = onClickCallback
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

    override fun showTitle(title: Text) {
        this.title.text = title.text
        this.title.visibility = VISIBLE
        title.textColor?.let { setTextColor(it, this.title) }
    }

    override fun showDescription(description: Text) {
        this.description.text = description.text
        this.description.visibility = VISIBLE
        description.textColor?.let { setTextColor(it, this.description) }
    }

    override fun showSubtitle(subtitle: Text) {
        this.subtitle.text = subtitle.text
        this.subtitle.visibility = VISIBLE
        subtitle.textColor?.let { setTextColor(it, this.subtitle) }
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
        if (logos.size == DEFAULT_LOGO_SIZE && logos.first().type == "image") {
            logo.visibility = VISIBLE
            logo.setImageURI(logos.first().image)
        }
    }

    private fun setTextColor(textColor: String?, text: TextView) {
        text.setTextColor(Color.parseColor(textColor))
    }

    private fun setCornerRadius() {
        radius = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            CORNER_RADIUS_VALUE,
            resources.displayMetrics
        )
    }

    private fun setPressAnimation(context: Context) {
        stateListAnimator = AnimatorInflater.loadStateListAnimator(
            context,
            R.drawable.cover_card_click_animation
        )
    }

    companion object {
        private const val CORNER_RADIUS_VALUE = 6f
        private const val DEFAULT_LOGO_SIZE = 1
    }
}