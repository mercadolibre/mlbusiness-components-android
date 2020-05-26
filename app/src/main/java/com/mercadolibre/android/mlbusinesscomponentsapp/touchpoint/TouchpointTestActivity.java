package com.mercadolibre.android.mlbusinesscomponentsapp.touchpoint;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.Toast;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.callback.OnClickCallback;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.response.MLBusinessTouchpointResponse;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.tracking.MLBusinessTouchpointTracker;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.tracking.print.MLBusinessTouchpointListener;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.view.MLBusinessTouchpointView;
import com.mercadolibre.android.mlbusinesscomponentsapp.R;
import java.util.Map;

public class TouchpointTestActivity extends AppCompatActivity implements OnClickCallback {

    private ScrollView scrollView;
    private Button touchpointChangerTop;
    private Button touchpointChangerBottom;
    private int touchpointResponseIndex;

    private MLBusinessTouchpointView touchpointView;
    private MLBusinessTouchpointListener touchpointListener;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.touchpoint_test_activity);
        scrollView = findViewById(R.id.tochpoint_scrollview);
        touchpointView = findViewById(R.id.touchpoint_view);
        touchpointChangerBottom = findViewById(R.id.touchpoint_button_bottom);
        touchpointChangerTop = findViewById(R.id.touchpoint_button_top);
        init();
    }

    private void init() {
        touchpointListener = new MLBusinessTouchpointListener();
        touchpointListener.setOnTouchListener(scrollView);
        touchpointView.setOnClickCallback(this);
        touchpointView.setTracker(mockTracker());
        touchpointView.setCanOpenMercadoPago(true);
        initButtons();
    }

    private void initButtons() {
        configTouchpointButton(touchpointChangerBottom);
        configTouchpointButton(touchpointChangerTop);
        touchpointChangerBottom.callOnClick();
    }

    @Override
    protected void onPause() {
        super.onPause();
        touchpointListener.resetTrackedPrints();
        Toast.makeText(this, "Clean prints history", Toast.LENGTH_SHORT).show();
    }

    private void updateView() {
        final MLBusinessTouchpointResponse data =
            TouchpointSamples.values()[touchpointResponseIndex].retrieveResponse(this);
        touchpointView.update(data);
    }

    @Override
    public void onClick(@Nullable final String deepLink) {
        if (deepLink != null) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(deepLink)));
        }
    }

    private void configTouchpointButton(final Button touchpointButton) {
        touchpointButton.setOnClickListener(v -> {
            touchpointListener.resetTrackedPrints();
            if (TouchpointSamples.values().length == touchpointResponseIndex) {
                touchpointResponseIndex = 0;
            }
            updateView();
            touchpointResponseIndex++;
        });
    }

    private MLBusinessTouchpointTracker mockTracker() {
        return new MLBusinessTouchpointTracker() {

            private String id;

            @Override
            public void track(@Nullable final String action, @Nullable final Map<String, Object> data) {
                Toast.makeText(getBaseContext(),
                    "Track -> context: " + id + " action: " + action + " with data: " + data, Toast.LENGTH_SHORT)
                    .show();
            }

            @Override
            public void setId(final String id) {
                this.id = id;
            }
        };
    }
}
