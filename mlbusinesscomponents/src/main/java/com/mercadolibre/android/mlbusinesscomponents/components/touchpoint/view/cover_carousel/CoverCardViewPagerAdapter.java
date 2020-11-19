package com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.view.cover_carousel;

import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.view.cover_carousel.cover_card.CoverCardView;
import java.util.ArrayList;
import java.util.List;

public class CoverCardViewPagerAdapter extends PagerAdapter {

    private final List<CoverCardView> elementsView;

    /* default */ CoverCardViewPagerAdapter() {
        elementsView = new ArrayList<>();
    }

    /* default */ void setElementsView(final List<CoverCardView> itemsView) {
        elementsView.clear();
        elementsView.addAll(itemsView);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull final ViewGroup container, final int position) {
        final int virtualPosition = position % elementsView.size();
        container.addView(elementsView.get(virtualPosition));
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
}
