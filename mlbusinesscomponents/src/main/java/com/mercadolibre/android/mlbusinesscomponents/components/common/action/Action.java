package com.mercadolibre.android.mlbusinesscomponents.components.common.action;

public class Action implements ActionInterface {

    private final String title;
    private final String link;

    public Action(final String title, final String link) {
        this.title = title;
        this.link = link;
    }

    @Override
    public String getTitle() {
        return null;
    }

    @Override
    public String getLink() {
        return null;
    }
}
