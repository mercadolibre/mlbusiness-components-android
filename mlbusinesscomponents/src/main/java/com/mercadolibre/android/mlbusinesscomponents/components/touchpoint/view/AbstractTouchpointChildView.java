package com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.callback.OnClickCallback;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.TouchpointContent;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.tracking.MLBusinessTouchpointTracker;

public abstract class AbstractTouchpointChildView<M extends TouchpointContent> extends FrameLayout {

    @Nullable protected MLBusinessTouchpointTracker tracker;
    @Nullable protected OnClickCallback onClickCallback;

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

    public void setTracker(@Nullable final MLBusinessTouchpointTracker tracker) {
        this.tracker = tracker;
    }

    public void setOnClickCallback(@Nullable final OnClickCallback onClickCallback) {
        this.onClickCallback = onClickCallback;
    }
}
