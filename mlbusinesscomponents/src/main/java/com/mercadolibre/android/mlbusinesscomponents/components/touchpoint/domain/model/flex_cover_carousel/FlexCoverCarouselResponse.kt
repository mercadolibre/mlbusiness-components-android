package com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.flex_cover_carousel

import androidx.annotation.Keep
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.TouchpointContent
import java.io.Serializable

@Keep
data class FlexCoverCarouselResponse(
    val items: List<FlexCoverCard>
) : TouchpointContent, Serializable

@Keep
data class FlexCoverCard(
    val backgroundColor: String,
    val cornerRadius: Int,
    val mainDescription: Text,
    val hasShadow: Boolean,
    val imageHeader: String,
    val link: String,
    val logos: List<Logo>,
    val pill: Pill,
    val subtitle: Text,
    val title: Text,
    val tracking: Tracking
) : Serializable

@Keep
data class Text(
    val text: String,
    val textColor: String
) : Serializable

@Keep
data class Logo(
    val image: String,
    val label: Text,
    val style: Style,
    val type: String
) : Serializable

@Keep
data class Pill(
    val backgroundColor: String,
    val borderColor: String,
    val text: String,
    val textColor: String
) : Serializable

@Keep
data class Tracking(
    val trackingId: String
) : Serializable

@Keep
data class Style(
    val backgroundColor: String,
    val border: Int,
    val borderColor: String,
    val height: Int,
    val width: Int
) : Serializable