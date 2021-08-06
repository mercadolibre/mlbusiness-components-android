package com.mercadolibre.android.mlbusinesscomponentsapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.LinearLayout;
import com.mercadolibre.android.mlbusinesscomponents.components.common.MLBusinessInfoView;
import com.mercadolibre.android.mlbusinesscomponents.components.common.downloadapp.MLBusinessDownloadAppView;
import com.mercadolibre.android.mlbusinesscomponents.components.crossselling.MLBusinessCrossSellingBoxView;
import com.mercadolibre.android.mlbusinesscomponents.components.discount.MLBusinessDiscountBoxView;
import com.mercadolibre.android.mlbusinesscomponents.components.loyalty.MLBusinessLoyaltyHeaderView;
import com.mercadolibre.android.mlbusinesscomponents.components.loyalty.MLBusinessLoyaltyRingView;
import com.mercadolibre.android.mlbusinesscomponents.components.loyalty.broadcaster.LoyaltyBroadcastData;
import com.mercadolibre.android.mlbusinesscomponents.components.loyalty.broadcaster.LoyaltyBroadcastReceiver;
import com.mercadolibre.android.mlbusinesscomponents.components.loyalty.broadcaster.LoyaltyBroadcaster;
import com.mercadolibre.android.mlbusinesscomponents.components.actioncard.MLBusinessActionCardView;
import com.mercadolibre.android.mlbusinesscomponentsapp.touchpoint.TouchpointTestActivity;

public class MainActivity extends AppCompatActivity
    implements MLBusinessLoyaltyRingView.OnClickLoyaltyRing, MLBusinessDiscountBoxView.OnClickDiscountBox,
    MLBusinessCrossSellingBoxView.OnClickCrossSellingBoxView,
    MLBusinessDownloadAppView.OnClickDownloadApp {

    LoyaltyBroadcast loyaltyBroadcast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MLBusinessLoyaltyRingView ringView = findViewById(R.id.loyaltyView);
        MLBusinessDiscountBoxView discountBoxView = findViewById(R.id.discountView);
        MLBusinessDownloadAppView downloadAppView = findViewById(R.id.downloadAppView);
        MLBusinessActionCardView splitPaymentView = findViewById(R.id.money_split);
        MLBusinessCrossSellingBoxView crossSellingBoxView = findViewById(R.id.crossSellingView);
        MLBusinessLoyaltyHeaderView loyaltyHeaderView = findViewById(R.id.loyaltyHeaderView);
        MLBusinessLoyaltyHeaderView loyaltyHeaderViewWithSubscription = findViewById(R.id.loyaltyHeaderViewWithSubscription);
        LinearLayout benefitContainer = findViewById(R.id.loyaltyBenefitsContainer);

        Button button = findViewById(R.id.buttonOpen);

        final Button nextDiscountBox = findViewById(R.id.discountButton);
        final Button openTouchpoint = findViewById(R.id.touchpoint_button);
        openTouchpoint
            .setOnClickListener(v -> startActivity(new Intent(MainActivity.this, TouchpointTestActivity.class)));

        button.setOnClickListener(v -> {
            final Intent intent = new Intent(MainActivity.this, ButtonsActivity.class);
            startActivity(intent);
        });

        MLBusinessInfoView benefitView = new MLBusinessInfoView(this);

        ringView.init(new MLBusinessLoyaltyRingDataSample(), this);

        final MLBusinessDiscountBoxDataSample discount = new MLBusinessDiscountBoxDataSample();
        discountBoxView.init(discount, this);
        nextDiscountBox.setOnClickListener(v -> {
            discount.changeItems();
            discountBoxView.updateWithData(discount, this);
        });

        downloadAppView.init(new MLBusinessDownloadAppDataSample(), this);

        splitPaymentView.init(new MLBusinessActionCardViewDataSample());
        splitPaymentView.setOnClickListener(v -> onClickSplitPaymentButton());

        crossSellingBoxView.init(new MLBusinessCrossSellingBoxDataSample(), this);

        loyaltyHeaderView.init(new MLBusinessLoyaltyHeaderDataSample());
        loyaltyHeaderViewWithSubscription.init(new MLBusinessLoyaltyHeaderDataSampleWithSubscription());

        benefitView.init(new MLBusinessInfoDataSample());
        benefitContainer.addView(benefitView);

        loyaltyBroadcast = new LoyaltyBroadcast();
        LoyaltyBroadcaster.getInstance().register(loyaltyBroadcast, getApplicationContext());
    }

    private static class LoyaltyBroadcast extends LoyaltyBroadcastReceiver {

        @Override
        public void onReceive(LoyaltyBroadcastData loyaltyBroadcastData) {
            Log.d("LoyaltyBroadcast", "Mensaje recibido del broadcast de Loyalty");
        }
    }

    @Override
    public void onClickLoyaltyButton(@NonNull final String deepLink) {
        LoyaltyBroadcastData loyaltyBroadcastData = new LoyaltyBroadcastData();
        loyaltyBroadcastData.setPercentage(0.2f);
        loyaltyBroadcastData.setLevel(2);
        loyaltyBroadcastData.setPrimaryColor("#FEFEFE");

        LoyaltyBroadcaster.getInstance().updateInfo(getApplicationContext(), loyaltyBroadcastData);
        launchActivity(deepLink);
    }

    @Override
    public void onClickDiscountItem(final int index, @Nullable final String deepLink,
        @Nullable final String trackId) {
        if (deepLink != null) {
            launchActivity(deepLink);
        }
    }

    @Override
    public void OnClickCrossSellingButton(@NonNull final String deepLink) {
        launchActivity(deepLink);
    }

    @Override
    public void OnClickDownloadAppButton(@NonNull final String deepLink) {
        launchActivity(deepLink);
    }

    private void onClickSplitPaymentButton() {
        launchActivity("mercadopago://mplayer/money_split_external?operation_id=7068064969&source=px");
    }

    private void launchActivity(@NonNull final String deepLink) {
        try {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(deepLink)));
        } catch (Exception e) {

        }
    }

    @Override
    protected void onDestroy() {
        LoyaltyBroadcaster.getInstance().unregister(loyaltyBroadcast, getApplicationContext());
        super.onDestroy();
    }
}
