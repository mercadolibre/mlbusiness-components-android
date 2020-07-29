package com.mercadolibre.android.mlbusinesscomponents.components.actioncard

import android.content.Context
import android.graphics.Color
import android.os.Build
import android.support.v4.content.ContextCompat
import android.support.v7.widget.AppCompatImageView
import android.support.v7.widget.AppCompatTextView
import android.support.v7.widget.CardView
import android.util.AttributeSet
import android.util.TypedValue
import com.mercadolibre.android.mlbusinesscomponents.R
import com.mercadolibre.android.mlbusinesscomponents.components.utils.FontHelper
import com.mercadolibre.android.picassodiskcache.PicassoDiskLoader
import com.mercadolibre.android.ui.font.TypefaceHelper

class MLBusinessActionCardView(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : CardView(context,
    attrs, defStyleAttr) {

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context) : this(context, null, 0)

    init {
        initView(context)
    }

    private fun initView(context: Context) {
        inflate(context, R.layout.ml_view_action_card, this)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            isClickable = true
            foreground = ContextCompat.getDrawable(context, TypedValue().run {
                context.theme.resolveAttribute(android.R.attr.selectableItemBackground, this, true)
                resourceId
            })
        }
    }

    fun init(actionCardViewData: MLBusinessActionCardViewData) {
        PicassoDiskLoader.get(context).load(actionCardViewData.getImageUrl()).into(findViewById<AppCompatImageView>(R.id.split_image))
        findViewById<AppCompatTextView>(R.id.split_button).text = actionCardViewData.getAffordanceText()
        with(findViewById<AppCompatTextView>(R.id.split_text)) {
            text = actionCardViewData.getTitle()
            setTextColor(Color.parseColor(actionCardViewData.getTitleColor()))
            setBackgroundColor(Color.parseColor(actionCardViewData.getTitleBackgroundColor()))
            if (!isInEditMode) {
                TypefaceHelper.setTypeface(this, FontHelper.from(actionCardViewData.getTitleWeight()))
            }
        }
    }
}