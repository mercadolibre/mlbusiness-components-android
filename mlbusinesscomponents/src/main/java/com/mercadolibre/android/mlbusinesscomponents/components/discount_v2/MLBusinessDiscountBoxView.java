package com.mercadolibre.android.mlbusinesscomponents.components.discount_v2;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.widget.GridLayout;
import android.widget.TextView;
import com.mercadolibre.android.mlbusinesscomponents.R;
import com.mercadolibre.android.mlbusinesscomponents.common.MLBusinessSingleItem;
import java.util.List;

public class MLBusinessDiscountBoxView extends ConstraintLayout {

    private final TextView titleLabel;
    private final TextView subtitleLabel;
    private final GridLayout gridBoxView;
    private final MLBusinessDiscountBoxPresenter presenter;

    /**
     * Constructor
     *
     * @param context the context
     */
    public MLBusinessDiscountBoxView(final Context context) {
        this(context, null);
    }

    /**
     * Constructor
     *
     * @param context the context
     * @param attrs the attribute set
     */
    public MLBusinessDiscountBoxView(final Context context, @Nullable final AttributeSet attrs) {
        this(context, attrs, 0);
    }

    /**
     * Constructor
     *
     * @param context the context
     * @param attrs the attribute set
     * @param defStyleAttr the attribute style
     */
    public MLBusinessDiscountBoxView(final Context context, @Nullable final AttributeSet attrs,
        final int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inflate(context, R.layout.ml_view_business_discount_box_v2, this);
        titleLabel = findViewById(R.id.titleLabel);
        subtitleLabel = findViewById(R.id.subtitleLabel);
        gridBoxView = findViewById(R.id.gridBox);

        presenter = new MLBusinessDiscountBoxPresenter();
    }

    /**
     * Bind discount component
     *
     * @param discountBoxData A {@link MLBusinessDiscountBoxData} to be bind
     * @param listener A {@link OnClickDiscountBox} callback to be called on {@link MLBusinessSingleItem} click event
     */
    public void init(@NonNull final MLBusinessDiscountBoxData discountBoxData,
        @Nullable final OnClickDiscountBox listener) {
        presenter.bind(discountBoxData, listener, this);
    }

    /**
     * Re bind model with new model
     *
     * @param discountBoxData A new {@link MLBusinessDiscountBoxData} to bind
     * @param listener A {@link OnClickDiscountBox} callback to be called on {@link MLBusinessSingleItem} click event
     */
    public void updateWithData(@NonNull final MLBusinessDiscountBoxData discountBoxData,
        @Nullable final OnClickDiscountBox listener) {
        init(discountBoxData, listener);
    }

    /* default */ void showTitle(final String title) {
        titleLabel.setText(title);
        titleLabel.setVisibility(VISIBLE);
    }

    /* default */ void hideTitle() {
        titleLabel.setVisibility(GONE);
    }

    /* default */ void showSubtitle(final String subtitle) {
        subtitleLabel.setText(subtitle);
        titleLabel.setVisibility(VISIBLE);
    }

    /* default */ void hideSubtitle() {
        titleLabel.setVisibility(GONE);
    }

    /* default */ void setColumnsCount(final int columnsCount) {
        gridBoxView.setColumnCount(columnsCount);
    }

    /* default */ void showRowWithItems(final List<MLBusinessSingleItem> items,
        @Nullable final OnClickDiscountBox listener, int startIndex) {
        for (final MLBusinessSingleItem item : items) {
            final MLBusinessDiscountBoxItemView itemView = new MLBusinessDiscountBoxItemView(getContext());
            itemView.bind(item, listener, startIndex);
            gridBoxView.addView(itemView);
            startIndex++;
        }
    }
}