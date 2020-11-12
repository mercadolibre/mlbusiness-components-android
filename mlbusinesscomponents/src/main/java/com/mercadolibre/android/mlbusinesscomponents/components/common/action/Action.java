package com.mercadolibre.android.mlbusinesscomponents.components.common.action;

import androidx.annotation.Keep;

@Keep
public class Action implements ActionInterface {

    private final String title;
    private final String link;

    public Action(final String title, final String link) {
        this.title = title;
        this.link = link;
    }

    @Override
    public String getActionTitle() {
        return title;
    }

    @Override
    public String getActionLink() {
        return link;
    }
}
