package com.mercadolibre.android.mlbusinesscomponentsapp

import com.mercadolibre.android.mlbusinesscomponents.components.actioncard.MLBusinessActionCardViewData

class MLBusinessActionCardViewDataSample: MLBusinessActionCardViewData {
    override fun getTitle() = "Podés dividir este gasto con tus contactos"

    override fun getTitleColor() = "#cc000000"

    override fun getTitleBackgroundColor() = "#ffffff"

    override fun getTitleWeight() = "semi_bold"

    override fun getImageUrl() = "https://i.imgur.com/OX0LH62.png"

    override fun getAffordanceText() = "Dividir gasto"
}