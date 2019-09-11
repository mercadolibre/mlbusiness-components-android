package com.mercadolibre.android.mlbusinesscomponents.components.discount;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;
import com.mercadolibre.android.mlbusinesscomponents.R;

import static com.mercadolibre.android.mlbusinesscomponents.components.utils.StringUtils.isValidString;

public class MLBusinessDiscountBoxView extends ConstraintLayout {

    public interface OnClickDiscountBox {
        void onClickDiscountItem(final int index, @Nullable final String deepLink,
            @Nullable final String trackId);
    }

    private RecyclerView recyclerDiscountBox;
    private GridLayoutManager manager;

    private MLBusinessDiscountBoxData businessDiscountBoxData;
    private TextView titleLabel;
    private TextView subtitleLabel;
    private OnClickDiscountBox onClickDiscountBox;

    public MLBusinessDiscountBoxView(final Context context) {
        super(context);
        initMLBusinessDiscountBoxView(context);
    }

    public MLBusinessDiscountBoxView(final Context context, final AttributeSet attrs) {
        super(context, attrs);
        initMLBusinessDiscountBoxView(context);
    }

    public MLBusinessDiscountBoxView(final Context context, final AttributeSet attrs,
        final int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initMLBusinessDiscountBoxView(context);
    }

    private void initMLBusinessDiscountBoxView(final Context context) {
        inflate(context, R.layout.ml_view_business_discount_box, this);
        final int DEFAULT_COLUMNS = 6;

        recyclerDiscountBox = findViewById(R.id.recyclerDiscountBox);
        manager = new GridLayoutManager(context, DEFAULT_COLUMNS) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }

            @Override
            public boolean canScrollHorizontally() {
                return false;
            }
        };
        recyclerDiscountBox.setLayoutManager(manager);
        titleLabel = findViewById(R.id.titleLabel);
        subtitleLabel = findViewById(R.id.subtitleLabel);
    }

    private void configDiscountBoxView() {
        final MLBusinessDiscountBoxAdapter discountBoxAdapter =
            new MLBusinessDiscountBoxAdapter(businessDiscountBoxData.getItems(),
                onClickDiscountBox);

        final int totalSize = discountBoxAdapter.getItemCount();
        final int span = totalSize % 3;
        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(final int position) {
                if (span == 0 || (position <= ((totalSize - 1) - span))) {
                    return 2;
                } else if (span == 1) {
                    return 6;
                } else {
                    return 3;
                }
            }
        });

        recyclerDiscountBox.setAdapter(discountBoxAdapter);
        recyclerDiscountBox.setHasFixedSize(true);

        String title = businessDiscountBoxData.getTitle();
        String subTitle = businessDiscountBoxData.getSubtitle();

        if (isValidString(title)) {
            titleLabel.setText(title);
        } else {
            titleLabel.setVisibility(View.GONE);
        }
        if (isValidString(subTitle)) {
            subtitleLabel.setText(subTitle);
        } else {
            subtitleLabel.setVisibility(View.GONE);
        }
    }

    public void init(@NonNull final MLBusinessDiscountBoxData businessDiscountBoxData,
        @Nullable final OnClickDiscountBox onclick) {
        this.businessDiscountBoxData = businessDiscountBoxData;
        this.onClickDiscountBox = onclick;
        configDiscountBoxView();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (onClickDiscountBox != null) {
            onClickDiscountBox = null;
        }
    }
}
