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
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.response.MLBusinessTouchpointData;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.tracking.MLBusinessTouchpointTracker;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.tracking.print.MLBusinessTouchpointListener;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.view.MLBusinessTouchpointView;
import com.mercadolibre.android.mlbusinesscomponentsapp.R;

public class TouchpointTestActivity extends AppCompatActivity implements OnClickCallback {

    private ScrollView scrollView;
    private Button touchpointChanger;
    private int touchpointResponseIndex;

    private MLBusinessTouchpointView touchpointView;
    private MLBusinessTouchpointListener printerListener;
    private MLBusinessTouchpointTracker tracker;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.touchpoint_test_activity);
        scrollView = findViewById(R.id.tochpoint_scrollview);
        touchpointView = findViewById(R.id.touchpoint_view);
        touchpointChanger = findViewById(R.id.touchpoint_button);
        init();
    }

    @Override
    protected void onStop() {
        super.onStop();
        printerListener.resetTrackedPrints();
    }

    private void init() {
        tracker = mockTracker();
        printerListener = MLBusinessTouchpointListener.listen(tracker, scrollView);
        touchpointView.setOnClickCallback(this);
        configButton();
    }

    private void updateView() {
        final MLBusinessTouchpointData data =
            TouchpointSamples.values()[touchpointResponseIndex].retrieveResponse(this, tracker);
        touchpointView.update(data);
        printerListener.resetTrackedPrints();
    }

    @Override
    public void onClick(@Nullable final String deepLink) {
        if (deepLink != null) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(deepLink)));
        }
    }

    private void configButton() {
        touchpointChanger.setOnClickListener(v -> {
            if (TouchpointSamples.values().length == touchpointResponseIndex) {
                touchpointResponseIndex = 0;
            }
            updateView();
            touchpointResponseIndex++;
        });
        touchpointChanger.callOnClick();
    }

    private MLBusinessTouchpointTracker mockTracker() {
        return (action, eventData) -> Toast
            .makeText(this, "Track -> action: " + action + " with data: " + eventData, Toast.LENGTH_SHORT).show();
    }
}
