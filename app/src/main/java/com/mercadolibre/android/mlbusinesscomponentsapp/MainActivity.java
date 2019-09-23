package com.mercadolibre.android.mlbusinesscomponentsapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;

import com.mercadolibre.android.mlbusinesscomponents.components.common.MLBusinessInfoView;
import com.mercadolibre.android.mlbusinesscomponents.components.common.downloadapp.MLBusinessDownloadAppView;
import com.mercadolibre.android.mlbusinesscomponents.components.crossselling.MLBusinessCrossSellingBoxView;
import com.mercadolibre.android.mlbusinesscomponents.components.discount.MLBusinessDiscountBoxView;
import com.mercadolibre.android.mlbusinesscomponents.components.loyalty.MLBusinessLoyaltyHeaderView;
import com.mercadolibre.android.mlbusinesscomponents.components.loyalty.MLBusinessLoyaltyRingView;

public class MainActivity extends AppCompatActivity
        implements MLBusinessLoyaltyRingView.OnClickLoyaltyRing,
        MLBusinessDiscountBoxView.OnClickDiscountBox,
        MLBusinessCrossSellingBoxView.OnClickCrossSellingBoxView,
        MLBusinessDownloadAppView.OnClickDownloadApp {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MLBusinessLoyaltyRingView ringView = findViewById(R.id.loyaltyView);
        MLBusinessDiscountBoxView discountBoxView = findViewById(R.id.discountView);
        MLBusinessDownloadAppView downloadAppView = findViewById(R.id.downloadAppView);
        MLBusinessCrossSellingBoxView crossSellingBoxView = findViewById(R.id.crossSellingView);
        MLBusinessLoyaltyHeaderView loyaltyHeaderView = findViewById(R.id.loyaltyHeaderView);
        LinearLayout benefitContainer = findViewById(R.id.loyaltyBenefitsContainer);

        MLBusinessInfoView benefitView = new MLBusinessInfoView(this);

        ringView.init(new MLBusinessLoyaltyRingDataSample(), this);

        discountBoxView.init(new MLBusinessDiscountBoxDataSample(), this);

        downloadAppView.init(new MLBusinessDownloadAppDataSample(), this);

        crossSellingBoxView.init(new MLBusinessCrossSellingBoxDataSample(), this);

        loyaltyHeaderView.init(new MLBusinessLoyaltyHeaderDataSample());

        benefitView.init(new MLBusinessInfoDataSample());
        benefitContainer.addView(benefitView);
    }

    @Override
    public void onClickLoyaltyButton(@NonNull final String deepLink) {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(deepLink)));
    }

    @Override
    public void onClickDiscountItem(final int index, @Nullable final String deepLink,
                                    @Nullable final String trackId) {
        if (deepLink != null) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(deepLink)));
        }
    }

    @Override
    public void OnClickCrossSellingButton(@NonNull final String deepLink) {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(deepLink)));
    }

    @Override
    public void OnClickDownloadAppButton(@NonNull final String deepLink) {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(deepLink)));
    }
}
