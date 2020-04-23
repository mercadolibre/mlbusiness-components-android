package com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.view;

import android.content.Context;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.TouchpointContent;

public interface TouchpointViewFactory<M extends TouchpointContent> {

    /**
     * Creates the desired touchpoint view
     *
     * @param context the context
     * @return the instance of the view
     */
    AbstractTouchpointChildView<M> create(final Context context);
}
