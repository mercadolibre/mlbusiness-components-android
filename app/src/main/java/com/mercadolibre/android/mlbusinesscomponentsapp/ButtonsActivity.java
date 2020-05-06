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
        buttonProgress.Builder(findViewById(R.id.ripple))
                .setTextSize(16)
                .setTextInformation("Procesar Pago","Cargando")
                .setColorText(R.color.ui_meli_red);

        buttonProgress.setOnClickListener(v -> {
                Toast.makeText(ButtonsActivity.this,"hola", Toast.LENGTH_SHORT).show();
                buttonProgress.startAnimationCustom();
            }
        );

        new Handler().postDelayed(buttonProgress::startAnimationCustom, 3000);
        new Handler().postDelayed(buttonProgress::resetButton, 5000);
    }

    private OnFinishAnimationListener getFinishAnimationListener(){
        return () -> Toast.makeText(this, "A nimation finish", Toast.LENGTH_SHORT).show();
    }
}
