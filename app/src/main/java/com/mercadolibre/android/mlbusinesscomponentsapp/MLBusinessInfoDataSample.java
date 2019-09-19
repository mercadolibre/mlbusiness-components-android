package com.mercadolibre.android.mlbusinesscomponentsapp;

import com.mercadolibre.android.mlbusinesscomponents.components.common.MLBusinessInfoData;

public class MLBusinessInfoDataSample implements MLBusinessInfoData {
    @Override
    public String getIcon() {
        return "https://http2.mlstatic.com/static/org-img/loyalty/benefits/mobile/ic-shipping-discount-64.png";
    }

    @Override
    public String getDescription() {
        return "Descuentos con Mercado Pago";
    }

    @Override
    public String getIconBackgroundHexaColor() {
        return "#1AC2B0";
    }
}
