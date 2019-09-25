package com.mercadolibre.android.mlbusinesscomponents.components.loyalty;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;

import android.widget.TextView;
import com.mercadolibre.android.mlbusinesscomponents.R;

import java.lang.ref.WeakReference;

public class MLBusinessLoyaltyRingView extends ConstraintLayout {

    public interface OnClickLoyaltyRing {
        void onClickLoyaltyButton(@NonNull final String deepLink);
    }

    private LoyaltyProgress progress;
    private TextView loyaltyTitle;
    private TextView loyaltyButton;
    private WeakReference<OnClickLoyaltyRing> onClickLoyaltyRing;
    private MLBusinessLoyaltyRingData businessLoyaltyRingData;

    public MLBusinessLoyaltyRingView(final Context context) {
        this(context, null);
    }

    public MLBusinessLoyaltyRingView(final Context context, final AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MLBusinessLoyaltyRingView(final Context context, final AttributeSet attrs,
        final int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initMLBusinessLoyaltyRingView(context, attrs);
    }

    private void initMLBusinessLoyaltyRingView(final Context context, final AttributeSet attrs) {
        inflate(context, R.layout.ml_view_business_loyalty_ring, this);
        initLoyaltyRingView();
    }

    private void initLoyaltyRingView() {
        progress = findViewById(R.id.loyaltyRing);
        loyaltyTitle = findViewById(R.id.loyaltyTitle);
        loyaltyButton = findViewById(R.id.loyaltyButton);
    }

    private void configLoyaltyRingView() {
        final float percentage = businessLoyaltyRingData.getRingPercentage();
        final int colorRing = Color.parseColor(businessLoyaltyRingData.getRingHexaColor());
        progress.setColorText(colorRing);
        progress.setColorProgress(colorRing);
        progress.setProgress(percentage);
        progress.setAnimation();
        progress.setNumber(businessLoyaltyRingData.getRingNumber());

        loyaltyTitle.setText(businessLoyaltyRingData.getTitle());
        loyaltyButton.setText(businessLoyaltyRingData.getButtonTitle());
        loyaltyButton.setOnClickListener(v -> {
                if (onClickLoyaltyRing != null) {
                    OnClickLoyaltyRing listener = onClickLoyaltyRing.get();
                    if (listener != null) {
                        listener
                                .onClickLoyaltyButton(businessLoyaltyRingData.getButtonDeepLink());
                    }
                }
            }
        );
    }

    public void init(
        @NonNull final MLBusinessLoyaltyRingData businessLoyaltyRingData,
        @NonNull final OnClickLoyaltyRing onClick) {
        this.businessLoyaltyRingData = businessLoyaltyRingData;
        this.onClickLoyaltyRing = new WeakReference<>(onClick);
        configLoyaltyRingView();
    }

}