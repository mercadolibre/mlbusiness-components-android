package com.mercadolibre.android.mlbusinesscomponentsapp;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.mercadolibre.android.mlbusinesscomponents.components.discount.MLBusinessDiscountBoxView;
import com.mercadolibre.android.mlbusinesscomponents.components.loyalty.MLBusinessLoyaltyRingView;

public class MainActivity extends AppCompatActivity implements MLBusinessLoyaltyRingView.OnClickLoyaltyRing,
    MLBusinessDiscountBoxView.OnClickDiscountBox {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MLBusinessLoyaltyRingView ringView = findViewById(R.id.loyaltyView);
        MLBusinessDiscountBoxView discountBoxView = findViewById(R.id.discountView);

        ringView.setBusinessLoyaltyRingData(new MLBusinessLoyaltyRingDataSample());
        ringView.setOnClickLoyaltyRing(this);

        discountBoxView.setBusinessDiscountBoxData(new MLBusinessDiscountBoxDataSample());
        discountBoxView.setOnClickDiscountBox(this);

    }

    @Override
    public void onClickLoyaltyButton(@NonNull final String deepLink) {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(deepLink)));
    }

    @Override
    public void onClickDiscountItem(@Nullable final String deepLink, @Nullable final String trackId) {
        if (deepLink != null) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(deepLink)));
        }
    }
}
