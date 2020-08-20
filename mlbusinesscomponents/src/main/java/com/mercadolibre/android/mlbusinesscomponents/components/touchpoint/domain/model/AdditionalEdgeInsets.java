package com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model;

import androidx.annotation.Keep;
import java.io.Serializable;

@Keep
public class AdditionalEdgeInsets implements Serializable {

    private static final long serialVersionUID = -1355285710958843901L;

    private final int top;
    private final int left;
    private final int bottom;
    private final int right;

    /**
     * Constructor
     *
     * @param top the additional top inset in pixels
     * @param left the additional left inset in pixels
     * @param bottom the additional bottom inset in pixels
     * @param right the additional right inset in pixels
     */
    public AdditionalEdgeInsets(final int top, final int left, final int bottom, final int right) {
        this.top = top;
        this.left = left;
        this.bottom = bottom;
        this.right = right;
    }

    public int getTop() {
        return top;
    }

    public int getLeft() {
        return left;
    }

    public int getBottom() {
        return bottom;
    }

    public int getRight() {
        return right;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || !getClass().equals(o.getClass())) {
            return false;
        }

        final AdditionalEdgeInsets that = (AdditionalEdgeInsets) o;

        if (getTop() != that.getTop()) {
            return false;
        }
        if (getLeft() != that.getLeft()) {
            return false;
        }
        if (getBottom() != that.getBottom()) {
            return false;
        }
        return getRight() == that.getRight();
    }

    @Override
    public int hashCode() {
        int result = getTop();
        result = 31 * result + getLeft();
        result = 31 * result + getBottom();
        result = 31 * result + getRight();
        return result;
    }
}
