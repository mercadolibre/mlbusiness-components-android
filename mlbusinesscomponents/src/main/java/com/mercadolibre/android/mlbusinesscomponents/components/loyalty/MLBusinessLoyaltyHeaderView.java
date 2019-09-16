package com.mercadolibre.android.mlbusinesscomponents.components.loyalty;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.content.res.AppCompatResources;
import android.util.AttributeSet;
import android.widget.TextView;

import com.mercadolibre.android.mlbusinesscomponents.R;

public class MLBusinessLoyaltyHeaderView extends ConstraintLayout {

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
        inflate(context, R.layout.ml_view_business_loyalty_ring, this);

        initLoyaltyHeaderView();
    }

    private void initLoyaltyHeaderView() {
        progress = findViewById(R.id.loyaltyRing);
        title = findViewById(R.id.title);
        loyaltyLevelInfo = findViewById(R.id.loyaltyLevelInfo);
    }

    private void configLoyaltyHeaderView() {
        updateRing();

        int textColor = Color.parseColor(businessLoyaltyHeaderData.getTextHexaColor());
        title.setText(businessLoyaltyHeaderData.getTitle());
        title.setTextColor(textColor);

        loyaltyLevelInfo.setText(businessLoyaltyHeaderData.getSubtitle());
        loyaltyLevelInfo.setTextColor(textColor);

        this.setBackgroundColor(Color.parseColor(businessLoyaltyHeaderData.getBackgroundHexaColor()));
    }

    private void updateRing() {
        Drawable unwrappedDrawable = AppCompatResources.getDrawable(getContext(), R.drawable.loyalty_progress_background);
        if (unwrappedDrawable != null) {
            Drawable wrappedDrawable = DrawableCompat.wrap(unwrappedDrawable);
            DrawableCompat.setTint(wrappedDrawable, Color.parseColor(businessLoyaltyHeaderData.getRingBackgroundHexaColor()));
        }

        final float percentage = businessLoyaltyHeaderData.getRingPercentage();
        progress.setColorProgress(Color.parseColor(businessLoyaltyHeaderData.getRingHexaColor()));
        progress.setColorText(Color.parseColor(businessLoyaltyHeaderData.getRingHexaColor()));
        progress.setProgress(percentage);
        progress.setAnimation();
        progress.setLoyaltyNumber(businessLoyaltyHeaderData.getRingNumber());
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
