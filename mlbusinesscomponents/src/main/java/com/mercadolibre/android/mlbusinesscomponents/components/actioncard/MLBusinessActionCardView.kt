package com.mercadolibre.android.mlbusinesscomponents.components.actioncard

import android.content.Context
import android.graphics.Color
import android.support.v7.widget.CardView
import android.util.AttributeSet
import com.mercadolibre.android.mlbusinesscomponents.R
import com.mercadolibre.android.mlbusinesscomponents.components.utils.FontHelper
import com.mercadolibre.android.picassodiskcache.PicassoDiskLoader
import com.mercadolibre.android.ui.font.TypefaceHelper
import kotlinx.android.synthetic.main.ml_view_action_card.view.*

class MLBusinessActionCardView(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : CardView(context, attrs, defStyleAttr) {

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context) : this(context, null, 0)

    init {
        initView(context)
    }

    private fun initView(context: Context) {
        inflate(context, R.layout.ml_view_action_card, this)
    }

    fun init(actionCardViewData: MLBusinessActionCardViewData) {
        PicassoDiskLoader.get(context).load(actionCardViewData.getImageUrl()).into(split_image)
        split_button.text = actionCardViewData.getAffordanceText()
        with(split_text) {
            text = actionCardViewData.getTitle()
            setTextColor(Color.parseColor(actionCardViewData.getTitleColor()))
            setBackgroundColor(Color.parseColor(actionCardViewData.getTitleBackgroundColor()))
            if (!isInEditMode) {
                TypefaceHelper.setTypeface(this, FontHelper.from(actionCardViewData.getTitleWeight()))
            }
        }
    }
}