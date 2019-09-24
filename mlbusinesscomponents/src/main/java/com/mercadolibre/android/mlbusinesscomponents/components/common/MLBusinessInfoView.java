package com.mercadolibre.android.mlbusinesscomponents.components.common;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.content.res.AppCompatResources;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.widget.TextView;

import com.mercadolibre.android.mlbusinesscomponents.R;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

public class MLBusinessInfoView extends ConstraintLayout {

    private TextView description;
    private AppCompatImageView icon;
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
            DrawableCompat.setTint(wrappedDrawable, Color.parseColor(businessInfoData.getIconBackgroundHexaColor()));
            icon.setBackground(wrappedDrawable);
        }

        description.setText(businessInfoData.getDescription());

        Picasso.with(getContext()).load(businessInfoData.getIcon()).into(icon);
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
