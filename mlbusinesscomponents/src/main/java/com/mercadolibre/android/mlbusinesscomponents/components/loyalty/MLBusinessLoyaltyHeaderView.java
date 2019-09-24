package com.mercadolibre.android.mlbusinesscomponents.components.loyalty;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mercadolibre.android.mlbusinesscomponents.R;

public class MLBusinessLoyaltyHeaderView extends ConstraintLayout {

    private ViewGroup containerView;
    private LoyaltyProgress progress;
    private TextView title;
    private TextView loyaltyLevelInfo;
    private MLBusinessLoyaltyHeaderData businessLoyaltyHeaderData;

    public MLBusinessLoyaltyHeaderView(Context context) {
        super(context);
        initMLBusinessLoyaltyHeaderView(context);
    }

    public MLBusinessLoyaltyHeaderView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initMLBusinessLoyaltyHeaderView(context);
    }

    public MLBusinessLoyaltyHeaderView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initMLBusinessLoyaltyHeaderView(context);
    }

    private void initMLBusinessLoyaltyHeaderView(final Context context) {
        inflate(context, R.layout.ml_view_business_loyalty_header, this);
        initLoyaltyHeaderView();
    }

    private void initLoyaltyHeaderView() {
        containerView = findViewById(R.id.containerView);
        progress = findViewById(R.id.loyaltyRing);
        title = findViewById(R.id.loyaltyTitle);
        loyaltyLevelInfo = findViewById(R.id.loyaltyLevelInfo);
    }

    private void configLoyaltyHeaderView() {
        updateRing();

        int textColor = Color.parseColor(businessLoyaltyHeaderData.getPrimaryHexaColor());
        title.setText(businessLoyaltyHeaderData.getTitle());
        title.setTextColor(textColor);

        loyaltyLevelInfo.setText(businessLoyaltyHeaderData.getSubtitle());
        loyaltyLevelInfo.setTextColor(textColor);

        containerView.setBackgroundColor(Color.parseColor(businessLoyaltyHeaderData.getBackgroundHexaColor()));
    }

    private void updateRing() {
        final float percentage = businessLoyaltyHeaderData.getRingPercentage();
        progress.setColorProgress(Color.parseColor(businessLoyaltyHeaderData.getPrimaryHexaColor()));
        progress.setColorText(Color.parseColor(businessLoyaltyHeaderData.getPrimaryHexaColor()));
        progress.setColorSecondaryRing(Color.parseColor(businessLoyaltyHeaderData.getSecondaryHexaColor()));
        progress.setProgress(percentage);
        progress.setAnimation();
        progress.setNumber(businessLoyaltyHeaderData.getRingNumber());
    }

    public void init(
            @NonNull final MLBusinessLoyaltyHeaderData businessLoyaltyHeaderData) {
        this.businessLoyaltyHeaderData = businessLoyaltyHeaderData;
        configLoyaltyHeaderView();
    }

    public void updateWithModel(
            @NonNull final MLBusinessLoyaltyHeaderData businessLoyaltyHeaderData) {
        init(businessLoyaltyHeaderData);
    }
}
