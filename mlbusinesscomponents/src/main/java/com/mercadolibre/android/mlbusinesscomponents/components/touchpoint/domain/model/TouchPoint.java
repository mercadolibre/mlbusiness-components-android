package com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model;

import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.TouchpointRegistry;

public class TouchPoint {

    private final TouchpointRegistry type;
    private final TouchpointContent content;

    /**
     * Constructor
     *
     * @param type A {@link TouchpointRegistry}
     * @param content A {@link TouchpointContent}
     */
    public TouchPoint(final TouchpointRegistry type, final TouchpointContent content) {
        this.type = type;
        this.content = content;
    }

    public TouchpointRegistry getType() {
        return type;
    }

    public TouchpointContent getContent() {
        return content;
    }
}
