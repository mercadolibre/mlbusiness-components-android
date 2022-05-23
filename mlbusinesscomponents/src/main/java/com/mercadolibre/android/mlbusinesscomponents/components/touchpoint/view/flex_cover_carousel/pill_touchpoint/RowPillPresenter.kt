package com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.view.flex_cover_carousel.pill_touchpoint

class RowPillPresenter {

    fun bindPill(
        pill: PillInterface,
        view: RowPillView
    ) {

        if (pill.text.isNullOrEmpty()) {
            view.hideView()
            return
        }

        setText(pill.text, pill.textColor, view)
        setBackgroundColor(pill.backgroundColor, view)
    }

    private fun setText(text: String, textColor: String?, view: RowPillView) {
        view.setText(text, textColor)
    }

    private fun setBackgroundColor(backgroundColor: String?, view: RowPillView) {
        backgroundColor?.let { view.setBackground(it) }
    }
}
