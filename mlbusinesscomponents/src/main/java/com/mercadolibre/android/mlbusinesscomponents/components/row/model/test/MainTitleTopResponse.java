package com.mercadolibre.android.mlbusinesscomponents.components.row.model.test;

import androidx.annotation.Keep;

import com.mercadolibre.android.mlbusinesscomponents.components.row.model.MainTitleTopInterface;

@Keep
public class MainTitleTopResponse implements MainTitleTopInterface {

    private final String text;
    private final String color;

    /**
     * Constructor
     *
     * @param text the text of the main title top.
     * @param color the color of the main title top.
     */
    public MainTitleTopResponse(final String text, final String color) {
        this.text = text;
        this.color = color;
    }

    @Override
    public String getText() { return text; }

    @Override
    public String getColor() { return color; }
}
