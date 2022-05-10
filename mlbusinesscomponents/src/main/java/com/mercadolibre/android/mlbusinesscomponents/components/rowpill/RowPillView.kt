package com.mercadolibre.android.mlbusinesscomponents.components.rowpill

import android.content.Context
import android.graphics.Color
import android.text.Html
import android.util.AttributeSet
import android.util.TypedValue
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.facebook.drawee.view.SimpleDraweeView
import com.mercadolibre.android.mlbusinesscomponents.R
import com.mercadolibre.android.mlbusinesscomponents.components.utils.DensityUtils
import com.mercadolibre.android.picassodiskcache.loadImage

class RowPillView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : LinearLayout(context, attrs) {

    private val presenter = RowPillPresenter()
    private val chipCardView : CardView
    private val chipViewText: TextView
    private val discountsPayersPillLeftIcon: SimpleDraweeView

    init {
        inflate(context, R.layout.row_pill_view, this)
        setBackgroundColor(resources.getColor(R.color.andes_transparent))

        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.RowPillView)
        val cardCornerRadius = typedArray.getDimension(R.styleable.RowPillView_pillCornerRadius,
            DensityUtils.getPxFromDp(context, DEFAULT_ROUND_CORNERS).toFloat()
        )
        discountsPayersPillLeftIcon = findViewById(R.id.discounts_payers_pill_left_icon)
        chipViewText = findViewById(R.id.chip_view_text)
        chipCardView = findViewById(R.id.chip_card_view)
        chipCardView.radius = cardCornerRadius
        typedArray.recycle()
    }

    fun bind(pill: PillInterface) {
        presenter.bindPill(pill, this)
    }

    fun setText(text: String, textColor: String?) {
        chipViewText.apply {
            this.text = Html.fromHtml(text)
            textColor?.let { setTextColor(Color.parseColor(it)) }
        }
    }

     fun setTextSize(textSize: Float) {
         chipViewText.apply {
             setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize)
         }
    }

    fun setBackground(backgroundColor: String) {
        chipCardView.setCardBackgroundColor(Color.parseColor(backgroundColor))
    }

    fun hideView() {
        visibility = GONE
    }

    fun setIcon(icon: String) {
        discountsPayersPillLeftIcon.loadImage(icon)
        discountsPayersPillLeftIcon.visibility = VISIBLE
    }

    fun setBorderColor(color: String) {
       // chipCardView.strokeWidth = DEFAULT_STROKE_WIDTH
       // chipCardView.strokeColor = color.parseColorOrDefault(DEFAULT_COLOR)
    }

    companion object {
        private const val DEFAULT_ROUND_CORNERS = 10
        private const val DEFAULT_STROKE_WIDTH = 4
        private const val DEFAULT_COLOR = Color.WHITE
    }
}
