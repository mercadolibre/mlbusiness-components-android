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
    val background_color: String,
    val corner_radius: Int,
    val description: Description,
    val has_shadow: Boolean,
    val image_header: String,
    val link: String,
    val logos: List<Logo>,
    val pill: Pill,
    val subtitle: Subtitle,
    val title: Title,
    val tracking: Tracking
) : Serializable

@Keep
data class Description(
    val text: String,
    val text_color: String
) : Serializable

@Keep
data class Logo(
    val image: String,
    val label: Label,
    val style: Style,
    val type: String
) : Serializable

@Keep
data class Pill(
    val background_color: String,
    val border_color: String,
    val text: String,
    val text_color: String
) : Serializable

@Keep
data class Subtitle(
    val text: String,
    val text_color: String
) : Serializable

@Keep
data class Title(
    val text: String,
    val text_color: String
) : Serializable

@Keep
data class Tracking(
    val tracking_id: String
) : Serializable

@Keep
data class Label(
    val text: String,
    val text_color: String
) : Serializable

@Keep
data class Style(
    val background_color: String,
    val border: Int,
    val border_color: String,
    val height: Int,
    val width: Int
) : Serializable