package com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.view.cover_carousel;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.PagerAdapter;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.callback.OnClickCallback;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.cover_carousel.model.cover_card.CoverCardInterface;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.view.cover_carousel.cover_card.CoverCardInterfaceView;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.view.cover_carousel.cover_card.CoverCardView;
import java.util.ArrayList;
import java.util.List;

public class CoverCardViewPagerAdapter extends PagerAdapter {

    private final Context context;
    @Nullable private OnClickCallback onClickCallback;
    private final List<CoverCardInterfaceView> elementsView;

    /* default */ CoverCardViewPagerAdapter(final Context context) {
        this.context = context;
        elementsView = new ArrayList<>();
    }

    /* default */ void setElementsView(final List<CoverCardInterface> itemsView) {
        final List<CoverCardInterfaceView> coverCardsViews = new ArrayList<>();

        CoverCardView view;
        for (final CoverCardInterface itemData : itemsView) {
            view = new CoverCardView(context);
            view.setOnClickCallback(onClickCallback);
            view.bind(itemData);
            coverCardsViews.add(view);
        }

        elementsView.clear();
        elementsView.addAll(coverCardsViews);
        notifyDataSetChanged();
    }

    /* default */ List<CoverCardInterfaceView> getElementsList() {
        return elementsView;
    }

    /* default */ void setOnClickCallback(@Nullable final OnClickCallback onClickCallback) {
        this.onClickCallback = onClickCallback;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull final ViewGroup container, final int position) {
        final int virtualPosition = position % elementsView.size();
        container.addView(elementsView.get(virtualPosition).getView());
        return elementsView.get(virtualPosition);
    }

    @Override
    public int getCount() {
        return elementsView.size();
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object view) {
        container.removeView((View) view);
    }

    @Override
    public boolean isViewFromObject(@NonNull final View view, @NonNull final Object object) {
        return view.equals(object);
    }

    @Override
    public int getItemPosition(@NonNull final Object object) {
        return POSITION_NONE;
    }
}
