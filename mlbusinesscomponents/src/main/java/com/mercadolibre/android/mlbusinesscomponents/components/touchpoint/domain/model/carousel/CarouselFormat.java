package com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.carousel;

import android.os.Parcel;
import android.os.Parcelable;

public class CarouselFormat implements Parcelable {

    public static final Creator<CarouselFormat> CREATOR = new Creator<CarouselFormat>() {
        @Override
        public CarouselFormat createFromParcel(final Parcel in) {
            return new CarouselFormat(in);
        }

        @Override
        public CarouselFormat[] newArray(final int size) {
            return new CarouselFormat[size];
        }
    };

    private final String backgroundColor;
    private final String textColor;

    /**
     * Constructor
     *
     * @param backgroundColor the feature background color.
     * @param textColor the feature text color.
     */
    public CarouselFormat(final String backgroundColor, final String textColor) {
        this.backgroundColor = backgroundColor;
        this.textColor = textColor;
    }

    /**
     * Constructor
     *
     * @param in the input data.
     */
    protected CarouselFormat(final Parcel in) {
        backgroundColor = in.readString();
        textColor = in.readString();
    }

    public String getBackgroundColor() {
        return backgroundColor;
    }

    public String getTextColor() {
        return textColor;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(final Parcel dest, final int flags) {
        dest.writeString(backgroundColor);
        dest.writeString(textColor);
    }

    //CPD-OFF
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CarouselFormat)) {
            return false;
        }

        final CarouselFormat that = (CarouselFormat) o;

        if (getBackgroundColor() == null ? that.getBackgroundColor() != null
            : !getBackgroundColor().equals(that.getBackgroundColor())) {
            return false;
        }
        return getTextColor() == null ? that.getTextColor() == null : getTextColor().equals(that.getTextColor());
    }

    @SuppressWarnings("checkstyle:magicnumber")
    @Override
    public int hashCode() {
        int result = getBackgroundColor() == null ? 0 : getBackgroundColor().hashCode();
        result = 31 * result + (getTextColor() == null ? 0 : getTextColor().hashCode());
        return result;
    }
}
