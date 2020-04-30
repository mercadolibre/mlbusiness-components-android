package com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.callback.OnClickCallback;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.TouchpointContent;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.tracking.MLBusinessTouchpointTracker;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.tracking.print.TouchpointPrintProvider;
import java.util.Map;

public abstract class AbstractTouchpointChildView<M extends TouchpointContent> extends FrameLayout {

    @Nullable protected MLBusinessTouchpointTracker tracker;
    @Nullable protected OnClickCallback onClickCallback;
    @Nullable protected Map<String, Object> tracking;
    @Nullable protected TouchpointPrintProvider printProvider;

    /**
     * Constructor
     *
     * @param context the context
     */
    public AbstractTouchpointChildView(@NonNull final Context context) {
        super(context);
    }

    /**
     * Constructor
     *
     * @param context the context
     * @param attrs the attribute set
     */
    public AbstractTouchpointChildView(@NonNull final Context context, @Nullable final AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * Constructor
     *
     * @param context the context
     * @param attrs the attribute set
     * @param defStyleAttr the attribute style
     */
    public AbstractTouchpointChildView(@NonNull final Context context, @Nullable final AttributeSet attrs,
        final int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * Bind model on view
     *
     * @param model A {@link TouchpointContent}
     */
    public abstract void bind(@Nullable M model);

    /**
     * Do print
     */
    public abstract void print();

    public void setTracker(@Nullable final MLBusinessTouchpointTracker tracker) {
        this.tracker = tracker;
    }

    public void setOnClickCallback(@Nullable final OnClickCallback onClickCallback) {
        this.onClickCallback = onClickCallback;
    }

    public void setExtraData(@Nullable final Map<String, Object> tracking) {
        this.tracking = tracking;
    }

    public void setPrintProvider(@Nullable final TouchpointPrintProvider printProvider) {
        this.printProvider = printProvider;
    }
}
