package com.mercadolibre.android.mlbusinesscomponents.components.loyalty.broadcaster;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;

public final class LoyaltyBroadcaster {

    public static final String LOYALTY_BROADCASTER_NAME = "LOYALTY_BROADCASTER";
    public static final String LOYALTY_BROADCAST_LEVEL_KEY = "LOYALTY_BROADCAST_LEVEL_KEY";
    public static final String LOYALTY_BROADCAST_POINTS_KEY = "LOYALTY_BROADCAST_POINTS_KEY";
    public static final String LOYALTY_BROADCAST_PERCENTAGE_KEY = "LOYALTY_BROADCAST_PERCENTAGE_KEY";
    public static final String LOYALTY_BROADCAST_PRIMARY_COLOR_KEY = "LOYALTY_BROADCAST_PRIMARY_COLOR_KEY";
    public static final String LOYALTY_BROADCAST_PENDING_NOTIFICATIONS_KEY = "LOYALTY_BROADCAST_PENDING_NOTIFICATIONS_KEY";

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

    @SuppressWarnings("unused")
    public void updateInfo(Context context, LoyaltyBroadcastData loyaltyBroadcastData) {
        Intent intent = new Intent(LOYALTY_BROADCASTER_NAME);

        intent.putExtra(LOYALTY_BROADCAST_LEVEL_KEY, loyaltyBroadcastData.getLevel());
        intent.putExtra(LOYALTY_BROADCAST_POINTS_KEY, loyaltyBroadcastData.getPoints());
        intent.putExtra(LOYALTY_BROADCAST_PERCENTAGE_KEY, loyaltyBroadcastData.getPercentage());
        intent.putExtra(LOYALTY_BROADCAST_PRIMARY_COLOR_KEY, loyaltyBroadcastData.getPrimaryColor());
        intent.putExtra(LOYALTY_BROADCAST_PENDING_NOTIFICATIONS_KEY, loyaltyBroadcastData.getPendingNotifications());

        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
    }

}
