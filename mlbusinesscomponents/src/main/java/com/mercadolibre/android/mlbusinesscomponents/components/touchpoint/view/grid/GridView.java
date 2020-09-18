package com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.view.grid;

import android.content.Context;
import androidx.annotation.Nullable;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TableRow;
import com.mercadolibre.android.mlbusinesscomponents.R;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.grid.Grid;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.grid.GridItem;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.tracking.TouchpointTrackeable;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.view.AbstractTouchpointChildView;
import com.mercadolibre.android.mlbusinesscomponents.components.utils.ScaleUtils;
import java.util.ArrayList;
import java.util.List;
import org.jetbrains.annotations.NotNull;

import static com.mercadolibre.android.mlbusinesscomponents.components.utils.TrackingUtils.trackPrint;
import static com.mercadolibre.android.mlbusinesscomponents.components.utils.TrackingUtils.trackShow;

public class GridView extends AbstractTouchpointChildView<Grid> {

    private final GridLayout gridLayout;
    private final GridPresenter presenter;
    private List<TouchpointTrackeable> trackeables;

    /**
     * Constructor
     *
     * @param context the context
     */
    public GridView(final Context context) {
        this(context, null);
    }

    /**
     * Constructor
     *
     * @param context the context
     * @param attrs the attribute set
     */
    public GridView(final Context context, @Nullable final AttributeSet attrs) {
        this(context, attrs, 0);
    }

    /**
     * Constructor
     *
     * @param context the context
     * @param attrs the attribute set
     * @param defStyleAttr the attribute style
     */
    public GridView(final Context context, @Nullable final AttributeSet attrs, final int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inflate(context, R.layout.touchpoint_grid_view, this);
        gridLayout = findViewById(R.id.grid_layout);
        presenter = new GridPresenter();
    }

    @Override
    public void bind(final Grid model) {
        gridLayout.removeAllViews();
        presenter.bind(model, this);
        trackeables = new ArrayList<>(model.getItems());
        trackShow(tracker, trackeables);
        decorate();
    }

    private void decorate() {
        if (additionalInsets != null) {
            setPadding((int) ScaleUtils.getPxFromDp(getContext(), additionalInsets.getLeft()),
                (int) ScaleUtils.getPxFromDp(getContext(), additionalInsets.getTop()),
                (int) ScaleUtils.getPxFromDp(getContext(), additionalInsets.getRight()),
                (int) ScaleUtils.getPxFromDp(getContext(), additionalInsets.getBottom()));
        }
    }

    @Override
    public void print() {
        for (final TouchpointTrackeable trackeable : trackeables) {
            printProvider.accumulateData(trackeable.getTracking());
        }
        trackPrint(tracker, printProvider);
    }

    /* default */ void setRawCount(final int rawCount) {
        gridLayout.setRowCount(rawCount);
    }

    /* default */ void showRowWithItems(final List<GridItem> items) {
        final LinearLayout rowView = getRowView();
        for (final GridItem item : items) {
            final GridItemView itemView = new GridItemView(getContext());
            itemView.bind(item, onClickCallback, tracker, tracking, isMPInstalled);
            rowView.addView(itemView);
        }
        gridLayout.addView(rowView);
    }

    @NotNull
    private LinearLayout getRowView() {
        final LinearLayout rowView = new LinearLayout(getContext());
        rowView.setLayoutParams(
            new TableRow.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        rowView.setVisibility(VISIBLE);
        rowView.setOrientation(LinearLayout.HORIZONTAL);
        return rowView;
    }

    @Override
    public int getStaticHeight() {
        return presenter.getGridSize() == 2 ? getResources().getDimensionPixelSize(R.dimen.large_grid_static_height)
            : getResources().getDimensionPixelSize(R.dimen.small_grid_static_height);
    }
}
