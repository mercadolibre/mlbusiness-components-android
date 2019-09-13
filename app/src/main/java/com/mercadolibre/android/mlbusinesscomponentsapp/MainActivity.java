package com.mercadolibre.android.mlbusinesscomponentsapp;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import com.mercadolibre.android.mlbusinesscomponents.components.common.DownloadAppView;
import com.mercadolibre.android.mlbusinesscomponents.components.discount.MLBusinessDiscountBoxView;
import com.mercadolibre.android.mlbusinesscomponents.components.loyalty.MLBusinessLoyaltyRingView;

public class MainActivity extends AppCompatActivity
    implements MLBusinessLoyaltyRingView.OnClickLoyaltyRing,
    MLBusinessDiscountBoxView.OnClickDiscountBox, View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MLBusinessLoyaltyRingView ringView = findViewById(R.id.loyaltyView);
        MLBusinessDiscountBoxView discountBoxView = findViewById(R.id.discountView);
        DownloadAppView downloadAppView = findViewById(R.id.downloadAppView);

        ringView.init(new MLBusinessLoyaltyRingDataSample(), this);

        discountBoxView.init(new MLBusinessDiscountBoxDataSample(), this);

        downloadAppView.init(DownloadAppView.AppSite.ML, "Exclusivo con la app de Mercado Pago", "Descargar");
        downloadAppView.setOnClickDownloadView(this);

    }

    @Override
    public void onClickLoyaltyButton(@NonNull final String deepLink) {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(deepLink)));
    }

    @Override
    public void onClickDiscountItem(final int index, @Nullable final String deepLink, @Nullable final String trackId) {
        if (deepLink != null) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(deepLink)));
        }
    }

    @Override
    public void onClick(final View v) {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.mercadolibre.com.ar/")));
    }
}
