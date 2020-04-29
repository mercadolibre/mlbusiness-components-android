package com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.view.grid;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TableRow;
import android.widget.TextView;
import com.mercadolibre.android.mlbusinesscomponents.R;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.grid.Grid;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.grid.GridItem;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.tracking.MLBusinessTouchpointTracker;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.tracking.print.TouchpointPrintProvider;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.view.AbstractTouchpointChildView;
import com.mercadolibre.android.mlbusinesscomponents.components.utils.TrackingUtils;
import java.util.List;
import org.jetbrains.annotations.NotNull;

import static com.mercadolibre.android.mlbusinesscomponents.components.utils.TrackingUtils.SHOW;

public class GridView extends AbstractTouchpointChildView<Grid> {

    private final TextView title;
    private final TextView subtitle;
    private final GridLayout gridLayout;
    private final GridPresenter presenter;

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
        title = findViewById(R.id.grid_title);
        subtitle = findViewById(R.id.grid_subtitle);
        gridLayout = findViewById(R.id.grid_layout);
        presenter = new GridPresenter();
    }

    @Override
    public void bind(final Grid model) {
        gridLayout.removeAllViews();
        presenter.bind(model, this);
        trackShowEvent(model.getItems());
    }

    @Override
    public void print(final TouchpointPrintProvider printProvider, final MLBusinessTouchpointTracker tracker) {
        //TODO
    }

    private void trackShowEvent(final List<GridItem> items) {
        if (tracker != null) {
            tracker.track(SHOW, TrackingUtils.retrieveGridItemsDataToTrack(items));
        }
    }

    /* default */ void showTitle(final String title) {
        this.title.setText(title);
        this.title.setVisibility(VISIBLE);
    }

    /* default */ void hideTitle() {
        title.setVisibility(GONE);
    }

    /* default */ void showSubtitle(final String subtitle) {
        this.subtitle.setText(subtitle);
        this.subtitle.setVisibility(VISIBLE);
    }

    /* default */ void hideSubtitle() {
        subtitle.setVisibility(GONE);
    }

    /* default */ void setRawCount(final int rawCount) {
        gridLayout.setRowCount(rawCount);
    }

    /* default */ void showRowWithItems(final List<GridItem> items) {
        final LinearLayout rowView = getRowView();
        for (final GridItem item : items) {
            final GridItemView itemView = new GridItemView(getContext());
            itemView.bind(item, onClickCallback, tracker);
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
}