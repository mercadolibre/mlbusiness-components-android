package com.mercadolibre.android.mlbusinesscomponents.components.common;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.content.res.AppCompatResources;
import android.util.AttributeSet;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.mercadolibre.android.mlbusinesscomponents.R;

public class MLBusinessInfoView extends ConstraintLayout {

    private TextView description;
    private SimpleDraweeView icon;
    private MLBusinessInfoData businessInfoData;

    public MLBusinessInfoView(Context context) {
        super(context);
        initMLBusinessInfoView(context);
    }

    public MLBusinessInfoView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initMLBusinessInfoView(context);
    }

    public MLBusinessInfoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initMLBusinessInfoView(context);
    }

    private void initMLBusinessInfoView(final Context context) {
        inflate(context, R.layout.ml_view_business_info, this);

        initInfoView();
    }

    private void initInfoView() {
        description = findViewById(R.id.description);
        icon = findViewById(R.id.icon);
    }

    private void configLoyaltyHeaderView() {
        Drawable unwrappedDrawable = AppCompatResources.getDrawable(getContext(), R.drawable.info_icon_background);
        if (unwrappedDrawable != null) {
            Drawable wrappedDrawable = DrawableCompat.wrap(unwrappedDrawable);
            DrawableCompat.setTint(wrappedDrawable, Color.parseColor(businessInfoData.getBackgroundHexaColor()));
        }

        description.setText(businessInfoData.getDescription());

        icon.setImageURI(businessInfoData.getIcon());
    }

    public void init(
            @NonNull final MLBusinessInfoData businessInfoData) {
        this.businessInfoData = businessInfoData;
        configLoyaltyHeaderView();
    }

    public void updateWithModel(
            @NonNull final MLBusinessInfoData businessInfoData) {
        init(businessInfoData);
    }
}
