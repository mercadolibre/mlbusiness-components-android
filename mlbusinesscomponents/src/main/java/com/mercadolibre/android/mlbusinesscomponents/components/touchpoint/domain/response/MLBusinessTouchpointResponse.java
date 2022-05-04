package com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.response;

import androidx.annotation.Keep;
import androidx.annotation.Nullable;
import com.google.gson.JsonElement;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.AdditionalEdgeInsets;
import java.io.Serializable;
import java.util.Map;

@Keep
public class MLBusinessTouchpointResponse implements Serializable {

    private static final long serialVersionUID = -6143525838531474740L;

    public String id;
    public String type;
    @Nullable public JsonElement content;
    @Nullable public Map<String, Object> tracking;
    @Nullable public AdditionalEdgeInsets additional_edge_insets;
}
