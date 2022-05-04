package com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.view.flex_cover_carousel.flex_cover_card

import android.content.Context
import android.util.AttributeSet
import android.util.TypedValue
import android.widget.LinearLayout
import androidx.cardview.widget.CardView
import com.facebook.drawee.view.SimpleDraweeView
import com.mercadolibre.android.mlbusinesscomponents.R
import com.mercadolibre.android.mlbusinesscomponents.common.Constants
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.callback.OnClickCallback
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.flex_cover_carousel.FlexCoverCard

class FlexCoverCardView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : CardView(context, attrs, defStyleAttr), FlexCoverCardInterfaceView {

    private val presenter: FlexCoverCardPresenter
    private val cardCoverImage: SimpleDraweeView
    private val cardCoverContent: LinearLayout
    private var onClickCallback: OnClickCallback? = null

    init {
        inflate(getContext(), R.layout.touchpoint_flex_cover_carousel_card_view, this)
        cardCoverImage = findViewById(R.id.touchpoint_flex_cover_carousel_card_image)
        cardCoverContent = findViewById(R.id.touchpoint_flex_cover_carousel_card_content)
        presenter = FlexCoverCardPresenter()
        setCornerRadius()
    }

    /**
     * binds the view.
     *
     * @param model the data to bind
     */
    override fun bind(model: FlexCoverCard?) {
        bind(model, Constants.NON_SIZE)
    }

    /**
     * binds the view.
     *
     * @param model the data to bind
     * @param size the card's size
     */
    override fun bind(model: FlexCoverCard?, size: Int) {
        if (size != Constants.NON_SIZE) {
            setNewHeight(size)
        }
        presenter.bindView(model, this)
    }

    private fun setNewHeight(size: Int) {
        val layoutParams = layoutParams
        layoutParams.height = size
    }

    /**
     * Cover image for the card.
     *
     * @param cover url for the cover image.
     */
    override fun setCoverImage(cover: String) {
        cardCoverImage.setImageURI(cover)
    }

    /**
     * Sets the click listener
     *
     * @param link the link
     */
    override fun setOnClick(link: String) {
        isClickable = true
        setOnClickListener { onClickEvent(link) }
    }

    private fun onClickEvent(link: String?) {
        onClickCallback?.onClick(link)
    }

    /**
     * dissmiss the click action
     */
    override fun dismissClickable() {
        isClickable = false
    }

    /**
     * sets the onclick callback
     *
     * @param onClickCallback
     */
    override fun setOnClickCallback(onClickCallback: OnClickCallback?) {
        this.onClickCallback = onClickCallback
    }

    override fun getCoverCardHeight(): Int {
        return cardCoverImage.layoutParams.height + cardCoverContent.layoutParams.height
    }

    override fun getView(): FlexCoverCardView {
        return this
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