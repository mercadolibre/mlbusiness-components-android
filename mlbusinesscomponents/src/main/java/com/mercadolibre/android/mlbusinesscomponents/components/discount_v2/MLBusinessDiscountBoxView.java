package com.mercadolibre.android.mlbusinesscomponents.components.discount_v2;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.widget.TextView;
import com.mercadolibre.android.mlbusinesscomponents.R;
import com.mercadolibre.android.mlbusinesscomponents.components.discount.MLBusinessDiscountBoxData;
import java.lang.ref.WeakReference;

public class MLBusinessDiscountBoxView extends ConstraintLayout {

    private MLBusinessDiscountBoxData discountBoxData;
    private TextView titleLabel;
    private TextView subtitleLabel;
    private WeakReference<OnClickDiscountBox> onClickDiscountBox;

    public MLBusinessDiscountBoxView(final Context context) {
        this(context, null);
    }

    public MLBusinessDiscountBoxView(final Context context, @Nullable final AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MLBusinessDiscountBoxView(final Context context, @Nullable final AttributeSet attrs,
        final int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initMLBusinessDiscountBoxView(context);
    }

    private void initMLBusinessDiscountBoxView(final Context context) {
        inflate(context, R.layout.ml_view_business_discount_box, this);
        titleLabel = findViewById(R.id.titleLabel);
        subtitleLabel = findViewById(R.id.subtitleLabel);
    }

    public void init(@NonNull final MLBusinessDiscountBoxData discountBoxData,
        @Nullable final OnClickDiscountBox onClick) {
        this.discountBoxData = discountBoxData;
        onClickDiscountBox = new WeakReference<>(onClick);
    }

    public void updateWithData(@NonNull final MLBusinessDiscountBoxData businessDiscountBoxData,
        @Nullable final OnClickDiscountBox onclick) {
        init(businessDiscountBoxData, onclick);
    }
}