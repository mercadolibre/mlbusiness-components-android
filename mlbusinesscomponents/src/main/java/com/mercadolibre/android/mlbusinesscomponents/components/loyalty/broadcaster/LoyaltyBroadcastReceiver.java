package com.mercadolibre.android.mlbusinesscomponents.components.loyalty.broadcaster;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public abstract class LoyaltyBroadcastReceiver extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {
        if (LoyaltyBroadcaster.LOYALTY_BROADCASTER_NAME.equals(intent.getAction())) {
            if (intent.hasExtra(LoyaltyBroadcaster.LOYALTY_BROADCAST_PRIMARY_COLOR_KEY) &&
                    intent.hasExtra(LoyaltyBroadcaster.LOYALTY_BROADCAST_LEVEL_KEY) &&
                    intent.hasExtra(LoyaltyBroadcaster.LOYALTY_BROADCAST_PENDING_NOTIFICATIONS_KEY) &&
                    intent.hasExtra(LoyaltyBroadcaster.LOYALTY_BROADCAST_POINTS_KEY) &&
                    intent.hasExtra(LoyaltyBroadcaster.LOYALTY_BROADCAST_PERCENTAGE_KEY)) {

                LoyaltyBroadcastData loyaltyBroadcastData = new LoyaltyBroadcastData();
                loyaltyBroadcastData.setPercentage(intent.getFloatExtra(LoyaltyBroadcaster.LOYALTY_BROADCAST_PERCENTAGE_KEY, 0));
                loyaltyBroadcastData.setPoints(intent.getIntExtra(LoyaltyBroadcaster.LOYALTY_BROADCAST_POINTS_KEY, 0));
                loyaltyBroadcastData.setLevel(intent.getIntExtra(LoyaltyBroadcaster.LOYALTY_BROADCAST_LEVEL_KEY, 1));
                loyaltyBroadcastData.setPrimaryColor(intent.getStringExtra(LoyaltyBroadcaster.LOYALTY_BROADCAST_PRIMARY_COLOR_KEY));
                loyaltyBroadcastData.setPendingNotifications(intent.getIntExtra(LoyaltyBroadcaster.LOYALTY_BROADCAST_PENDING_NOTIFICATIONS_KEY, 0));

                onReceive(loyaltyBroadcastData);

            }
        }
    }

    public abstract void onReceive(LoyaltyBroadcastData loyaltyBroadcastData);
}
