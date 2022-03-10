package com.mercadolibre.android.mlbusinesscomponents.components.adbanner

import android.content.Context
import android.util.AttributeSet
import com.mercadolibre.android.picassodiskcache.PicassoDiskLoader.get
import kotlin.jvm.JvmOverloads
import androidx.cardview.widget.CardView
import androidx.appcompat.widget.AppCompatImageView
import com.mercadolibre.android.mlbusinesscomponents.R
import android.util.TypedValue

class MLBusinessAdBannerView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : CardView(context, attrs, defStyleAttr) {

    private val image: AppCompatImageView

    init {
        inflate(context, R.layout.ml_view_ad_banner, this)
        image = findViewById(R.id.adBannerImage)
    }

    interface OnClickAdBannerView {
        fun onClickAdBannerViewLink(deepLink: String)
    }

    interface SendUrlOnClick {
        fun sendUrl(url: String)
    }

    fun init(
        businessAdBannerData: MLBusinessAdBannerData,
        onClick: OnClickAdBannerView,
        sendUrlOnClick: SendUrlOnClick? = null
    ) {
        loadImageUrl(businessAdBannerData.imageUrl)
        image.setOnClickListener {
            sendUrlOnClick?.sendUrl(businessAdBannerData.urlClick)
            onClick.onClickAdBannerViewLink(businessAdBannerData.urlDeepLink)
        }
    }

    private fun loadImageUrl(url: String) {
        get(context)
            .load(url)
            .placeholder(R.drawable.rectangle_skeleton)
            .into(image)
    }
}
