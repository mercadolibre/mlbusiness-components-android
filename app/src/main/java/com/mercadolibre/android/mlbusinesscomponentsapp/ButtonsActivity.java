package com.mercadolibre.android.mlbusinesscomponentsapp;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;
import com.mercadolibre.android.mlbusinesscomponents.components.explodingbutton.ProgressButton;


public class ButtonsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_botones);
        ProgressButton buttonProgress = findViewById(R.id.progressTest1);
        buttonProgress.Builder()
                .setTextSize(16)
                .setTextInformation("Procesar Pago","Cargando")
                .setDurationAnimationCircle(7000)
                .setDurationDelayRipple(7000)
                .setDurationRipple(7000)
                .setColorText(R.color.ui_meli_red);

        buttonProgress.setOnClickListener(v -> {
            Toast.makeText(ButtonsActivity.this,"hola", Toast.LENGTH_SHORT).show();
            }
        );

        buttonProgress.addFinishAnimationListener(() -> {
            Toast.makeText(ButtonsActivity.this, "hola", Toast.LENGTH_SHORT).show();
            buttonProgress.reset();
            }
        );

        new Handler().postDelayed(() -> {
            buttonProgress.finishProgress(
                R.color.ui_meli_red,
                null
            );
        }, 3000);
    }
}
