package com.mercadolibre.android.mlbusinesscomponents.components.loyalty.broadcaster;

import de.greenrobot.event.EventBus;

public final class LoyaltyBroadcaster {

    private LoyaltyBroadcastData loyaltyBroadcastData;
    private static LoyaltyBroadcaster instance;
    private static final EventBus LOYALTY_DRAWER_BUS = new EventBus();

    @SuppressWarnings("unused")
    public static LoyaltyBroadcaster getInstance() {
        if (instance == null) {
            instance = new LoyaltyBroadcaster();
        }
        return instance;
    }

    public static EventBus getLoyaltyBroadcasterBus() {
        return LOYALTY_DRAWER_BUS;
    }

    @SuppressWarnings("unused")
    public void updateInfo(LoyaltyBroadcastData loyaltyBroadcastData) {
        this.loyaltyBroadcastData = loyaltyBroadcastData;
        postNewLoyaltyInfo(loyaltyBroadcastData);
    }

    private void postNewLoyaltyInfo(LoyaltyBroadcastData loyaltyBroadcastData) {
        getLoyaltyBroadcasterBus().post(loyaltyBroadcastData);
    }

}
