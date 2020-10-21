package com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.hybrid_carousel.response;

import androidx.annotation.Keep;
import androidx.annotation.Nullable;
import com.google.gson.JsonElement;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.tracking.print.TouchpointTracking;
import java.io.Serializable;
import java.util.Objects;

@Keep
public class HybridCarouselCardContainer implements Serializable {

    private final String type;
    private final String link;
    private final TouchpointTracking tracking;
    @Nullable public JsonElement content;

    public HybridCarouselCardContainer(final String type, final String link,
        final TouchpointTracking tracking, @Nullable final JsonElement content) {
        this.type = type;
        this.link = link;
        this.tracking = tracking;
        this.content = content;
    }

    public String getType() {
        return type;
    }

    public String getLink() {
        return link;
    }

    public TouchpointTracking getTracking() {
        return tracking;
    }

    @Nullable
    public JsonElement getContent() {
        return content;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        final HybridCarouselCardContainer that = (HybridCarouselCardContainer) o;
        return Objects.equals(type, that.type)
            && Objects.equals(link, that.link)
            && content == null ? that.content == null
            : (that.content != null && content.toString().equals(that.content.toString()));
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, link, content);
    }
}
