package com.mercadolibre.android.mlbusinesscomponentsapp;

import androidx.annotation.NonNull;
import com.mercadolibre.android.mlbusinesscomponents.components.common.MLBusinessInfoData;

public class MLBusinessInfoDataSample implements MLBusinessInfoData {
    @NonNull
    @Override
    public String getIcon() {
        return "https://http2.mlstatic.com/static/org-img/loyalty/benefits/mobile/ic-shipping-discount-64.png";
    }

    @NonNull
    @Override
    public String getDescription() {
        return "Descuentos con Mercado Pago";
    }

    @NonNull
    @Override
    public String getIconBackgroundHexaColor() {
        return "#1AC2B0";
    }

    @Override
    public int getLogoSize() {
        return 40;
    }

    @Override
    public int getTextSize() {
        return 20;
    }
}
