package com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.view.flex_cover_carousel.flex_cover_card

import kotlin.jvm.JvmOverloads
import android.util.AttributeSet
import androidx.cardview.widget.CardView
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.tracking.TouchpointTrackeable
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.view.cover_carousel.cover_card.CoverCardInterfaceView
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.view.cover_carousel.CardTransformer
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.view.flex_cover_carousel.flex_cover_card.FlexCoverCardPresenter
import com.facebook.drawee.view.SimpleDraweeView
import android.view.View
import android.widget.LinearLayout
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.tracking.print.TouchpointTracking
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.callback.OnClickCallback
import android.util.TypedValue
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.view.flex_cover_carousel.flex_cover_card.FlexCoverCardView
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.cover_carousel.model.cover_card.CoverCardInterfaceModel
import android.view.ViewGroup
import com.mercadolibre.android.mlbusinesscomponents.components.row.model.TouchpointRowItemInterface
import android.os.Build
import android.animation.AnimatorInflater
import android.content.Context
import com.mercadolibre.android.mlbusinesscomponents.R
import com.mercadolibre.android.mlbusinesscomponents.common.Constants

class FlexCoverCardView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : CardView(context, attrs, defStyleAttr), TouchpointTrackeable, CoverCardInterfaceView,
    CardTransformer {
    private val presenter: FlexCoverCardPresenter
    private val cardCoverImage: SimpleDraweeView
    private val skeletonView: View
    private val cardCoverContent: LinearLayout
    private var tracking: TouchpointTracking? = null
    private var onClickCallback: OnClickCallback? = null
    private fun setCornerRadius() {
        radius = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            CORNER_RADIUS_VALUE,
            resources.displayMetrics
        )
    }

    private fun onShowSkeleton() {
        showSkeleton()
    }

    /**
     * binds the view.
     *
     * @param model the data to bind
     */
    override fun bind(model: CoverCardInterfaceModel) {
        bind(model, Constants.NON_SIZE)
    }

    /**
     * binds the view.
     *
     * @param model the data to bind
     * @param size the card's size
     */
    override fun bind(model: CoverCardInterfaceModel, size: Int) {
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
     * Binds data from descriptions into an hybrid_row
     *
     * @param description the data to bind.
     */
    override fun setRow(description: TouchpointRowItemInterface) {}

    /**
     * Cover image for the card.
     *
     * @param cover url for the cover image.
     */
    override fun setCoverImage(cover: String) {
        cardCoverImage.setImageURI(cover)
        cardCoverImage.refreshDrawableState()
    }

    /**
     * Sets the click listener
     *
     * @param link the link
     */
    override fun setOnClick(link: String) {
        isClickable = true
        setOnClickListener { v: View? -> onClickEvent(link) }
    }

    private fun onClickEvent(link: String) {
        if (tracking != null) {
            onClickCallback!!.sendTapTracking(tracking)
        }
        onClickCallback!!.onClick(link)
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

    override fun getTracking(): TouchpointTracking? {
        return tracking
    }

    override fun getCoverCardHeight(): Int {
        return cardCoverImage.layoutParams.height + cardCoverContent.layoutParams.height
    }

    override fun showSkeleton() {
        skeletonView.visibility = VISIBLE
    }

    override fun hideSkeleton() {
        skeletonView.visibility = GONE
    }

    override fun getSkeletonState(): Boolean {
        return skeletonView.visibility == VISIBLE
    }

    override fun setPressAnimation() {
        presenter.setPressAnimationWithLink(this)
    }

    override fun setOnClickListenerWithAnimationAndLink(
        link: String,
        tracking: TouchpointTracking
    ) {
        isClickable = true
        setOnClickListener { v: View ->
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                v.stateListAnimator = AnimatorInflater.loadStateListAnimator(
                    context, R.drawable.cover_card_click_animation
                )
            }
            setTracking(tracking)
            onClickEvent(link)
        }
    }

    override fun setTracking(tracking: TouchpointTracking?) {
        this.tracking = tracking
    }

    override fun getView(): FlexCoverCardView {
        return this
    }

    //eliminar estos metodos que apagan el color de la imagen
    override fun setTopImageToClosedtStatus() {
        //   cardCoverImage.setAlpha(0.4f);
    }

    override fun setTopImageToDefaultStatus() {
        //  cardCoverImage.setAlpha(1f);
    }

    companion object {
        private const val CORNER_RADIUS_VALUE = 6f
    }

    init {
        inflate(getContext(), R.layout.touchpoint_flex_cover_carousel_card_view, this)
        cardCoverImage = findViewById(R.id.touchpoint_flex_cover_carousel_card_image)
        skeletonView = findViewById(R.id.touchpoint_cover_carousel_card_image_skeleton)
        cardCoverContent = findViewById(R.id.touchpoint_flex_cover_carousel_card_content)
        presenter = FlexCoverCardPresenter()
        setCornerRadius()
        onShowSkeleton()
    }
}