package com.mercadolibre.android.mlbusinesscomponents.components.loyalty.broadcaster;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;

public final class LoyaltyBroadcaster {

    public static final String LOYALTY_BROADCASTER_NAME = "LOYALTY_BROADCASTER";
    public static final String LOYALTY_BROADCAST_LEVEL_KEY = "LOYALTY_BROADCAST_LEVEL_KEY";
    public static final String LOYALTY_BROADCAST_PERCENTAGE_KEY = "LOYALTY_BROADCAST_PERCENTAGE_KEY";
    public static final String LOYALTY_BROADCAST_PRIMARY_COLOR_KEY = "LOYALTY_BROADCAST_PRIMARY_COLOR_KEY";

    private static LoyaltyBroadcaster instance;

    @SuppressWarnings("unused")
    public static LoyaltyBroadcaster getInstance() {
        if (instance == null) {
            instance = new LoyaltyBroadcaster();

        }
        return instance;
    }

    public void register(LoyaltyBroadcastReceiver receiver, Context context) {
        LocalBroadcastManager.getInstance(context).registerReceiver(receiver, new IntentFilter(LOYALTY_BROADCASTER_NAME));
    }

    public void unregister(LoyaltyBroadcastReceiver receiver, Context context) {
        LocalBroadcastManager.getInstance(context).unregisterReceiver(receiver);
    }

    @SuppressWarnings("unused")
    public void updateInfo(Context context, LoyaltyBroadcastData loyaltyBroadcastData) {
        Intent intent = new Intent(LOYALTY_BROADCASTER_NAME);

        intent.putExtra(LOYALTY_BROADCAST_LEVEL_KEY, loyaltyBroadcastData.getLevel());
        intent.putExtra(LOYALTY_BROADCAST_PERCENTAGE_KEY, loyaltyBroadcastData.getPercentage());
        intent.putExtra(LOYALTY_BROADCAST_PRIMARY_COLOR_KEY, loyaltyBroadcastData.getPrimaryColor());

        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
    }

}
