package com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.view.hybrid_carousel.view_more.card;

import android.support.annotation.Nullable;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.hybrid_carousel.model.HybridCarouselCardContainerModel;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.hybrid_carousel.model.view_more.HybridCarouselViewMoreCard;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.hybrid_carousel.model.view_more.ViewMoreMainTitle;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.tracking.print.TouchpointTracking;

public class HybridViewMoreCardPresenter {

    private final HybridCarouselViewMoreCardView view;

    /**
     * Constructor
     * @param view the view.
     */
    public HybridViewMoreCardPresenter(final HybridCarouselViewMoreCardView view) {
        this.view = view;
    }

    /**
     * binds the view
     * @param model data to bind
     */
    public void bindView(final HybridCarouselCardContainerModel model) {
        final HybridCarouselViewMoreCard data = setData(model);

        if (model == null || data == null) {
            hideView();
            return;
        }

        setOnClickListener(model.getLink(), model.getTracking());
        setImage(data.getMainImage());
        setTitle(data.getMainTitle());

        view.showView();
    }

    private void setOnClickListener(final String link, @Nullable final TouchpointTracking tracking) {
        if(link == null || !link.isEmpty()) {
            view.setOnClick(link, tracking);
        } else {
            view.dismissClickable();
        }
    }

    private void setImage(final String mainImage) {
        if(mainImage != null && !mainImage.isEmpty()){
            view.setImage(mainImage);
        } else {
            view.hideImage();
        }
    }

    private void setTitle(final ViewMoreMainTitle mainTitle) {
        if(mainTitle.isValid()) {
            view.setMiddleTitle(mainTitle.getLabel(), mainTitle.getFormat());
        } else {
            view.hideTitle();
        }
    }

    private HybridCarouselViewMoreCard setData(final HybridCarouselCardContainerModel model) {
        HybridCarouselViewMoreCard card = null;

        try{
            card = (HybridCarouselViewMoreCard) model.getContent();
        } catch (Exception e) {
            // no op..
        }

        return card;
    }

    private void hideView() {
        view.hideView();
    }
}
