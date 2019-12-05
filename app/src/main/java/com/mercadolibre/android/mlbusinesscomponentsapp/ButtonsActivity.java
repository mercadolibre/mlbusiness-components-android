package com.mercadolibre.android.mlbusinesscomponentsapp;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.mercadolibre.android.mlbusinesscomponents.components.explodingbutton.ButtonProgress;
import com.mercadolibre.android.mlbusinesscomponents.components.explodingbutton.OnFinishAnimationListener;


public class ButtonsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_botones);
        ButtonProgress buttonProgress = findViewById(R.id.progressTest1);
        buttonProgress.Builder()
                .setTextSize(16)
                .setTextInformation("Procesar Pago","Cargando")
                .setColorText(R.color.ui_meli_red)
                .setDurationRipple(500)
                .setMaxTimeFromServices(7000)
                .setDurationFinishProgress(1000)
                .setDurationAnimationCircle(200)
                .setDurationDelayRipple(500)
                .setViewParent(findViewById(R.id.ripple))
                .addFinishAnimationListener(getFinishAnimationListener());

        new Handler().postDelayed(() -> buttonProgress.finishProgress(
                R.color.ui_meli_red,
                R.drawable.mercado_pago
        ), 3000);

        ButtonProgress buttonProgress2 = findViewById(R.id.progressTest2);
        buttonProgress2.Builder()
                .setTextSize(16)
                .setTextInformation("Procesar Pago","Cargando")
                .setColorText(R.color.ui_meli_black)
                .setDurationRipple(500)
                .setMaxTimeFromServices(7000)
                .setDurationFinishProgress(1000)
                .setDurationAnimationCircle(200)
                .setDurationDelayRipple(500)
                .setViewParent(findViewById(R.id.ripple))
                .addFinishAnimationListener(getFinishAnimationListener());

        new Handler().postDelayed(() -> buttonProgress2.finishProgress(
                R.color.ui_meli_yellow,
                R.drawable.ui_ic_clear
        ), 3750);

        ButtonProgress buttonProgress3 = findViewById(R.id.progressTest3);
        buttonProgress3.Builder()
                .setTextSize(16)
                .setTextInformation("Procesar Pago","Cargando")
                .setColorText(R.color.ui_meli_white)
                .setDurationRipple(500)
                .setMaxTimeFromServices(7000)
                .setDurationFinishProgress(1000)
                .setDurationAnimationCircle(200)
                .setDurationDelayRipple(500)
                .setViewParent(findViewById(R.id.ripple))
                .addFinishAnimationListener(getFinishAnimationListener());

        new Handler().postDelayed(() -> buttonProgress3.finishProgress(
                R.color.ui_meli_blue,
                R.drawable.skeleton
        ), 4500);

        ButtonProgress buttonProgress4 = findViewById(R.id.progressTest4);
        buttonProgress4.Builder()
                .setTextSize(16)
                .setTextInformation("Procesar Pago","Cargando")
                .setColorText(R.color.ui_meli_white)
                .setDurationRipple(500)
                .setMaxTimeFromServices(7000)
                .setDurationFinishProgress(1000)
                .setDurationAnimationCircle(200)
                .setDurationDelayRipple(500)
                .setViewParent(findViewById(R.id.ripple))
                .addFinishAnimationListener(getFinishAnimationListener());

        new Handler().postDelayed(() -> buttonProgress4.finishProgress(
                R.color.ui_meli_green,
                R.drawable.mercado_libre
        ), 5250);
    }

    private OnFinishAnimationListener getFinishAnimationListener(){
        return () -> Toast.makeText(this, "A nimation finish", Toast.LENGTH_SHORT).show();
    }
}
