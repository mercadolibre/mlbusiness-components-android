package com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.flex_cover_carousel

import com.mercadolibre.android.mlbusinesscomponents.components.rowpill.PillInterface
import android.os.Parcelable
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.TouchpointContent
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FlexCoverCarouselResponse(
    val items: List<FlexCoverCard>
) : TouchpointContent, Parcelable

@Parcelize
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
) : Parcelable

@Parcelize
data class Text(
    val text: String,
    val textColor: String
) : Parcelable

@Parcelize
data class Logo(
    val image: String,
    val label: Text,
    val style: Style,
    val type: String
) : Parcelable

@Parcelize
data class Pill(
    override val text: String,
    override val textColor: String,
    override val backgroundColor: String?,
    override val leftIcon: String?,
    override val borderColor: String?
) : Parcelable, PillInterface

@Parcelize
data class Tracking(
    val trackingId: String
) : Parcelable

@Parcelize
data class Style(
    val backgroundColor: String,
    val border: Int,
    val borderColor: String,
    val height: Int,
    val width: Int
) : Parcelable