package com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.view.cover_carousel.cover_card;

import androidx.annotation.Nullable;
import com.mercadolibre.android.mlbusinesscomponents.components.row.model.TouchpointRowItemInterface;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.callback.OnClickCallback;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.cover_carousel.model.cover_card.CoverCardInterfaceModel;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.tracking.print.TouchpointTracking;

public interface CoverCardInterfaceView {

    void bind(final CoverCardInterfaceModel model);

    void bind(final CoverCardInterfaceModel model, final int size);

    void setRow(final TouchpointRowItemInterface description);

    void setCoverImage(final String cover);

    void setOnClick(final String link);

    void dismissClickable();

    void setOnClickCallback(@Nullable final OnClickCallback onClickCallback);

    int getCoverCardHeight();

    void showSkeleton();

    void hideSkeleton();

    boolean getSkeletonState();

    void setOnClickListenerWithAnimationAndLink(final String link, final TouchpointTracking tracking);

    void setTracking(@Nullable final TouchpointTracking tracking);

    CoverCardView getView();

    void setTopImageToClosedtStatus();

    void setTopImageToDefaultStatus();
}
