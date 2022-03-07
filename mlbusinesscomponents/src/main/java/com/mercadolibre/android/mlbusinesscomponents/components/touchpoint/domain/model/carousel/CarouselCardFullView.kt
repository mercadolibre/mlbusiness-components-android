package com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.carousel

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.facebook.drawee.view.SimpleDraweeView
import com.mercadolibre.android.mlbusinesscomponents.R
import com.mercadolibre.android.mlbusinesscomponents.common.Constants
import com.mercadolibre.android.mlbusinesscomponents.common.TouchpointAssetLoader
import com.mercadolibre.android.mlbusinesscomponents.common.TouchpointImageLoader
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.callback.OnClickCallback
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.tracking.MLBusinessTouchpointTracker
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.tracking.TouchpointTrackeable
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.tracking.print.TouchpointTracking
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.view.carousel.card.AssetLoader
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.view.carousel.card.CarouselCardFullViewPresenter
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.view.carousel.card.CarouselCardInterface
import com.mercadolibre.android.mlbusinesscomponents.components.utils.TrackingUtils

class CarouselCardFullView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null
) : CardView(context, attrs), TouchpointTrackeable, CarouselCardInterface {

    private var mainImage: SimpleDraweeView
    private val mainLabel: TextView
    private var onClickCallback: OnClickCallback? = null
    private var tracker: MLBusinessTouchpointTracker? = null
    private var isMPInstalled = true
    private val button: FrameLayout
    private var tracking: TouchpointTracking? = null
    private val presenter: CarouselCardFullViewPresenter
    private var extraData: Map<String, Any>? = null

    init {
        inflate(context, R.layout.touchpoint_carousel_card_full_view, this)

        mainImage = findViewById(R.id.touchpoint_carousel_card_full_view_card_image)
        mainLabel = findViewById(R.id.touchpoint_carousel_card_full_view_main_label)
        button = findViewById(R.id.touchpoint_carousel_card_full_view_image_container)
        presenter = CarouselCardFullViewPresenter()
    }

    /**
     * Binds the model
     *
     * @param card the model
     */
    fun bind(card: CarouselCard?) {
        bind(card, Constants.NON_SIZE)
    }

    /**
     * Binds the model
     *
     * @param card the model
     * @param size the size in pixels
     */
    override fun bind(card: CarouselCard?, size: Int) {
        if (size != Constants.NON_SIZE) {
            val layoutParams: ViewGroup.LayoutParams = layoutParams
            layoutParams.height = size
        }
        presenter.onBind(card, this)
    }

    override fun getTracking(): TouchpointTracking? {
        return tracking
    }

    /* default */
    fun showImage(logo: String) {
        AssetLoader.getImage(logo, this.mainImage) { shouldLoadImage: Boolean ->
            if (shouldLoadImage) {
                TouchpointAssetLoader.create().withContainer(this.mainImage).withSource(logo).load()
            }
        }
    }

    /* default */
    fun hideImage() {
        mainImage.visibility = View.GONE
    }


    /* default */
    fun showMainLabel(mainLabel: String) {
        this.mainLabel.text = mainLabel
        this.mainLabel.visibility = View.VISIBLE
    }

    /**
     * Hides the main label view
     */
    fun hideMainLabel() {
        mainLabel.visibility = View.GONE
    }

    /* default */
    fun setTextColor(textColor: String?) {
        val color = Color.parseColor(textColor)
        mainLabel.setTextColor(color)
    }

    /* default */
    fun onClick(link: String) {
        button.isClickable = isMPInstalled
        if (isMPInstalled) {
            button.setOnClickListener { onClickEvent(link) }
        }
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

    override fun setOnClickCallback(onClickCallback: OnClickCallback) {
        this.onClickCallback = onClickCallback
    }

    override fun setTracker(tracker: MLBusinessTouchpointTracker) {
        this.tracker = tracker
    }

    override fun setExtraData(extraData: MutableMap<String, Any>) {
        this.extraData = extraData
    }

    override fun setCanOpenMercadoPago(isMPInstalled: Boolean) {
        this.isMPInstalled = isMPInstalled
    }

    override fun setImageLoader(imageLoader: TouchpointImageLoader) {
        AssetLoader.setStrategy(imageLoader)
    }
}
