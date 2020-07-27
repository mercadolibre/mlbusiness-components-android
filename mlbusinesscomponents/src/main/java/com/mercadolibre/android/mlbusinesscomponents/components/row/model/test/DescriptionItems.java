package com.mercadolibre.android.mlbusinesscomponents.components.row.model.test;

import android.support.annotation.Keep;
import com.mercadolibre.android.mlbusinesscomponents.components.row.model.DescriptionItemsInterface;
import java.io.Serializable;

@Keep
public class DescriptionItems implements DescriptionItemsInterface {

    private final String type;
    private final String content;
    private final String color;

    /**
     * Constructor
     *
     * @param type the type of item.
     * @param content the content of the item.
     * @param color the color of the imte.
     */
    public DescriptionItems(final String type, final String content, final String color) {
        this.type = type;
        this.content = content;
        this.color = color;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public String getContent() {
        return content;
    }

    @Override
    public String getColor() {
        return color;
    }

}
