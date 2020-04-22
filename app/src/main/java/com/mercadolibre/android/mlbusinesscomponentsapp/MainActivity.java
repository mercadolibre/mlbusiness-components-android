package com.mercadolibre.android.mlbusinesscomponentsapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import com.google.gson.Gson;
import com.mercadolibre.android.mlbusinesscomponents.components.common.MLBusinessInfoView;
import com.mercadolibre.android.mlbusinesscomponents.components.common.downloadapp.MLBusinessDownloadAppView;
import com.mercadolibre.android.mlbusinesscomponents.components.crossselling.MLBusinessCrossSellingBoxView;
import com.mercadolibre.android.mlbusinesscomponents.components.discount.MLBusinessDiscountBoxView;
import com.mercadolibre.android.mlbusinesscomponents.components.loyalty.MLBusinessLoyaltyHeaderView;
import com.mercadolibre.android.mlbusinesscomponents.components.loyalty.MLBusinessLoyaltyRingView;
import com.mercadolibre.android.mlbusinesscomponents.components.loyalty.broadcaster.LoyaltyBroadcastData;
import com.mercadolibre.android.mlbusinesscomponents.components.loyalty.broadcaster.LoyaltyBroadcastReceiver;
import com.mercadolibre.android.mlbusinesscomponents.components.loyalty.broadcaster.LoyaltyBroadcaster;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.OnClickCallback;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.TouchpointCreator;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.response.TouchpointResponse;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.view.TouchpointView;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity
    implements MLBusinessLoyaltyRingView.OnClickLoyaltyRing, MLBusinessDiscountBoxView.OnClickDiscountBox,
    MLBusinessCrossSellingBoxView.OnClickCrossSellingBoxView,
    MLBusinessDownloadAppView.OnClickDownloadApp, OnClickCallback {

    Gson gson = new Gson();
    int i = 0;
    LoyaltyBroadcast loyaltyBroadcast;

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
        FrameLayout touchpointContainer = findViewById(R.id.touchpoint_container);


        Button button = findViewById(R.id.buttonOpen);

        final Button nextDiscountBox = findViewById(R.id.discountButton);
        final Button changeTouchpointView = findViewById(R.id.touchpoint_button);

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

        final List<TouchpointSamples> samples = Arrays.asList(TouchpointSamples.values());
        changeTouchpointView.setOnClickListener(v -> {
            touchpointContainer.removeAllViews();
            if (samples.size() <= i) {
                i = 0;
            }
            final TouchpointResponse otherResponse = samples.get(i).get(gson, this);
            final TouchpointView otherView = TouchpointCreator.create(this, otherResponse).withOnClick(this).get();
            touchpointContainer.addView(otherView);
            i++;
        });
        changeTouchpointView.callOnClick();

        downloadAppView.init(new MLBusinessDownloadAppDataSample(), this);

        crossSellingBoxView.init(new MLBusinessCrossSellingBoxDataSample(), this);

        loyaltyHeaderView.init(new MLBusinessLoyaltyHeaderDataSample());

        benefitView.init(new MLBusinessInfoDataSample());
        benefitContainer.addView(benefitView);

        loyaltyBroadcast = new LoyaltyBroadcast();
        LoyaltyBroadcaster.getInstance().register(loyaltyBroadcast, getApplicationContext());
    }

    @Override
    public void onClick(@Nullable final String deepLink) {
        if (deepLink != null) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(deepLink)));
        }
    }

    private class LoyaltyBroadcast extends LoyaltyBroadcastReceiver {

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

    @Override
    protected void onDestroy() {
        LoyaltyBroadcaster.getInstance().unregister(loyaltyBroadcast, getApplicationContext());
        super.onDestroy();
    }
}
