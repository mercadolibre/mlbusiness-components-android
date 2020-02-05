package com.mercadolibre.android.mlbusinesscomponents.components.discount;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TableRow;
import android.widget.TextView;
import com.mercadolibre.android.mlbusinesscomponents.R;
import com.mercadolibre.android.mlbusinesscomponents.common.MLBusinessSingleItem;
import java.util.List;
import org.jetbrains.annotations.NotNull;

public class MLBusinessDiscountBoxView extends LinearLayout {

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
        inflate(context, R.layout.ml_view_business_discount_box, this);
        initView();
        titleLabel = findViewById(R.id.titleLabel);
        subtitleLabel = findViewById(R.id.subtitleLabel);
        gridBoxView = findViewById(R.id.gridBox);

        presenter = new MLBusinessDiscountBoxPresenter();
    }

    private void initView() {
        setOrientation(VERTICAL);
        setGravity(Gravity.CENTER);
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
        gridBoxView.removeAllViews();
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

    /* default */ void setRawCount(final int rawCount) {
        gridBoxView.setRowCount(rawCount);
    }

    /* default */ void showRowWithItems(final List<MLBusinessSingleItem> items, int startIndex,
        @Nullable final OnClickDiscountBox listener, @Nullable final MLBusinessDiscountTracker discountTracker) {
        final LinearLayout rowView = getRowView();
        for (final MLBusinessSingleItem item : items) {
            final MLBusinessDiscountBoxItemView itemView = new MLBusinessDiscountBoxItemView(getContext());
            itemView.bind(item, listener, startIndex, discountTracker);
            rowView.addView(itemView);
            startIndex++;
        }
        gridBoxView.addView(rowView);
    }

    @NotNull
    private LinearLayout getRowView() {
        final LinearLayout rowView = new LinearLayout(getContext());
        rowView.setLayoutParams(
            new TableRow.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        rowView.setVisibility(VISIBLE);
        rowView.setOrientation(LinearLayout.HORIZONTAL);
        return rowView;
    }

    public interface OnClickDiscountBox {

        /**
         * On click discount item callback
         *
         * @param index The position which one is draw
         * @param deepLink The desiree link to lunch
         * @param trackId The item identifier to be tracked
         */
        void onClickDiscountItem(final int index, @Nullable final String deepLink, @Nullable final String trackId);
    }
}