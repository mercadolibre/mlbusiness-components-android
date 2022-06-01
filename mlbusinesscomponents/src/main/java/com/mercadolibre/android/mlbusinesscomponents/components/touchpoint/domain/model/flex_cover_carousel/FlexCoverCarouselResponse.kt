package com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.flex_cover_carousel

import android.os.Parcelable
import androidx.annotation.Keep
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.view.flex_cover_carousel.pill_touchpoint.PillInterface
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.TouchpointContent
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.tracking.TouchpointTrackeable
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.tracking.print.TouchpointTracking
import kotlinx.android.parcel.Parcelize

@Keep
@Parcelize
data class FlexCoverCarouselResponse(
    val items: List<FlexCoverCard>
) : TouchpointContent, Parcelable

@Keep
@Parcelize
data class FlexCoverCard(
    val backgroundColor: String,
    val mainDescription: Text,
    val imageHeader: String,
    val link: String,
    val logos: List<Logo>?,
    val pill: Pill,
    val subtitle: Text,
    val title: Text,
    private val tracking: TouchpointTracking
) : Parcelable, TouchpointTrackeable {
    override fun getTracking(): TouchpointTracking? {
        return tracking
    }
}

@Keep
@Parcelize
data class Text(
    val text: String,
    val textColor: String?
) : Parcelable

@Keep
@Parcelize
data class Logo(
    val image: String,
    val label: Text,
    val style: Style,
    val type: String
) : Parcelable

@Keep
@Parcelize
data class Pill(
    override val text: String,
    override val textColor: String,
    override val backgroundColor: String?,
    override val borderColor: String?
) : Parcelable, PillInterface

@Keep
@Parcelize
data class Style(
    val backgroundColor: String,
    val border: Int,
    val borderColor: String,
    val height: Int,
    val width: Int
) : Parcelable