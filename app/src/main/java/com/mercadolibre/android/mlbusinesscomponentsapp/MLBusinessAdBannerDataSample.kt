package com.mercadolibre.android.mlbusinesscomponentsapp

import com.mercadolibre.android.mlbusinesscomponents.components.adbanner.MLBusinessAdBannerData

class MLBusinessAdBannerDataSample : MLBusinessAdBannerData {
    override val imageUrl: String
        get() = "https://http2.mlstatic.com/D_NQ_750106-MLA46995964779_082021-F.webp"
    override val urlDeepLink: String
        get() = "https://www.mercadolibre.com.ar/"
}
