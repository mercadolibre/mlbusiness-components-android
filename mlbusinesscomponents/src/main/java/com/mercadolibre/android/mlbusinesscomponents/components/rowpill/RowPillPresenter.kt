package com.mercadolibre.android.mlbusinesscomponents.components.rowpill

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
        setIcon(pill.leftIcon, view)
        setBorderColor(pill.borderColor, view)
    }

    private fun setText(text: String, textColor: String?, view: RowPillView) {
        view.setText(text, textColor)
    }

    private fun setBackgroundColor(backgroundColor: String?, view: RowPillView) {
        backgroundColor?.let { view.setBackground(it) }
    }

    private fun setIcon(icon: String?, view: RowPillView) {
        icon?.let { view.setIcon(it) }
    }

    private fun setBorderColor(color: String? ,view: RowPillView) {
        color?.let { view.setBorderColor(color) }
    }
}
