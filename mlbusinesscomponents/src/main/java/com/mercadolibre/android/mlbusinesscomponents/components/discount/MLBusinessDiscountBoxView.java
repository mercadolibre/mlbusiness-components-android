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
import com.mercadolibre.android.mlbusinesscomponents.common.MLBusinessSingleItem;
import com.mercadolibre.android.mlbusinesscomponents.components.utils.ScaleUtils;
import java.util.List;

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

    private static final int DEFAULT_LIST_SIZE = 6;

    public MLBusinessDiscountBoxView(final Context context) {
        this(context, null);
    }

    public MLBusinessDiscountBoxView(final Context context, final AttributeSet attrs) {
        this(context, attrs, 0);
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
            new MLBusinessDiscountBoxAdapter(
                getLimitedList(),
                onClickDiscountBox);
        final int totalSize = discountBoxAdapter.getItemCount();
        final int defaultColumns = totalSize == 4 ? 2 : DEFAULT_LIST_SIZE;
        final int span = totalSize % (defaultColumns / 2);

        final GridLayoutManager manager = new GridLayoutManager(getContext(), defaultColumns) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }

            @Override
            public boolean canScrollHorizontally() {
                return false;
            }
        };
        if (defaultColumns == 6) {
            manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(final int position) {
                    if (span == 0 || (position <= ((totalSize - 1) - span))) {
                        return 2;
                    } else if (span == 1) {
                        return DEFAULT_LIST_SIZE;
                    } else {
                        return 3;
                    }
                }
            });
        }

        for (int i = 0; i < recyclerDiscountBox.getItemDecorationCount(); i++) {
            recyclerDiscountBox.removeItemDecorationAt(0);
        }
        recyclerDiscountBox.addItemDecoration(new SpacesItemDecoration(getContext(), span, defaultColumns));
        recyclerDiscountBox.setLayoutManager(manager);
        recyclerDiscountBox.setAdapter(discountBoxAdapter);
        recyclerDiscountBox.setHasFixedSize(true);

        String title = businessDiscountBoxData.getTitle();
        String subTitle = businessDiscountBoxData.getSubtitle();

        if (isValidString(title) && isValidString(subTitle)) {
            titleLabel.setText(title);
            subtitleLabel.setText(subTitle);
        } else {
            titleLabel.setVisibility(View.GONE);
            subtitleLabel.setVisibility(View.GONE);
        }
    }

    private List<MLBusinessSingleItem> getLimitedList() {
        List<MLBusinessSingleItem> items = businessDiscountBoxData.getItems();
        return items.size() > DEFAULT_LIST_SIZE ? items.subList(0, DEFAULT_LIST_SIZE) : items;
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

    public void updateWithData(@NonNull final MLBusinessDiscountBoxData businessDiscountBoxData,
        @Nullable final OnClickDiscountBox onclick) {
        init(businessDiscountBoxData, onclick);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (onClickDiscountBox != null) {
            onClickDiscountBox = null;
        }
    }

    private static class SpacesItemDecoration extends RecyclerView.ItemDecoration {
        private final int topSpace;
        private final int lateralSpace;
        private final int itemsInLastRow;
        private final int defaultColumns;

        SpacesItemDecoration(final Context context, final int itemsInLastRow, final int defaultColumns) {
            this.topSpace = (int) ScaleUtils.getPxFromDp(context, 24);
            this.lateralSpace = (int) ScaleUtils.getPxFromDp(context, 16);
            this.itemsInLastRow = itemsInLastRow;
            this.defaultColumns = defaultColumns;
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
                        outRect.left = view.getLayoutParams().width + lateralSpace * 3;
                    }
                } else {
                    outRect.left = lateralSpace;
                }

                if (isFirstRow(parent, view)) {
                    outRect.top = 0;
                } else {
                    outRect.top = topSpace;
                }

                outRect.right = lateralSpace;
                outRect.bottom = 0;
            }
        }

        private boolean isFirstRow(final RecyclerView parent, final View view) {
            final int columns = defaultColumns == DEFAULT_LIST_SIZE ? 2 : 1;
            return parent.getChildAdapterPosition(view) <= columns;
        }

        private boolean startLastRow(final RecyclerView parent,
            final RecyclerView.Adapter adapter, final View view, final int itemsInLastRow) {
            final int itemCount = adapter.getItemCount();
            return itemCount > 1 &&
                parent.getChildAdapterPosition(view) == itemCount - itemsInLastRow;
        }
    }
}
