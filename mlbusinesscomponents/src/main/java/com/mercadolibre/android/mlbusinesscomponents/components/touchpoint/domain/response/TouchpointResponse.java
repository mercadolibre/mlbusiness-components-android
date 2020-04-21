package com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.response;

import android.support.annotation.Nullable;
import com.google.gson.JsonElement;
import java.io.Serializable;

public class TouchpointResponse implements Serializable {

    private static final long serialVersionUID = -6143525838531474740L;

    public String type;
    @Nullable public JsonElement content;
}
