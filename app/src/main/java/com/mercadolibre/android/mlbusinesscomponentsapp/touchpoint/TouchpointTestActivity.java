package com.mercadolibre.android.mlbusinesscomponentsapp.touchpoint;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.Toast;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.callback.OnClickCallback;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.response.TouchpointResponse;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.tracking.TouchpointTracker;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.tracking.print.TouchpointPrintListener;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.view.TouchpointView;
import com.mercadolibre.android.mlbusinesscomponentsapp.R;

public class TouchpointTestActivity extends AppCompatActivity implements OnClickCallback {

    private TouchpointView touchpointView;
    private ScrollView scrollView;
    private Button touchpointChanger;
    private int touchpointResponseIndex = -1;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.touchpoint_test_activity);
        scrollView = findViewById(R.id.tochpoint_scrollview);
        touchpointView = findViewById(R.id.touchpoint_view);
        touchpointChanger = findViewById(R.id.touchpoint_button);
        init();
    }

    @SuppressLint("ClickableViewAccessibility")
    private void init() {
        final TouchpointTracker tracker = mockTracker();
        scrollView.setOnTouchListener(new TouchpointPrintListener(tracker));
        touchpointView.setTracker(tracker);
        touchpointView.setOnClickCallback(this);
        touchpointChanger.setOnClickListener(v -> {
            final TouchpointResponse response = getResponse();
            touchpointView.init(response);
        });
        touchpointChanger.callOnClick();
    }

    private TouchpointResponse getResponse() {
        if (TouchpointSamples.values().length <= touchpointResponseIndex) {
            touchpointResponseIndex = -1;
        }
        touchpointResponseIndex++;
        return TouchpointSamples.values()[touchpointResponseIndex].retrieveResponse(this);

    }

    private TouchpointTracker mockTracker() {
        return (action, eventData) -> Toast
            .makeText(this, "Track -> action: " + action + " with data: " + eventData, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(@Nullable final String deepLink) {
        if (deepLink != null) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(deepLink)));
        }
    }
}
