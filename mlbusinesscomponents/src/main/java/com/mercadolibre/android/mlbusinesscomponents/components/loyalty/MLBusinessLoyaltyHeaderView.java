package com.mercadolibre.android.mlbusinesscomponents.components.loyalty;

import android.content.Context;
import android.graphics.Color;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mercadolibre.android.mlbusinesscomponents.R;

public class MLBusinessLoyaltyHeaderView extends ConstraintLayout {

    private final ViewGroup containerView;
    private final LoyaltyProgress progress;
    private final TextView title;
    private final TextView subtitleInfo;
    private MLBusinessLoyaltyHeaderData businessLoyaltyHeaderData;

    public MLBusinessLoyaltyHeaderView(final Context context) {
        this(context, null);
    }

    public MLBusinessLoyaltyHeaderView(final Context context, final AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MLBusinessLoyaltyHeaderView(final Context context, final AttributeSet attrs, final int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        inflate(context, R.layout.ml_view_business_loyalty_header, this);

        containerView = findViewById(R.id.containerView);
        progress = findViewById(R.id.loyaltyRing);
        title = findViewById(R.id.loyaltyTitle);
        subtitleInfo = findViewById(R.id.subtitleInfo);
    }

    private void configLoyaltyHeaderView() {
        if (businessLoyaltyHeaderData.getRingNumber() == 0) {
            progress.setVisibility(View.GONE);
        } else {
            updateRing();
        }

        setTitles();
        containerView.setBackgroundColor(Color.parseColor(businessLoyaltyHeaderData.getBackgroundHexaColor()));
    }

    private void setTitles() {
        final int textColor = Color.parseColor(businessLoyaltyHeaderData.getPrimaryHexaColor());

        if (businessLoyaltyHeaderData.getTitle() != null) {
            title.setText(businessLoyaltyHeaderData.getTitle());
            title.setTextColor(textColor);
        } else {
            title.setVisibility(View.GONE);
            if (businessLoyaltyHeaderData.getSubtitle() != null) {
                setSubtitleConstraintsWhenTitleIsEmpty();
            }
        }

        if (businessLoyaltyHeaderData.getSubtitle() != null) {
            subtitleInfo.setText(businessLoyaltyHeaderData.getSubtitle());
            subtitleInfo.setTextColor(textColor);
        }

    }

    private void setSubtitleConstraintsWhenTitleIsEmpty() {
        int subtitleId = R.id.subtitleInfo;
        int containerId = R.id.containerView;
        ConstraintLayout constraintLayout = findViewById(containerId);
        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(constraintLayout);
        constraintSet.connect(subtitleId, ConstraintSet.END, containerId, ConstraintSet.END, 0);
        constraintSet.connect(subtitleId, ConstraintSet.TOP, containerId, ConstraintSet.TOP, 0);
        constraintSet.connect(subtitleId, ConstraintSet.START, containerId, ConstraintSet.START, 0);
        constraintSet.connect(subtitleId, ConstraintSet.BOTTOM, containerId, ConstraintSet.BOTTOM, 0);
        constraintSet.applyTo(constraintLayout);
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

    public void init(@NonNull final MLBusinessLoyaltyHeaderData businessLoyaltyHeaderData) {
        this.businessLoyaltyHeaderData = businessLoyaltyHeaderData;
        configLoyaltyHeaderView();
    }

    public void updateWithModel(@NonNull final MLBusinessLoyaltyHeaderData businessLoyaltyHeaderData) {
        init(businessLoyaltyHeaderData);
    }
}