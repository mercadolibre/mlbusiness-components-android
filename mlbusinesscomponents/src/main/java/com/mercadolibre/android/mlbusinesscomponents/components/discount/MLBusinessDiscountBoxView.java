package com.mercadolibre.android.mlbusinesscomponents.components.discount;

import android.content.Context;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;
import com.mercadolibre.android.mlbusinesscomponents.R;
import com.mercadolibre.android.mlbusinesscomponents.components.utils.ScaleUtils;

import static com.mercadolibre.android.mlbusinesscomponents.components.utils.StringUtils.isValidString;

public class MLBusinessDiscountBoxView extends ConstraintLayout {

    public interface OnClickDiscountBox {
        void onClickDiscountItem(final int index, @Nullable final String deepLink,
            @Nullable final String trackId);
    }

    private RecyclerView recyclerDiscountBox;

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

        recyclerDiscountBox = findViewById(R.id.recyclerDiscountBox);
        titleLabel = findViewById(R.id.titleLabel);
        subtitleLabel = findViewById(R.id.subtitleLabel);
    }

    private void configDiscountBoxView() {
        final MLBusinessDiscountBoxAdapter discountBoxAdapter =
            new MLBusinessDiscountBoxAdapter(businessDiscountBoxData.getItems(),
                onClickDiscountBox);
        final int totalSize = discountBoxAdapter.getItemCount();
        final int DEFAULT_COLUMNS = totalSize == 4 ? 2 : 6;
        final int span = totalSize % (DEFAULT_COLUMNS / 2);

        final GridLayoutManager manager = new GridLayoutManager(getContext(), DEFAULT_COLUMNS) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }

            @Override
            public boolean canScrollHorizontally() {
                return false;
            }
        };
        if (DEFAULT_COLUMNS == 6) {
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
        }

        recyclerDiscountBox.addItemDecoration(new SpacesItemDecoration(getContext(), span));
        recyclerDiscountBox.setLayoutManager(manager);
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

    public void updateWithData(@NonNull final MLBusinessDiscountBoxData businessDiscountBoxData) {
        init(businessDiscountBoxData, this.onClickDiscountBox);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (onClickDiscountBox != null) {
            onClickDiscountBox = null;
        }
    }

    private static class SpacesItemDecoration extends RecyclerView.ItemDecoration {
        private int topSpace;
        private int lateralSpace;
        private int itemsInLastRow;

        SpacesItemDecoration(final Context context, int itemsInLastRow) {
            this.topSpace = (int) ScaleUtils.getPxFromDp(context, 24);
            this.lateralSpace = (int) ScaleUtils.getPxFromDp(context, 8);
            this.itemsInLastRow = itemsInLastRow;
        }

        @Override
        public void getItemOffsets(@NonNull Rect outRect, @NonNull View view,
            @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
            RecyclerView.Adapter adapter = parent.getAdapter();
            if (adapter != null) {
                if (itemsInLastRow > 0 &&
                    startLastRow(parent, adapter, view, itemsInLastRow)) {
                    if (itemsInLastRow == 2) {
                        outRect.left = (view.getLayoutParams().width + lateralSpace * 4) / 2;
                    } else {
                        outRect.left = (view.getLayoutParams().width) + lateralSpace * 3;
                    }
                } else {
                    outRect.left = lateralSpace;
                }

                outRect.right = lateralSpace;
                outRect.bottom = 0;
                outRect.top = topSpace;
            }
        }

        private boolean startLastRow(final RecyclerView parent,
            final RecyclerView.Adapter adapter, final View view, final int itemsInLastRow) {
            final int itemCount = adapter.getItemCount();
            return itemCount > 1 &&
                parent.getChildAdapterPosition(view) == itemCount - itemsInLastRow;
        }
    }
}
