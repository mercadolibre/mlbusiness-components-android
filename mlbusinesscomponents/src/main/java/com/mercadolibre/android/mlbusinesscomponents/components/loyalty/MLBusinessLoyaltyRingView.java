package com.mercadolibre.android.mlbusinesscomponents.components.loyalty;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;

import android.widget.TextView;
import com.mercadolibre.android.mlbusinesscomponents.R;

public class MLBusinessLoyaltyRingView extends ConstraintLayout {

    public interface OnClickLoyaltyRing {
        void onClickLoyaltyButton(@NonNull final String deepLink);
    }

    private LoyaltyProgress progress;
    private TextView loyaltyTitle;
    private TextView loyaltyButton;
    private OnClickLoyaltyRing onClickLoyaltyRing;
    private MLBusinessLoyaltyRingData businessLoyaltyRingData;

    public MLBusinessLoyaltyRingView(final Context context) {
        super(context);
        initMLBusinessLoyaltyRingView(context, null);
    }

    public MLBusinessLoyaltyRingView(final Context context, final AttributeSet attrs) {
        super(context, attrs);
        initMLBusinessLoyaltyRingView(context, attrs);
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
        progress.setColorProgress(Color.parseColor(businessLoyaltyRingData.getRingHexaColor()));
        progress.setProgress(percentage);
        progress.setAnimation();
        progress.setLoyaltyNumber(businessLoyaltyRingData.getRingNumber());

        loyaltyTitle.setText(businessLoyaltyRingData.getTitle());
        loyaltyButton.setText(businessLoyaltyRingData.getButtonTitle());
        loyaltyButton.setOnClickListener(v -> {
                if (onClickLoyaltyRing != null) {
                    onClickLoyaltyRing
                        .onClickLoyaltyButton(businessLoyaltyRingData.getButtonDeepLink());
                }
            }
        );
    }

    public void init(
        @NonNull final MLBusinessLoyaltyRingData businessLoyaltyRingData,
        @NonNull final OnClickLoyaltyRing onClick) {
        this.businessLoyaltyRingData = businessLoyaltyRingData;
        this.onClickLoyaltyRing = onClick;
        configLoyaltyRingView();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (onClickLoyaltyRing != null) {
            onClickLoyaltyRing = null;
        }
    }
}
