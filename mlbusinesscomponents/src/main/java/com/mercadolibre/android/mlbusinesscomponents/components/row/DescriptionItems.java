package com.mercadolibre.android.mlbusinesscomponents.components.row;

import android.support.annotation.Keep;
import java.io.Serializable;

@Keep
public class DescriptionItems implements Serializable {

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

    public String getType() {
        return type;
    }

    public String getContent() {
        return content;
    }

    public String getColor() {
        return color;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DescriptionItems)) {
            return false;
        }

        final DescriptionItems that = (DescriptionItems) o;

        if (type != null ? !type.equals(that.type) : that.type != null) {
            return false;
        }
        if (content != null ? !content.equals(that.content) : that.content != null) {
            return false;
        }
        return color != null ? color.equals(that.color) : that.color == null;
    }

    @Override
    public int hashCode() {
        int result = type != null ? type.hashCode() : 0;
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (color != null ? color.hashCode() : 0);
        return result;
    }
}
