package com.mercadolibre.android.mlbusinesscomponents.components.loyalty.broadcaster;

public interface LoyaltyBroadcastHandler {

    @SuppressWarnings("unused")
    void onEventMainThread(final LoyaltyBroadcastData event);

}
