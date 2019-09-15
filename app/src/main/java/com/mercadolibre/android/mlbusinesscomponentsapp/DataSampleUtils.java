package com.mercadolibre.android.mlbusinesscomponentsapp;

import java.util.LinkedList;
import java.util.List;

class DataSampleUtils {

    static List<com.mercadolibre.android.mlbusinesscomponents.common.MLBusinessSingleItem> getItems() {

        List<com.mercadolibre.android.mlbusinesscomponents.common.MLBusinessSingleItem> items = new LinkedList<>();

        items.add(new SingleItemDataSample(
            "https://upload.wikimedia.org/wikipedia/commons/thumb/3/36/McDonald%27s_Golden_Arches.svg/1200px-McDonald%27s_Golden_Arches.svg.png",
            "Hasta",
            "$ 200",
            null, ""
        ));
        items.add(new SingleItemDataSample(
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRY0eFECzyTCa83gOV3smCYDOSIggdUxSPirwtt5rS3LcWlzefo",
            "Hasta",
            "$ 300",
            "https://www.mercadopago.com.ar/", null
        ));
        items.add(new SingleItemDataSample(
            "https://upload.wikimedia.org/wikipedia/commons/b/b3/Logo-freddo.jpg",
            "Hasta",
            "$ 500",
            "https://www.mercadopago.com.ar/"
            , null
        ));
        items.add(new SingleItemDataSample(
            "https://urbancomunicacion.com/wp-content/uploads/2017/04/Logotipos-famosos-Starbucks-Urban-comunicacion-1.png",
            "Hasta",
            "$ 150",
            "https://www.mercadopago.com.ar/", null
        ));
        items.add(new SingleItemDataSample(
            "https://www.stickpng.com/assets/images/5a1c3211f65d84088faf13e8.png",
            "Hasta",
            "$ 100",
            "https://www.mercadopago.com.ar/", null
        ));
        items.add(new SingleItemDataSample(
            "https://pbs.twimg.com/profile_images/1124417403566395394/9Wuzg8pf.png",
            "Hasta",
            "$ 200",
            "https://www.mercadopago.com.ar/", null
        ));






        items.add(new SingleItemDataSample(
            "https://upload.wikimedia.org/wikipedia/en/thumb/6/66/Wendy%27s_logo_2012.svg/1200px-Wendy%27s_logo_2012.svg.png",
            "Hasta",
            "$ 200",
            "https://www.mercadopago.com.ar/", null
        ));
        items.add(new SingleItemDataSample(
            "https://upload.wikimedia.org/wikipedia/commons/thumb/3/36/McDonald%27s_Golden_Arches.svg/1200px-McDonald%27s_Golden_Arches.svg.png",
            "Hasta",
            "$ 200",
            null, ""
        ));
        items.add(new SingleItemDataSample(
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRY0eFECzyTCa83gOV3smCYDOSIggdUxSPirwtt5rS3LcWlzefo",
            "Hasta",
            "$ 300",
            "https://www.mercadopago.com.ar/", null
        ));


        return items;
    }
}
