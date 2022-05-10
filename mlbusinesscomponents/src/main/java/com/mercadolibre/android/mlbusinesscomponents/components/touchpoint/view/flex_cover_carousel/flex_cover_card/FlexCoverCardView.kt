package com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.view.flex_cover_carousel.flex_cover_card

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.util.AttributeSet
import android.util.TypedValue
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import com.facebook.drawee.view.SimpleDraweeView
import com.mercadolibre.android.mlbusinesscomponents.R
import com.mercadolibre.android.mlbusinesscomponents.common.Constants
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.callback.OnClickCallback
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.flex_cover_carousel.FlexCoverCard
import com.mercadolibre.android.mlbusinesscomponents.components.utils.StringUtils

class FlexCoverCardView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : CardView(context, attrs, defStyleAttr), FlexCoverCardInterfaceView {

    private val presenter: FlexCoverCardPresenter
    private val cardCoverImage: SimpleDraweeView
    private var onClickCallback: OnClickCallback? = null
    private val title: TextView
    private val subtitle: TextView
    private val description: TextView

    init {
        inflate(getContext(), R.layout.touchpoint_flex_cover_carousel_card_view, this)
        cardCoverImage = findViewById(R.id.touchpoint_flex_cover_carousel_card_image)
        presenter = FlexCoverCardPresenter()
        title = findViewById(R.id.touchpoint_flex_cover_carousel_title)
        subtitle = findViewById(R.id.touchpoint_flex_cover_carousel_subtitle)
        description = findViewById(R.id.touchpoint_flex_cover_carousel_description)
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
        val layoutParams = layoutParams
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
        onClickCallback?.onClick(link)
    }

    override fun dismissClickable() {
        isClickable = false
    }

    override fun setOnClickCallback(onClickCallback: OnClickCallback) {
        this.onClickCallback = onClickCallback
    }

    override fun getCoverCardHeight(): Int {
        val constraintLayout: ConstraintLayout = findViewById(R.id.touchpoint_flex_cover_carousel_card_container)
        return cardCoverImage.layoutParams.height + (constraintLayout.layoutParams.height - cardCoverImage.layoutParams.height)
    }

    override fun getView(): FlexCoverCardView {
        return this
    }

    override fun changeBackgroundColor(backgroundColor: String) {
        if (StringUtils.isValidString(backgroundColor)) setCardBackgroundColor(Color.parseColor(backgroundColor))
    }

    override fun showTitle(title: String){
       this.title.text =title
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

    private fun setCornerRadius() {
        radius = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            CORNER_RADIUS_VALUE,
            resources.displayMetrics
        )
    }

    companion object {
        private const val CORNER_RADIUS_VALUE = 6f
    }
}