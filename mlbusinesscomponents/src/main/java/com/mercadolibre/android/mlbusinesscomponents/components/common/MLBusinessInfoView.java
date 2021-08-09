package com.mercadolibre.android.mlbusinesscomponents.components.common;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.TextView;
import com.mercadolibre.android.mlbusinesscomponents.R;
import com.mercadolibre.android.picassodiskcache.PicassoDiskLoader;

public class MLBusinessInfoView extends ConstraintLayout {

    private final TextView description;
    private final AppCompatImageView icon;
    private MLBusinessInfoData businessInfoData;

    public MLBusinessInfoView(final Context context) {
        this(context, null);
    }

    public MLBusinessInfoView(final Context context, final AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MLBusinessInfoView(final Context context, final AttributeSet attrs, final int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        inflate(context, R.layout.ml_view_business_info, this);

        description = findViewById(R.id.description);
        icon = findViewById(R.id.icon);
    }

    private void configLoyaltyHeaderView() {
        final Drawable unwrappedDrawable = AppCompatResources.getDrawable(getContext(), R.drawable.info_icon_background);
        if (unwrappedDrawable != null) {
            final Drawable wrappedDrawable = DrawableCompat.wrap(unwrappedDrawable);
            DrawableCompat.setTint(wrappedDrawable, Color.parseColor(businessInfoData.getIconBackgroundHexaColor()));
            icon.setBackground(wrappedDrawable);
            setIconSize();
        }

        description.setText(businessInfoData.getDescription());
        setDescriptionTextSize();

        PicassoDiskLoader.get(getContext()).load(businessInfoData.getIcon()).into(icon);
    }

    private void setDescriptionTextSize() {
        int descriptionTextSize = businessInfoData.getDescriptionSize();
        if (descriptionTextSize != 0) {
            description.setTextSize(TypedValue.COMPLEX_UNIT_SP, descriptionTextSize);
        }
    }

    private void setIconSize() {
        int iconSize = businessInfoData.getIconSize();
        if (iconSize != 0) {
            final float density = getContext().getResources().getDisplayMetrics().density;
            final int valueInDp = (int) (iconSize * density);
            icon.getLayoutParams().height = valueInDp;
            icon.getLayoutParams().width = valueInDp;
        }
    }

    public void init(@NonNull final MLBusinessInfoData businessInfoData) {
        this.businessInfoData = businessInfoData;
        configLoyaltyHeaderView();
    }

    public void updateWithModel(@NonNull final MLBusinessInfoData businessInfoData) {
        init(businessInfoData);
    }
}