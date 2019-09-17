![Screenshot Android](https://github.com/mercadolibre/mlbusiness-components-android/blob/master/documentation/images/android_cover.png?raw=true)

<p align="center">
<a href="https://app.bitrise.io/app/6a0b02551ce70568">
<img src="https://app.bitrise.io/app/6a0b02551ce70568/status.svg?token=Zb_oXsTJoxHYoTS06kEZvg">
</a>

<a href="https://codebeat.co/projects/github-com-mercadolibre-mlbusiness-components-android-master">
<img src="https://codebeat.co/badges/d67f4c91-472f-4d98-99d8-3686ae75b853" alt="Codebeat quality status" />
</a>
</p>

# 📲 How to Install

#### Local deployment

With this command you can generate a local version for testing:

```./gradlew publishLocal```

# 🐒 How to use

### 1 - Android Studio

Add this line to your app's build.gradle inside the dependencies section:

```implementation 'com.mercadolibre.android.mlbusinesscomponents:mlbusinesscomponents:1.0.0'```

### 2 - Use your UI component.
Choose and instantiate your component.


# 📦 COMPONENTS
Each component is a subclass of ```ConstraintLayout```.

## 1️⃣ - MLBusinessLoyaltyRingView Component
This component allow you to show the progress ring of points, a label and actionable button. The most common use of this component is to show a user's progress within the loyalty program.

#### Visual Example:
![MLBusinessLoyaltyRingView](https://github.com/mercadolibre/mlbusiness-components-android/blob/master/documentation/images/loyaltyRingViewComponent.png?raw=true)

### MLBusinessLoyaltyRingView init
You need to set `MLBusinessLoyaltyRingData` interface. This interface allow you to populate the draw data into component. (Ring progress percent, ring color, label text, button title and button deeplink). You can be informed when the user presses the button of the component and receive the deeplink previously sent in `MLBusinessLoyaltyRingData`. Just add `OnClickLoyaltyRing` callback.

```java
MLBusinessLoyaltyRingView ringView = findViewById(R.id.ringView);
ringView.init(new MLBusinessLoyaltyRingDataSample(), new MLBusinessLoyaltyRingView.OnClickLoyaltyRing() {
            @Override
            public void onClickLoyaltyButton(@NonNull final String deepLink) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(deepLink)));
            }
        });
```

### MLBusinessLoyaltyRingData Interface
This interface allow you to providade the proper data to draw `MLBusinessLoyaltyRingView`. You can setup ring progress percent, ring color, label text, button title and button deeplink. Each value is mandatory.

#### Definition
```java
public interface MLBusinessLoyaltyRingData {
    String getRingHexaColor();
    int getRingNumber();
    float getRingPercentage();
    String getTitle();
    String getButtonTitle();
    String getButtonDeepLink();
}
```

#### Implementation Example
Implementation of `MLBusinessLoyaltyRingDataSample` example:
```java
public class MLBusinessLoyaltyRingDataSample implements MLBusinessLoyaltyRingData {
    @Override
    public String getRingHexaColor() {
        return "#3483FA";
    }

    @Override
    public int getRingNumber() {
        return 3;
    }

    @Override
    public float getRingPercentage() {
        return 60f;
    }

    @Override
    public String getTitle() {
        return "Sumaste 20 Mercado Puntos";
    }

    @Override
    public String getButtonTitle() {
        return "Mis beneficios";
    }

    @Override
    public String getButtonDeepLink() {
        return "https://www.mercadolibre.com.ar/";
    }
}
```

## 2️⃣ - MLBusinessDiscountBoxView Component
This component allow you to show a group of N items in a grid system (3 cols by default). You can add a title and subtitle for the main component. Also, you can provide imageUrl, title, subtitle, deepLinkItem and trackId for each item. This component is responsible for knowing and setting your own height based on number of cols and item quantity.
#### Visual Example:
![MLBusinessDiscountBoxView](https://github.com/mercadolibre/mlbusiness-components-android/blob/master/documentation/images/discountBoxViewComponent.png?raw=true)

### MLBusinessDiscountBoxView init
You need to set `MLBusinessDiscountBoxData` interface. This interface allow you to populate the draw data into component. (Title, subtitle for the main component and imageUrl, title, subtitle, deepLinkItem and trackId for each item). You can be informed when the user presses the item of the component and receive the deeplink and trackId previously sent in `MLBusinessSingleItem`. Additionally receive the index of the selected item. Just add `OnClickDiscountBox` callback.

```java
MLBusinessDiscountBoxView discountBoxView = findViewById(R.id.discountView);
discountBoxView.init(new MLBusinessDiscountBoxDataSample(),
            new MLBusinessDiscountBoxView.OnClickDiscountBox() {
                @Override
                public void onClickDiscountItem(final int index, @Nullable final String deepLink,
                    @Nullable final String trackId) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(deepLink)));
                }
            });
```

### MLBusinessDiscountBoxData Interface
This interface allow you to providade the proper data to draw `MLBusinessDiscountBoxView`. You can setup title and subtitle for the main component and a list of `MLBusinessSingleItem` that represent each element of the cell.

#### Definition
```java
public interface MLBusinessDiscountBoxData {
    @Nullable
    String getTitle();
    @Nullable
    String getSubtitle();
    @NonNull
    List<MLBusinessSingleItem> getItems();
}
```

Implementation of `MLBusinessDiscountBoxDataSample` example:
```java
public class MLBusinessDiscountBoxDataSample implements MLBusinessDiscountBoxData {

    @Nullable
    @Override
    public String getTitle() {
        return "200 descuentos";
    }

    @Nullable
    @Override
    public String getSubtitle() {
        return "35 exclusivos nivel 3";
    }

    @NonNull
    @Override
    public List<MLBusinessSingleItem> getItems() {
        return DataSampleUtils.getItems();
    }
}
```
### MLBusinessSingleItem
This interface represents the element of each cell for `MLBusinessDiscountBoxView`.
Each element contains imageUrl, title, subtitle, deepLinkItem and trackId.

#### Definition
```java
public interface MLBusinessSingleItem {
    String getImageUrl();
    String getTitleLabel();
    String getSubtitleLabel();
    @Nullable
    String getDeepLinkItem();
    @Nullable
    String getTrackId();
}
```

Implementation of `SingleItemDataSample` example:

```java
public class SingleItemDataSample implements MLBusinessSingleItem {

    @Override
    public String getImageUrl() {
        return "https://upload.wikimedia.org/wikipedia/commons/thumb/3/36/McDonald%27s_Golden_Arches.svg/1200px-McDonald%27s_Golden_Arches.svg.png";
    }

    @Override
    public String getTitleLabel() {
        return "Hasta";
    }

    @Override
    public String getSubtitleLabel() {
        return "$ 200";
    }

    @Nullable
    @Override
    public String getDeepLinkItem() {
        return "https://www.mercadopago.com.ar/";
    }

    @Nullable
    @Override
    public String getTrackId() {
        return null;
    }
}
```

## 3️⃣ - MLBusinessDividingLineView Component
This component allow you to show a divider line with option to show an inverted triangle.
See `app:hasTriangle="boolean"`.

#### Visual Example:
![MLBusinessDividingLineView](https://raw.githubusercontent.com/mercadolibre/mlbusiness-components-android/feature/new_components/documentation/images/MLBusinessDividingLineView.png)

#### Implementation
```xml
<com.mercadolibre.android.mlbusinesscomponents.components.common.dividingline.MLBusinessDividingLineView
            android:id="@+id/divider"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            app:hasTriangle="true"/>
```

## 4️⃣ - MLBusinessDownloadAppView Component
This component allows you to show a means to download the ML or MP application. You can add an AppSite to display ML or MP icons, title and button title.

#### Visual Example:
![MLBusinessDownloadAppView](https://raw.githubusercontent.com/mercadolibre/mlbusiness-components-android/feature/new_components/documentation/images/MLBusinessDownloadAppView.png)

### MLBusinessDownloadAppView init
You need to set `MLBusinessDownloadAppData` interface. This interface allow you to populate the draw data into component. (AppSite, Title and ButtonTitle). You can be informed when the user presses the item of the component. Just add `OnClickListener` callback.

```java
MLBusinessDownloadAppView downloadAppView = findViewById(R.id.downloadAppView);
downloadAppView.init(new MLBusinessDownloadAppDataSample());
downloadAppView.setOnClickDownloadButton(new View.OnClickListener() {
        @Override
        public void onClick(final View v) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.mercadolibre.com.ar/")));
        }
    });
```
### MLBusinessDownloadAppData Interface
This interface allow you to providade the proper data to draw `MLBusinessDownloadAppView`. You can setup AppSite, Title and ButtonTitle. Each value is mandatory.

#### Definition
```java
public interface MLBusinessDownloadAppData {
    @NonNull
    MLBusinessDownloadAppView.AppSite getAppSite();
    @NonNull
    String getTitle();
    @NonNull
    String getButtonTitle();
}
```

Implementation of `MLBusinessDownloadAppDataSample` example:
```java
public class MLBusinessDownloadAppDataSample implements MLBusinessDownloadAppData {
    @NonNull
    @Override
    public MLBusinessDownloadAppView.AppSite getAppSite() {
        return MLBusinessDownloadAppView.AppSite.MP;
    }

    @NonNull
    @Override
    public String getTitle() {
        return "Exclusivo con la app de Mercado Pago";
    }

    @NonNull
    @Override
    public String getButtonTitle() {
        return "Descargar";
    }
}
```

## 🔠 Font and color customization.
We use `MLUI` open source library to customize accent colors and font labels. In order to change those values check the documentation of `MLUI` stylesheet protocol.
https://github.com/mercadolibre/fury_mobile-android-ui

## 😉 Next steps?
* [x] Bitrise for releases.
* [x] Codebeat
* [x] AndroidLint.

## 📋 Supported OS & SDK Versions
* Android 4.1 (nivel de API 16)
* Android Studio 3.1+

## 🔮 Project Example
This project include a Android example project using `MLBusinessComponents` basic components.

## ❤️ Feedback
- Feel free to contribute or send feedback. Fork this project and propose your own fixes, suggestions and open a pull request with the changes.

## 👨🏻‍💻 Author
- Jorge Gonzalez / jorge.gonzalez@mercadolibre.com

## 👮🏻 License

```
MIT License

Copyright (c) 2019 - Mercado Pago / Mercado Libre

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```
