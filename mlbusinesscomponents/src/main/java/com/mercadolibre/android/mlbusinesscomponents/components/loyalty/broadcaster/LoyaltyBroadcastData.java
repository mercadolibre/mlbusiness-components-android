package com.mercadolibre.android.mlbusinesscomponents.components.loyalty.broadcaster;

public class LoyaltyBroadcastData {

    private Integer points;
    private Integer level;
    private Float percentage;
    private String primaryColor;
    private Integer pendingNotifications;

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Float getPercentage() {
        return percentage;
    }

    public void setPercentage(Float percentage) {
        this.percentage = percentage;
    }

    public String getPrimaryColor() {
        return primaryColor;
    }

    public void setPrimaryColor(String primaryColor) {
        this.primaryColor = primaryColor;
    }

    public Integer getPendingNotifications() {
        return pendingNotifications;
    }

    public void setPendingNotifications(Integer pendingNotifications) {
        this.pendingNotifications = pendingNotifications;
    }
}
