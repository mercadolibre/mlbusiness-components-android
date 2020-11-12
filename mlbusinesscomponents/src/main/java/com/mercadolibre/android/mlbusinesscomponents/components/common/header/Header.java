package com.mercadolibre.android.mlbusinesscomponents.components.common.header;

import androidx.annotation.Keep;
import com.mercadolibre.android.mlbusinesscomponents.components.common.action.Action;
import com.mercadolibre.android.mlbusinesscomponents.components.common.action.ActionInterface;
import java.io.Serializable;

@Keep
public class Header implements Serializable, HeaderInterface {

    private final String title;
    private final Action action;

    public Header(final String title, final Action action) {
        this.title = title;
        this.action = action;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public ActionInterface getAction() {
        return action;
    }
}
